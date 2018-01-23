package com.telegraph.stub.cruisestub

import com.github.tomakehurst.wiremock.client.WireMock._
import uk.co.telegraph.qe.SmartStub
import scala.io.Source

/**
  * Created by toorap on 17/08/2017.
  * Example of a stub encapsulating contract and state
  */
object MyStub extends SmartStub {


  override def setUpMocks(cannedResponsesPath: String): Unit  = {


    wireMockServer.stubFor(get(urlMatching(".*/n6DNHn4ryjz"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/basicHappyPath.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))


    wireMockServer.stubFor(get(urlMatching(".*/pvjqdBFp0sD"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/travelTeck.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    wireMockServer.stubFor(get(urlMatching(".*/pClvcx3M5N4"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/britishAisle.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    wireMockServer.stubFor(get(urlMatching(".*/ng5w8LfZ8Wl"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/britishDiscoveryAisle.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    wireMockServer.stubFor(get(urlMatching(".*/ng5xKjcstfs"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/britishIslesDiscovery.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    wireMockServer.stubFor(get(urlMatching(".*/mlMWvMk8tGh"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/easterSpring.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    wireMockServer.stubFor(get(urlMatching(".*/mlMWvMxfHXq"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/grandScottishLochs.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    wireMockServer.stubFor(get(urlMatching(".*/mlMWvMdxpST"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/icelandCruise.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    wireMockServer.stubFor(get(urlMatching(".*/ng5xFhmBC33"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/londonNewYear.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    wireMockServer.stubFor(get(urlMatching(".*/mlMWvMxfHXq"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/scottishHighlights.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    wireMockServer.stubFor(get(urlMatching(".*/ng5w7ZGDGzZ"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/summerGrandBritish.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

  }

  // driver class
  def main(args : Array[String]) {

    // port, canned file directory, swagger file, state model file, opening state
    MyStub.configureStub(args(0), args(1), args(2), args(3), "any")
    MyStub.start
  }
}