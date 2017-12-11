package com.github.dmitraver.adventofcode.y2017

import atto._
import Atto._
import cats.implicits._

import scala.util.matching.Regex.Groups

sealed trait Content
case class Group(groups: List[Content]) extends Content
case class Garbage(input: List[Char]) extends Content

object DayNine {

  val content  = garbage | group
  val garbage = (char('<') ~> many((char('!') ~> anyChar) | noneOf("!>")) <~ char('>')).map(a => List(a.toString()))
  def group: Parser[List[String]] = char('{') ~> sepBy(garbage | group, char(',')) <~ char('}')


  def main(args: Array[String]): Unit = {
    //println(char('<').flatMap(i => many(char('c'))).flatMap(_ => char('>')).parseOnly("<ccccc>").option)
    //println(many(anyChar).parseOnly("<agge>").option)
    println(garbage.parseOnly("<!!!>>").option)
    println(group.parse("{{<a!>},{<a!>},{<a!>},{<ab>}}").option)
  }
}
