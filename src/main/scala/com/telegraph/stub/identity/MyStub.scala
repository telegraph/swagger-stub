package com.telegraph.stub.identity

import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.stubbing.Scenario

import scala.io.Source

/**
  * Created by toorap on 17/08/2017.
  * Example of a stub encapsulating contract and state
  */
object MyStub extends BaseStub {


  // map of action -> map  of pre-state, post-state
  override var stateTransitions =    Map(
    "post" -> Map(Scenario.STARTED -> Scenario.STARTED))


  override def setUpMocks(cannedResponsesPath: String): Unit  = {

    // happy path get
    stateTransitions("post") foreach {

      case (inState, outState) =>
        wireMockServer.stubFor(post(urlMatching(".*/tokens"))
          .withRequestBody(equalToJson("{\"grant_type\":\"password\"}",true,true))
          .inScenario("state")
          .whenScenarioStateIs(inState)
          .withPostServeAction("validate-contract",new ContractValidationParameter("validate-contract"))
          .willReturn(
            aResponse()
              .withHeader("Content-Type", "application/json")
              .withBody(
                "{"+
                  "\"access_token\": \"MUpTUzcvMHVGUDQ1dDVsQnNQcWNNNEhHdWhzUm1OamdaZmExVmM5RzArak16VGRtR0xrM29BRjZKY1dCRGxibEg2Y01VaVJLNDZBPQ\","+
                  "\"token_type\": \"Bearer\","+
                  "\"refresh_token\": \"MUpTUzcvMHVGUDQ1dDVsQnNQcWNNNEhHdWhzUm1OamdaZmExVmM5RzArak16VGRtR0xrM29BRjZKY1dCRGxibEg2Y01VaVJLNDZBPQ\","+
                  "\"expires_in\": 3600,"+
                  "\"id_token\": \"eyJhbGciOiJub25lIn0.ewoJInByb2ZpbGUiOiB7CgkJInBJZCI6ICJiODFlYjM2Mi05NWQ4LTQ4YzAtOTY4MS1hOGJiZTg1Y2UxNjYiLAoJCSJjb21tZW50VG9rZW4iOiAiZXlKMWMyVnlibUZ0WlNJNkltMW5ZWEpuTmlJc0ltbGtJam9pTWpNME1qTTBNekkwTFRGbE1USXlNV1V4TWkweE1tVXhNbVV4TW1VdE1qRmxNVEpsTVRKbElpd2lZWFpoZEdGeUlqb2laSGRqZDJOM1pTSXNJblZ5YkNJNkltaDBkSEE2THk5dGVTNTBaV3hsWjNKaGNHZ3VZMjh1ZFdzdmJXVnRZbVZ5Y3k5dFoyRnlaellpZlE9PSBhYzEzNjRkOTIwNjE5NWRlZGYxNmYzODIxNjE1MjA2NDc3MWQ5ZWE5IDEzODA3MDYzODIiLAoJCSJmaXJzdE5hbWUiOiAiU2ltb24iLAoJCSJsYXN0TmFtZSI6ICJCYXluZXMiLAoJCSJ1c2VybmFtZSI6ICJleGFtcGxlIiwKCQkiZW1haWwiOiAiZXhhbXBsZUBnbWFpbC5jb20iLAoJCSJyZWFkZXJUeXBlIjogInN1YnNjcmliZXIiLCAvLyBpdCBjb3VsZCBiZSBhbm9ueW1vdXMsIHJlZ2lzdGVyZWQgb3Igc3Vic2NyaWJlcgoJCSJwZXJtaXNzaW9ucyI6IHsKCQkJImFjY2Vzc1dlYiI6IHRydWUsCgkJCSJhY2Nlc3NTbWFydHBob25lQXBwcyI6IHRydWUsCgkJCSJhY2Nlc3NUYWJsZXRBcHBzIjogdHJ1ZSwKCQkJImNhblBvc3RDb21tZW50cyI6IGZhbHNlLAoJCQkiYWNjZXNzTG95YWx0eSI6IHRydWUsCgkJCSJjYW5TdWJzY3JpYmUiOiBmYWxzZQoJCX0KCX0sCgkic2Vzc2lvbiI6IHsKCQkiaWQiOiAiMTYzOTViOTAtMWJjOC00ZmQ2LThhNjYtNzBhNmNhMDUxZjk1IiwKCQkic2VjdXJlSWQiOiAiNmE0MzljNWItNzljNi00NGU5LTlhYmQtZDJmN2IwYWUzMmQyIgoJfSwKCSJhbmFseXRpY3MiOiB7CgkJCSJsb2NhdGlvbiI6ICIiLAoJCQkidHJpYWxEZXNjcmlwdGlvbiI6ICIiLAoJCQkic3Vic2NyaXB0aW9uVHlwZSI6ICIiLAoJCQkiZmxvd1R5cGUiOiAiIiwKCQkJInByb2R1Y3RUeXBlIjogIiIsCgkJCSJ0b2tlblNvdXJjZSI6ICIiLAoJCQkidG9rZW5WYWx1ZSI6ICIiLAoJCQkiZGlzY291bnRWb3VjaGVyIjogIiIsCgkJCSJjdXJyZW50Q29tcGFpZ24iOiAib3BlbiBvZmZlciIsCgkJCSJzb3VyY2VDb21wYWlnbiI6ICJvcGVuIG9mZmVyIiwKCQkJInByb21vVHlwZSI6ICIiLAoJCQkidENvZGUiOiAiIiwKCQkJInN0YXR1cyI6IGZhbHNlLAoJCQkiYWNxdWlzaXRpb25Db21wbGV0ZWQiOiBmYWxzZSwKCQkJInByaWNlIjogIiIsCgkJCSJwcmljZURlc2NyaXB0aW9uIjogIiIsCgkJCSJzZXJ2aWNlSWQiOiAiIiwKCQkJInNlcnZpY2VQcmljZUlkIjogIiIsCgkJCSJkeW5hbWljUHJpY2UiOiAiQkxBTksiLAoJCQkicGF5bWVudFJlcXVpcmVkIjogZmFsc2UKCX0KfQ.\""+
                "}"
                )
              .withStatus(201))
          .willSetStateTo(outState)
        )
    }
  }

  // driver class
  def main(args : Array[String]) {

    // passed swagger file, port, canned responses file path
    MyStub.configureStub(args(0), args(1).toInt, args(2))
    MyStub.start
  }
}