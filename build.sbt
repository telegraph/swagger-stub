import Dependencies._

resolvers += "mvn-artifacts" at "s3://mvn-artifacts/snapshot"

lazy val root = (project in file(".")).
  settings( 
    inThisBuild(List(
      organization := "com.telegraph.stub.identity",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "IdentityStub",
    ServiceDependencies
  )

mainClass := Some("com.telegraph.stub.identity.MyStub")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}