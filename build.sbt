import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies ++= Seq(
      "org.scalatest" % "scalatest_2.10" % "2.0",
      "com.github.tomakehurst" % "wiremock" % "2.7.1",
      "com.atlassian.oai" % "swagger-request-validator-wiremock" % "1.2.1"
    )
  )
