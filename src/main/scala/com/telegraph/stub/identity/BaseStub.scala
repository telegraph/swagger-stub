package com.telegraph.stub.identity

import com.github.tomakehurst.wiremock.{WireMockServer, http}
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.atlassian.oai.validator.wiremock.SwaggerValidationListener
import com.github.tomakehurst.wiremock.common.FileSource
import com.github.tomakehurst.wiremock.extension.{Parameters, ResponseTransformer}
import com.github.tomakehurst.wiremock.http.{Request, Response}

import scala.io.Source

/**
  * @author ${parsh.toora}
  *
  * BaseStub configuring Wiremock with swagger file and abstract state and canned responses path
  *
  */

abstract class BaseStub {

  var wireMockServer: WireMockServer = null;
  private var wireMockListener: SwaggerValidationListener = null
  private var stubState:String="registered"

  def configureStub(inputSwaggerFile: String, inputPort: Int, cannedResponsesPath: String): Unit = {
    // port and swagger defn
    var port: Int = 8080
    if (inputPort != null)
      port = inputPort.toInt
    var swaggerFile = "home"
    if (inputSwaggerFile != null)
      swaggerFile = inputSwaggerFile

    wireMockServer = new WireMockServer(options().port(port).extensions(ContractValidationTransformer))
    wireMockListener = new SwaggerValidationListener(Source.fromFile(swaggerFile).mkString)
    wireMockServer.addMockServiceRequestListener(wireMockListener)
    setUpMocks(cannedResponsesPath)

    println(s"Stub configured for swagger api $swaggerFile running on port $port")
  }

  // start server
  def start = {
    if (wireMockServer!=null && !wireMockServer.isRunning)
      wireMockServer.start()
  }

  // stop server
  def stop = {
    if (wireMockServer==null)
      throw new Exception("Wiremock server may have found an invalid contract - please check logs")
    wireMockServer.stop
  }

  // action -> pre-start, post-state
  protected var stateTransitions : Map[String, Map[String,String]]

  protected def setUpMocks(cannedResponsesPath: String): Unit


  object ContractValidationTransformer extends ResponseTransformer {

    override def transform (request: Request, response: Response,
                            files: FileSource, parameters: Parameters): Response = {
      try {
        wireMockListener.reset()

        // validate swagger
        wireMockListener.requestReceived(request, response)
        wireMockListener.assertValidationPassed() // will throw error

        // validate state
        if (!stateTransitions.contains(request.getMethod.getName)) {
          return Response.Builder.like(response)
            .but()
            .body("Missing state transition for action: "+ request.getMethod.getName)
            .status(500)
            .build();
        }
        val stateTransitionsForThisAction = stateTransitions(request.getMethod.getName)
        if (!stateTransitionsForThisAction.contains(stubState)) {
          return Response.Builder.like(response)
            .but()
            .body("Invalid state transition for " + request.getMethod.getName + " from state " + stubState)
            .status(500)
            .build();
        }
        stubState = stateTransitionsForThisAction(stubState)

        // otherwise just act as if nothing happened
        return response

      } catch {
        case ex: Exception => {
          wireMockListener.reset()
          return Response.Builder.like(response)
            .but()
            .body("Invalid contract"+ ex.getLocalizedMessage)
            .status(500)
            .build();
        }
      }
    }

    override def getName: String = "validate-contract-request"
  }

}

