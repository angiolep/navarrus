
lazy val root = (project in file(".")).
  settings(
    organization := "bitspoke",
    name := "navarrus",
    version := "1.0",
    scalaVersion := "2.11.7"
  )

scalacOptions ++= Seq(
  "-feature",
  "-language:implicitConversions"
)

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)
