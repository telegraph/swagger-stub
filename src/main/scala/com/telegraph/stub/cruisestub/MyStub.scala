package com.telegraph.stub.cruisestub

import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.extension.Parameters
import com.github.tomakehurst.wiremock.http.Request
import uk.co.telegraph.qe.SmartStub

import scala.io.Source

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

    wireMockServer.stubFor(get(urlMatching(".*/ng5xKs6Mz5C"))
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

    wireMockServer.stubFor(get(urlMatching(".*/cruises?flakeIds=ng5xKjcstfs,mlMWvMk8tGh,ng5xFhmBC33,mlMWvMxfHXq,ng5w8LfZ8Wl,ng5w7ZGDGzZ,mlMWvMdxpST,ng5xKs6Mz5C,pClvcx3M5N4"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody("[" + Source.fromFile(cannedResponsesPath+ "/britishIslesDiscovery.json").mkString+","
            +Source.fromFile(cannedResponsesPath+ "/easterSpring.json").mkString+","
            +Source.fromFile(cannedResponsesPath+ "/londonNewYear.json").mkString+","
            +Source.fromFile(cannedResponsesPath+ "/scottishHighlights.json").mkString+","
            +Source.fromFile(cannedResponsesPath+ "/britishDiscoveryAisle.json").mkString+","
            +Source.fromFile(cannedResponsesPath+ "/summerGrandBritish.json").mkString+","
            +Source.fromFile(cannedResponsesPath+ "/icelandCruise.json").mkString+","
            +Source.fromFile(cannedResponsesPath+ "/grandScottishLochs.json").mkString+","
            +Source.fromFile(cannedResponsesPath+ "/britishAisle.json").mkString+"]"
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))
  }

  // driver class
  def main(args : Array[String]) {
    Thinig.thing(wireMockServer, "")
    // port, canned file directory, swagger file, state model file, opening state
    MyStub.configureStub(args(0), args(1), args(2), args(3), "any")
    MyStub.start
  }
}