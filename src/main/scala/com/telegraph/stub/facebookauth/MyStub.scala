package com.telegraph.stub.facebookauth

import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.http.RequestMethod
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import uk.co.telegraph.qe.SmartStub

import scala.io.Source

/**
  * Created by toorap on 17/08/2017.
  * Example of a stub encapsulating contract and state
  */
object MyStub extends SmartStub {


  override def setUpMocks(cannedResponsesPath: String): Unit  = {

    // happy path
    wireMockServer.stubFor(options(urlMatching(".*/tmgauth"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("access-control-allow-origin", "*")
          .withHeader("access-control-allow-headers", "content-type")
          .withHeader("access-control-allow-methods", "POST,OPTIONS")
          .withHeader("access-control-max-age", "86400")
          .withStatus(200)))

    // happy path
    wireMockServer.stubFor(post(urlMatching(".*/tmgauth"))
      .withRequestBody(equalToJson("{\"emailId\":\"subscribed@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuthSubscribed.json").mkString)
          .withStatus(200)))

    // unauthorised
    wireMockServer.stubFor(post(urlMatching(".*/tmgauth"))
      .withRequestBody(equalToJson("{\"emailId\":\"unauthorised@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuth401.json").mkString)
          .withStatus(401)))

    // system error
    wireMockServer.stubFor(post(urlMatching(".*/tmgauth"))
      .withRequestBody(equalToJson("{\"emailId\":\"systemError@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuth500.json").mkString)
          .withStatus(500)))

    // unavailable
    wireMockServer.stubFor(post(urlMatching(".*/tmgauth"))
      .withRequestBody(equalToJson("{\"emailId\":\"unavailable@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuth503.json").mkString)
          .withStatus(503)))

    // happy path fb
    wireMockServer.stubFor(post(urlMatching(".*/fbauth"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/fbAuthSubscribed.json").mkString)
          .withStatus(200)))

  }

  // driver class
  def main(args : Array[String]) {

    // port, canned file directory, swagger file, state model file, opening state
    MyStub.configureStub(args(0), args(1), args(2), args(3), "any")
    MyStub.start
  }
}