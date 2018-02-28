package com.telegraph.stub.productlistingstub

import com.github.tomakehurst.wiremock.client.WireMock._
import uk.co.telegraph.qe.SmartStub
import scala.io.Source

/**
  * Created by toorap on 17/08/2017.
  * Example of a stub encapsulating contract and state
  */
object SearchAPIStub extends SmartStub {


  override def setUpMocks(cannedResponsesPath: String): Unit  = {

    wireMockServer.stubFor(get(urlMatching(".*/?annotation=http://data.telegraph.co.uk/resource/kJv4VN1SDLx&annotation=http://data.telegraph.co.uk/ontologies/travel/Cruise&limit=10&offset=0"))
      .willReturn(
        aResponse()
          .withTransformerParameter("nextState", "any")
          .withHeader("Content-Type", "application/json")
          .withBody(Source.fromFile(cannedResponsesPath+ "/unitedKingdomCruiseListing.json").mkString
            .replace("&apos;"," ").replace("&quot;","'"))
          .withStatus(200)))

  }

  // driver class
  def main(args : Array[String]) {

    // port, canned file directory, swagger file, state model file, opening state
    SearchAPIStub.configureStub(args(0), args(1), args(2), args(3), "any")
    SearchAPIStub.start
  }
}
