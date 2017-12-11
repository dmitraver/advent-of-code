package com.github.dmitraver.adventofcode.y2017

import atto._
import Atto._
import cats.implicits._
import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.util.matching.Regex.Groups

sealed trait Content
case class Group(groups: List[Content]) extends Content
object Garbage extends Content {
  override def toString = "Garbage"
}

object DayNine {

  def garbage: Parser[Content] = (char('<') ~> many((char('!') ~> anyChar) | noneOf("!>")) <~ char('>')).map(_ => Garbage)
  def group: Parser[Content] = (char('{') ~> sepBy(garbage | group, char(',')) <~ char('}')).map(input => Group(input))

  private def totalScore(content: Content): Int = {
    def go(level: Int, g: Group): Int = {
      println("level:" + level)
      val groups = g.groups
      if (groups.isEmpty) level
      else {
        val sum = groups.foldLeft(0) { (acc, g) =>
          g match {
            case Garbage => acc
            case g@Group(_) => acc + level
          }
        }

        sum + groups.foldLeft(0) {  (acc, g) =>
          g match {
            case Garbage => acc
            case g@Group(_) => acc + go(level + 1, g)
          }
        }
      }
    }

    content match {
      case Garbage => 0
      case g @ Group(_) => go(1, g)
    }
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day9").mkString
    val parsed = group.parse("{{{},{},{{}}}}").option.get
    println(parsed)
    println(totalScore(parsed))
  }
}
