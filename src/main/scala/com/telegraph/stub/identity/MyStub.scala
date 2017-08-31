package com.telegraph.stub.identity

import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.stubbing.Scenario

import scala.io.Source

/**
  * Created by toorap on 17/08/2017.
  * Example of a stub encapsulating contract and state
  */
object MyStub extends BaseStub {


  // map of action -> map  of pre-state, post-state
  override var stateTransitions =    Map(
    "get" -> Map(Scenario.STARTED -> Scenario.STARTED))


  override def setUpMocks(cannedResponsesPath: String): Unit  = {

    // happy path get
    stateTransitions("get") foreach {

      case (inState, outState) =>
        wireMockServer.stubFor(get(urlMatching(".*/it"))
          .inScenario("state")
          .whenScenarioStateIs(inState)
          .withPostServeAction("validate-contract",new ContractValidationParameter("validate-contract"))
          .willReturn(
            aResponse()
              .withHeader("Content-Type", "application/json")
              .withBody("{ \"first\":\"Hello\", \"second\":\"World\" }")
              .withStatus(200))
          .willSetStateTo(outState)
        )
    }

  }

  // driver class
  def main(args : Array[String]) {

    // passed swagger file, port, canned responses file path
    MyStub.configureStub(args(0), args(1).toInt, args(2))
    MyStub.start
  }
}