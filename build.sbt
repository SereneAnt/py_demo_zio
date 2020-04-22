name := "zio_demo"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.1"

val zioVersion = "1.0.0-RC18-2"

libraryDependencies ++= Seq(
  "dev.zio"    %% "zio"   % zioVersion,

  "com.lihaoyi" %% "cask" % "0.6.0",

//  "org.slf4j" % "slf4j-log4j12" % "1.7.26"
)
