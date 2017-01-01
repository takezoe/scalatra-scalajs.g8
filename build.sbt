import org.scalatra.sbt._
import sbt.Project.projectToRef

//val Organization = "organization"
//val Name = "name"
//val Version = "0.0.1"
lazy val ScalaVersion = "2.12.1"
lazy val ScalatraVersion = "2.5.0"

lazy val common = (crossProject.crossType(CrossType.Pure) in file("common"))
.settings(scalaVersion := ScalaVersion)

lazy val commonJvm = common.jvm
lazy val commonJs  = common.js

lazy val client = (project in file("client")).settings(
  scalaVersion := ScalaVersion,
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.1",
    "com.lihaoyi" %%% "scalatags" % "0.6.2",
    "com.lihaoyi" %%% "scalarx" % "0.3.2",
    "be.doeraene" %%% "scalajs-jquery" % "0.9.1",
    "com.lihaoyi" %%% "upickle" % "0.4.4"
  ),
  scalaJSStage in Global := FastOptStage,
  skip in packageJSDependencies := false
)
.settings(
  Seq(fastOptJS, fullOptJS) map { packageJSKey =>
    crossTarget in (Compile, packageJSKey) := (baseDirectory in server).value / "src/main/webapp/assets/js"
  }
)
.enablePlugins(ScalaJSPlugin)
.dependsOn(commonJs)

lazy val server = (project in file("server"))
.settings(ScalatraPlugin.scalatraWithJRebel: _*)
.settings(
    sourcesInBase := false,
    //organization := Organization,
    //name := Name,
    //version := Version,
    scalaVersion := ScalaVersion,
    scalacOptions := Seq("-deprecation", "-language:postfixOps"),
    libraryDependencies ++= Seq(
      "org.scalatra" %% "scalatra" % ScalatraVersion,
      "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
      "org.scalatra" %% "scalatra-json" % ScalatraVersion,
      "org.json4s" %% "json4s-jackson" % "3.5.0",
      "io.github.gitbucket" %% "scalatra-forms" % "1.1.0",
      "com.typesafe.slick" %% "slick" % "3.2.0-M2",
      "org.eclipse.jetty" % "jetty-webapp" % "9.3.9.v20160517" % "container;provided",
      "javax.servlet" % "javax.servlet-api" % "3.1.0" % "container;provided;test"
    ),
    javacOptions in compile ++= Seq("-target", "8", "-source", "8"),
    testOptions in Test += Tests.Argument(TestFrameworks.Specs2, "junitxml", "console"),
    packageOptions += Package.MainClass("JettyLauncher")
)
.enablePlugins(SbtTwirl, JettyPlugin)
.dependsOn(commonJvm)

addCommandAlias("compileAll", "; server/compile; client/fastOptJS")
