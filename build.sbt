name := "AdventOfCode"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "commons-codec" % "commons-codec" % "1.10",
  "com.typesafe.play" % "play-json_2.11" % "2.4.6",

  // TODO: replace with my own parser combinator library
  "org.tpolecat" %% "atto-core"  % "0.6.0",
  "org.typelevel" %% "cats-core" % "1.0.0-RC1"
)
    