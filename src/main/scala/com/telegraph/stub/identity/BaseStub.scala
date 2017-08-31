package com.telegraph.stub.identity

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.atlassian.oai.validator.wiremock.SwaggerValidationListener
import com.atlassian.oai.validator.wiremock.SwaggerValidationListener.SwaggerValidationException
import com.github.tomakehurst.wiremock.core.Admin
import com.github.tomakehurst.wiremock.extension.PostServeAction
import com.github.tomakehurst.wiremock.stubbing.ServeEvent

import scala.io.Source

/**
  * @author ${parsh.toora}
  *
  * Alternative mechanisms for validating state can use '.withTransformerParameter("", "")
  */

abstract class BaseStub {

  var wireMockServer: WireMockServer = null;
  private var wireMockListener: SwaggerValidationListener = null

  def configureStub(inputSwaggerFile: String, inputPort: Int, cannedResponsesPath: String): Unit = {
    // port and swagger defn
    var port: Int = 8080
    if (inputPort != null)
      port = inputPort.toInt
    var swaggerFile = "home"
    if (inputSwaggerFile != null)
      swaggerFile = inputSwaggerFile

    wireMockServer = new WireMockServer(options().port(port).extensions(new ValidateContractAction))
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

  // validate contract and if invalid output error and bail out
  def validateContract = {
    try {
      wireMockListener.assertValidationPassed()
    } catch  {

      case ex: Exception => {
        println(ex.getLocalizedMessage)
        ex.printStackTrace()
        wireMockServer.resetAll()
        wireMockServer.shutdown()
        wireMockServer = null
        throw ex
      }
    }
  }

  // action -> pre-start, post-state
  protected var stateTransitions : Map[String, Map[String,String]]

  protected def setUpMocks(cannedResponsesPath: String): Unit

  // post serve action to validate contract
  class ValidateContractAction extends PostServeAction {
    override def doGlobalAction(serveEvent: ServeEvent, admin: Admin): Unit = {
      validateContract
    }
    override def getName: String = "validate-contract"
  }

  // Simple Bean to get things done
  class ContractValidationParameter(inputValidateName: String) extends Serializable {
    var validateName: String = inputValidateName
  }

}

