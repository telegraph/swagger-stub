import scala.collection.JavaConversions._
import Dependencies._

resolvers += "mvn-artifacts" at "s3://s3-eu-west-1.amazonaws.com/mvn-artifacts/release"

lazy val root = (project in file(".")).
  settings( 
    inThisBuild(List(
      organization := "com.telegraph.stub."+ System.getProperty("stubName"),
      scalaVersion := "2.11.8",
      version      := System.getProperty("verNumber")
    )),
    name := System.getProperty("stubName"),
    ServiceDependencies
  )

mainClass := Some("com.telegraph.stub."+ System.getProperty("stubName") +".MyStub")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}


publishTo := {
  if( isSnapshot.value ){
    Some("mvn-tmg-publisher" at "s3://s3-eu-west-1.amazonaws.com/mvn-artifacts/snapshot")
  }else{
    Some("mvn-tmg-publisher" at "s3://s3-eu-west-1.amazonaws.com/mvn-artifacts/release")
  }
}
