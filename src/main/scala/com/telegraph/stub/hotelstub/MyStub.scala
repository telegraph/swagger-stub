package com.telegraph.stub.hotelstub

import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.extension.Parameters
import com.github.tomakehurst.wiremock.http.Request
import uk.co.telegraph.qe.SmartStub

import scala.io.Source

object MyStub extends SmartStub {

  override def setUpMocks(cannedResponsesPath: String): Unit  = {

    wireMockServer.stubFor(get(urlMatching(".*/kfxl6S6C32l"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/hiltonLuxorResort.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    wireMockServer.stubFor(get(urlMatching(".*/k3kWmhGH9Sm"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/hiltonLuxorResort.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

    wireMockServer.stubFor(get(urlMatching(".*/kfxl6SzzYPB"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/hiltonLuxorResort.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))


    wireMockServer.stubFor(get(urlMatching(".*/mkbj9GHrC76"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/hiltonLuxorResort.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))


    wireMockServer.stubFor(get(urlMatching(".*/mbY85y0JBL2"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/hiltonLuxorResort.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))





  }

  // driver class
  def main(args : Array[String]) {
    // port, canned file directory, swagger file, state model file, opening state
    //java -jar target/scala-2.11/hotelstub-assembly-0.0.3.jar "8081" "/Users/rudrabhatlav/Downloads/cruise-stub-master/src/main/resources/cannedJson" "/Users/rudrabhatlav/Downloads/cruise-stub-master/src/main/resources/contract/openApi.json" "/Users/rudrabhatlav/Downloads/cruise-stub-master/src/main/resources/contract/stateModel.json" "any"
    MyStub.configureStub(args(0), args(1), args(2), args(3), "any")
    MyStub.start
  }
}