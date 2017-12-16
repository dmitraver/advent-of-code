package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.annotation.tailrec

sealed trait Move
case class SpinMove(x: Int) extends Move
case class ExchangeMove(x: Int, y: Int) extends Move
case class PartnerMove(a: Char, b: Char) extends Move

object DaySixteen {

  val SpinMovePattern = "s(\\w+)".r
  val ExchangeMovePattern = "x(\\d+)/(\\d+)".r
  val PartnerMovePattern = "p(\\w)/(\\w)".r

  def dance(initial: String, moves: Vector[Move]): String = {
    moves.foldLeft(initial) { (result, move) =>
      move match {
        case SpinMove(x) => spin(result, x)
        case ExchangeMove(x, y) => exchange(result, x, y)
        case PartnerMove(a, b)  => partner(result, a, b)
      }
    }
  }

  def parseMoves(moves: Vector[String]): Vector[Move] = {
    moves.map {
      case SpinMovePattern(x) => SpinMove(x.toInt)
      case ExchangeMovePattern(x, y) => ExchangeMove(x.toInt, y.toInt)
      case PartnerMovePattern(a, b)  => PartnerMove(a.head, b.head)
    }
  }

  private def spin(input: String, moves: Int): String = {
    input.takeRight(moves) + input.take(input.length - moves)
  }

  private def exchange(input: String, posA: Int, posB: Int) = {
    val x = input.charAt(posA)
    input.updated(posA, input.charAt(posB)).updated(posB, x)
  }

  private def partner(input: String, programA: Char, programB: Char) = {
    val indexA = input.indexOf(programA)
    val indexB = input.indexOf(programB)
    exchange(input, indexA, indexB)
  }

  @tailrec
  def danceNTimes(n: Int, input: String, moves: Vector[Move]): String = {
    if (n == 0) input
    else danceNTimes(n - 1, dance(input, moves), moves)
  }

  def main(args: Array[String]): Unit = {
    val moves = parseMoves(ResourceLoader.fromResource("y2017/day16").getLines().mkString.split(",").toVector)
    val input = Vector.range('a', 'q').mkString
    println("After dance: " + dance(input, moves))
    println("Dance 1000000000 times: " + danceNTimes(100, input, moves))
  }
}
