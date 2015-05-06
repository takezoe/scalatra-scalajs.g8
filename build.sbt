import org.scalatra.sbt._
//import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Project.projectToRef

//val Organization = "organization"
//val Name = "name"
//val Version = "0.0.1"
lazy val ScalaVersion = "2.11.6"
lazy val ScalatraVersion = "2.3.1"

lazy val common = (crossProject.crossType(CrossType.Pure) in file("common"))
.settings(scalaVersion := ScalaVersion)

lazy val commonJvm = common.jvm
lazy val commonJs  = common.js

lazy val client = (project in file("client")).settings(
  scalaVersion := ScalaVersion,
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.8.0",
    "com.lihaoyi" %%% "scalatags" % "0.5.1",
    "com.lihaoyi" %%% "scalarx" % "0.2.8",
    "be.doeraene" %%% "scalajs-jquery" % "0.8.0",
    "com.lihaoyi" %%% "upickle" % "0.2.8"
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
    resolvers ++= Seq(
      Classpaths.typesafeReleases,
      "amateras-repo" at "http://amateras.sourceforge.jp/mvn/"
    ),
    scalacOptions := Seq("-deprecation", "-language:postfixOps"),
    libraryDependencies ++= Seq(
      "org.scalatra" %% "scalatra" % ScalatraVersion,
      "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
      "org.scalatra" %% "scalatra-json" % ScalatraVersion,
      "org.json4s" %% "json4s-jackson" % "3.2.11",
      "jp.sf.amateras" %% "scalatra-forms" % "0.1.0",
      "com.typesafe.slick" %% "slick" % "3.0.0",
      "org.eclipse.jetty" % "jetty-webapp" % "8.1.16.v20140903" % "container;provided",
      "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts Artifact("javax.servlet", "jar", "jar")
    ),
    EclipseKeys.withSource := true,
    javacOptions in compile ++= Seq("-target", "8", "-source", "8"),
    testOptions in Test += Tests.Argument(TestFrameworks.Specs2, "junitxml", "console"),
    packageOptions += Package.MainClass("JettyLauncher")
)
.enablePlugins(SbtTwirl)
//.aggregate(client)
.dependsOn(commonJvm)
