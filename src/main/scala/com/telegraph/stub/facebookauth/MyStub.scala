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

    // tmg OPTIONS
    wireMockServer.stubFor(options(urlMatching("(.*)(tmgauth|fbauth)"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("access-control-allow-origin", "*")
          .withHeader("access-control-allow-headers", "content-type")
          .withHeader("access-control-allow-methods", "POST,OPTIONS")
          .withHeader("access-control-max-age", "86400")
          .withStatus(200)))

    // subscribed
    wireMockServer.stubFor(post(urlMatching(".*/tmgauth"))
      .withRequestBody(equalToJson("{\"password\":\"subscriber@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuthSubscribed.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    wireMockServer.stubFor(post(urlMatching(".*/fbauth"))
      .withRequestBody(equalToJson("{\"oauthtoken\":\"subscriber@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuthSubscribed.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    // registered
    wireMockServer.stubFor(post(urlMatching(".*/tmgauth"))
      .withRequestBody(equalToJson("{\"password\":\"registered@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuthSubscribed.json").mkString)
          .withStatus(200)))

    wireMockServer.stubFor(post(urlMatching(".*/fbauth"))
      .withRequestBody(equalToJson("{\"oauthtoken\":\"registered@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuthSubscribed.json").mkString)
          .withStatus(200)))

    // unauthorised
    wireMockServer.stubFor(post(urlMatching(".*/tmgauth"))
      .withRequestBody(equalToJson("{\"password\":\"unauthorised@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuth401.json").mkString)
          .withStatus(401)))

    wireMockServer.stubFor(post(urlMatching(".*/fbauth"))
      .withRequestBody(equalToJson("{\"oauthtoken\":\"unauthorised@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuth401.json").mkString)
          .withStatus(401)))

    // system error
    wireMockServer.stubFor(post(urlMatching(".*/tmgauth"))
      .withRequestBody(equalToJson("{\"password\":\"systemError@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuth500.json").mkString)
          .withStatus(500)))

    wireMockServer.stubFor(post(urlMatching(".*/fbauth"))
      .withRequestBody(equalToJson("{\"oauthtoken\":\"systemError@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuth500.json").mkString)
          .withStatus(500)))

    // unavailable
    wireMockServer.stubFor(post(urlMatching(".*/tmgauth"))
      .withRequestBody(equalToJson("{\"password\":\"unavailable@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuth503.json").mkString)
          .withStatus(503)))

    wireMockServer.stubFor(post(urlMatching(".*/fbauth"))
      .withRequestBody(equalToJson("{\"oauthtoken\":\"unavailable@telegraph.co.uk\"}",true,true))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath+ "/tmgAuth503.json").mkString)
          .withStatus(503)))
  }

  // driver class
  def main(args : Array[String]) {

    // port, canned file directory, swagger file, state model file, opening state
    MyStub.configureStub(args(0), args(1), args(2), args(3), "any")
    MyStub.start
  }
}