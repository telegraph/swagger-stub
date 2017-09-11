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

    // happy path registered remember_me=true
    wireMockServer.stubFor(post(urlMatching(".*/tmgauth"))
      .withRequestBody(equalToJson("{\"emailId\":\"subscribed@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuthSubscribed.json").mkString)
          .withStatus(200)))

    // happy path registered remember_me=true
    wireMockServer.stubFor(post(urlMatching(".*/fbauth"))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
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