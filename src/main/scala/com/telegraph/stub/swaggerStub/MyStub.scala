package com.telegraph.stub.swaggerStub

import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.extension.Parameters
import com.github.tomakehurst.wiremock.http.Request
import uk.co.telegraph.qe.SmartStub

import scala.io.Source

object MyStub extends SmartStub {

  override def setUpMocks(cannedResponsesPath: String): Unit  = {
  }

  // driver class
  def main(args : Array[String]) {
    // port, swagger file, mappings
    MyStub.configureStubWithOnlySwaggerAndMappings(args(0), args(1), args(2))
    MyStub.start
  }
}