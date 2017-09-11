import Dependencies._

resolvers += "mvn-artifacts" at "s3://mvn-artifacts/snapshot"

lazy val root = (project in file(".")).
  settings( 
    inThisBuild(List(
      organization := "com.telegraph.stub.facebookauth",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "facebookauthStub",
    ServiceDependencies
  )

mainClass := Some("com.telegraph.stub.facebookauth.MyStub")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}