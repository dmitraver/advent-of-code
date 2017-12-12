package com.github.dmitraver.adventofcode.y2017

import atto.Atto._
import atto._
import com.github.dmitraver.adventofcode.utils.ResourceLoader

sealed trait Content
case class Group(groups: List[Content]) extends Content
object Garbage extends Content

object DayNine {

  val garbageParser: Parser[Content] = (char('<') ~> many((char('!') ~> anyChar) | noneOf("!>")) <~ char('>')).map(_ => Garbage)
  val groupParser: Parser[Content] = (char('{') ~> sepBy(garbageParser | groupParser, char(',')) <~ char('}')).map(input => Group(input))

  def totalScore(content: Content): Int = {
    def go(level: Int, root: Group): Int = {
      val groups = root.groups
      if (groups.isEmpty) level
      else {
        val sum = groups.foldLeft(0) { (acc, g) =>
          g match {
            case Garbage => acc
            case g @ Group(_) => acc + go(level + 1, g)
          }
        }

        sum + level
      }
    }

    content match {
      case Garbage => 0
      case g @ Group(_) => go(1, g)
    }
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day9").mkString
    val parsed = groupParser.parse(input).option.get
    println(totalScore(parsed))
  }
}
