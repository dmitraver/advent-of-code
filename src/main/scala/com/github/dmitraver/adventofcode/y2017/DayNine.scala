package com.github.dmitraver.adventofcode.y2017

import atto.Atto._
import atto._
import com.github.dmitraver.adventofcode.utils.ResourceLoader

sealed trait Content
case class Group(groups: List[Content]) extends Content
case class Garbage(length: Int) extends Content

object DayNine {

  val garbageParser: Parser[Content] = (char('<') ~> many((char('!') ~> anyChar).map(_ => 0) | noneOf("!>").map(_ => 1)) <~ char('>')).map(vals => Garbage(vals.sum))
  val groupParser: Parser[Content] = (char('{') ~> sepBy(garbageParser | groupParser, char(',')) <~ char('}')).map(input => Group(input))

  def totalScore(content: Content): Int = {
    def go(level: Int, root: Group): Int = {
      val groups = root.groups
      if (groups.isEmpty) level
      else {
        val sum = groups.foldLeft(0) { (acc, g) =>
          g match {
            case Garbage(_) => acc
            case g @ Group(_) => acc + go(level + 1, g)
          }
        }

        sum + level
      }
    }

    content match {
      case Garbage(_) => 0
      case g @ Group(_) => go(1, g)
    }
  }

  def totalGarbageLength(content: Content): Int = content match {
    case Garbage(length) => length
    case Group(contents) => contents.foldLeft(0) { (acc, next) =>
      next match {
        case Garbage(length) => acc + length
        case g @ Group(_) => acc + totalGarbageLength(g)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day9").mkString
    val parsed = groupParser.parse(input).option.get
    println("Total score:" + totalScore(parsed))
    println("Total garbage length:" + totalGarbageLength(parsed))
  }
}
