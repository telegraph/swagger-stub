import sbt.Keys._
import sbt._

object Dependencies {

  val ServiceDependencies: Seq[Setting[_]] = Seq(
    libraryDependencies ++= Seq(
      "uk.co.telegraph.qe" % "smartstub_2.11" % "0.10.0"
    )
  )
}
