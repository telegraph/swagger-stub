import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.telegraph.stub.identity",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "IdentityStub",
    libraryDependencies ++= Seq(
      "com.github.tomakehurst" % "wiremock" % "2.7.1",
      "com.atlassian.oai" % "swagger-request-validator-wiremock" % "1.2.1"
    )
  )

mainClass := Some("com.telegraph.stub.identity.MyStub")
