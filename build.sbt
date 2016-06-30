name := """all-polls"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  evolutions,
  "org.hibernate" % "hibernate-entitymanager" % "4.2.12.Final",
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc41",
  "com.google.code.gson" % "gson" % "2.2.2"
)

fork in run := false

PlayKeys.externalizeResources := false