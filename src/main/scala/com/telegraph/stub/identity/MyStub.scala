package com.telegraph.stub.identity

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
    wireMockServer.stubFor(post(urlMatching(".*/tokens"))
      .withRequestBody(equalToJson("{\"identifier\":\"registered@telegraph.co.uk\"}",true,true))
      .willReturn(
          aResponse()
            .withHeader("Content-Type", "application/json")
            .withBody(Source.fromFile(cannedResponsesPath+ "/tokensPasswordGrantHappyRegistered.json").mkString)
            .withStatus(200)))

    // happy path subscribed
    wireMockServer.stubFor(post(urlMatching(".*/tokens"))
      .withRequestBody(equalToJson("{\"identifier\":\"subscriber@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tokensPasswordGrantHappySubscriber.json").mkString)
          .withStatus(200)))

    // happy path expired
    wireMockServer.stubFor(post(urlMatching(".*/tokens"))
      .withRequestBody(equalToJson("{\"identifier\":\"expired@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tokensPasswordGrantHappyExpired.json").mkString)
          .withStatus(200)))

    // happy path get remember_me=false
    wireMockServer.stubFor(post(urlMatching(".*/tokens"))
      .withRequestBody(equalToJson("{\"identifier\":\"dontRememberMe@telegraph.co.uk\"}",true,true))
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
          .withBody(Source.fromFile(cannedResponsesPath+ "/tokensPasswordGrantUnauthorised.json").mkString)
          .withStatus(401)))

    // sad path get - error
    wireMockServer.stubFor(post(urlMatching(".*/tokens"))
      .withRequestBody(equalToJson("{\"identifier\":\"error@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withStatus(500)))

    // sad path get - timeout
    wireMockServer.stubFor(post(urlMatching(".*/tokens"))
      .withRequestBody(equalToJson("{\"identifier\":\"timeout@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withFixedDelay(10000)
          .withStatus(408)))
  }

  // driver class
  def main(args : Array[String]) {

    // port, canned file directory, swagger file, state model file, opening state
    MyStub.configureStub(args(0), args(1), args(2), args(3), "registered")
    MyStub.start
  }
}