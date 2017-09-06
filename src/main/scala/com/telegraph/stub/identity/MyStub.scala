package com.telegraph.stub.identity

import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.http.RequestMethod
import scala.io.Source

/**
  * Created by toorap on 17/08/2017.
  * Example of a stub encapsulating contract and state
  */
object MyStub extends BaseStub {


  override def setUpMocks(cannedResponsesPath: String): Unit  = {

    // happy path get remeber_me=true
    wireMockServer.stubFor(post(urlMatching(".*/tokens"))
        .withRequestBody(equalToJson("{\"remember_me\":true}",true,true))
        .willReturn(
          aResponse()
            .withHeader("Content-Type", "application/json")
            .withBody(Source.fromFile(cannedResponsesPath+ "/tokensPasswordGrantHappy.json").mkString)
            .withStatus(200)))

    // happy path get remeber_me=false
    wireMockServer.stubFor(post(urlMatching(".*/tokens"))
      .withRequestBody(equalToJson("{\"remember_me\":false}",true,true))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tokensPasswordGrantHappyDontRememberMe.json").mkString)
          .withStatus(200)))

    // sad path get - unauthorised
    wireMockServer.stubFor(post(urlMatching(".*/tokens"))
      .withRequestBody(equalToJson("{\"identifier\":\"unauthorised@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tokensPasswordGrantSad.json").mkString)
          .withStatus(401)))
  }

  // driver class
  def main(args : Array[String]) {

    // port, canned file directory, swagger file, state model file, opening state
    MyStub.configureStub(args(0).toInt, args(1), args(2), args(3), "registered")
    MyStub.start
  }
}