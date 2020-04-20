name := "zio_demo"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.1"

val zioVersion = "1.0.0-RC18-2"
val http4sVersion = "0.21.2"

libraryDependencies ++= Seq(
  "dev.zio"    %% "zio"                 % zioVersion,

  "dev.zio"    %% "zio-interop-cats"    % "2.0.0.0-RC12",

  "org.http4s" %% "http4s-dsl"          % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,

  "org.slf4j" % "slf4j-log4j12" % "1.7.26"
)
