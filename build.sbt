name := "scope-demo-akka-scala"

version := "0.1"

scalaVersion := "2.12.10"

resolvers += Resolver.mavenLocal

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.25"
libraryDependencies += "com.squareup.okhttp3" % "okhttp" % "3.14.3"
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.2" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.25" % Test

lazy val distProject = project
  .in(file("."))
  .enablePlugins(JavaAgent)
  .settings(
    javaAgents += "com.undefinedlabs.scope" % "scope-agent" % "0.2.1-beta.4" % "test"
  )
