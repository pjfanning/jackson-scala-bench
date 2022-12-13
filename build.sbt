ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

enablePlugins(JmhPlugin)

lazy val root = (project in file("."))
  .settings(
    name := "jackson-scala-bench",
    idePackagePrefix := Some("com.github.pjfanning.jackson.scala"),
    libraryDependencies ++= Seq(
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.1",
      "org.scalatest" %% "scalatest" % "3.2.14" % Test
    )
  )
