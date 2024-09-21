ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

ThisBuild / crossScalaVersions := Seq("2.13.14", "3.3.3")

enablePlugins(JmhPlugin)

lazy val root = (project in file("."))
  .settings(
    name := "jackson-scala-bench",
    idePackagePrefix := Some("com.github.pjfanning.jackson.scala"),
    libraryDependencies ++= Seq(
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.2",
      "org.scalatest" %% "scalatest" % "3.2.19" % Test
    )
  )
