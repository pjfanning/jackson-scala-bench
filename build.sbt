ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

ThisBuild / crossScalaVersions := Seq("2.13.10", "3.2.2")

enablePlugins(JmhPlugin)

lazy val root = (project in file("."))
  .settings(
    name := "jackson-scala-bench",
    idePackagePrefix := Some("com.github.pjfanning.jackson.scala"),
    libraryDependencies ++= Seq(
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.2",
      "org.scalatest" %% "scalatest" % "3.2.15" % Test
    )
  )
