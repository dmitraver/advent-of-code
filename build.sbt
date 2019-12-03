name := "AdventOfCode"

version := "1.0"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "commons-codec" % "commons-codec" % "1.11",
  "com.typesafe.play" %% "play-json" % "2.6.11",

  // TODO: replace with my own parser combinator library
  "org.tpolecat"  %% "atto-core"  % "0.6.4",
  "org.typelevel" %% "cats-core" % "1.0.0",
  "co.fs2"        %% "fs2-core"  % "1.0.0"

)
    