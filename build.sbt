import com.lihaoyi.workbench.Plugin._

// Turn this project into a Scala.js project by importing these settings
enablePlugins(ScalaJSPlugin)

// Twirl import setting
lazy val root = (project in file(".")).enablePlugins(SbtTwirl)

name := "minMaxTicTacToe"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"


persistLauncher in Compile := true

persistLauncher in Test := false

testFrameworks += new TestFramework("utest.runner.Framework")

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.0",
  "be.doeraene" %%% "scalajs-jquery" % "0.9.0",
  "com.lihaoyi" %%% "utest" % "0.4.3"
)

workbenchSettings

bootSnippet := "client.Board().main();"

refreshBrowsers <<= refreshBrowsers.triggeredBy(fastOptJS in Compile)

testFrameworks += new TestFramework("utest.runner.Framework")

artifactPath in (Compile, fullOptJS) := file("bin/app.js")
