package com.github.dmitraver.adventofcode.y2018

import com.github.dmitraver.adventofcode.utils.ResourceLoader
import com.github.dmitraver.adventofcode.y2016.Coordinate
import com.github.dmitraver.adventofcode.utils.extensions._

object Day3 {

  val ClaimRegexp = "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)".r

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day3").getLines().toVector
    println("How many square inches of fabric are within two or more claims: " + partOne(input))
  }

  private def parseInput(input: Vector[String]): Vector[Claim] = {
    input map {
      case ClaimRegexp(id, leftMargin, topMargin, width, tall) =>
        Claim(id.toInt, leftMargin.toInt, topMargin.toInt, width.toInt, tall.toInt)
    }
  }

  def partOne(input: Vector[String]): Int = {
    val claims = parseInput(input)
    val result = claims.foldLeft(Map.empty[Coordinate, Set[Int]]) { (acc, claim) =>
      val cs = coordinates(claim)
      cs.foldLeft(acc)((a, c) => a.adjust(c, Some(Set(claim.id)))(claims => claims + claim.id))
    }

    result.count(_._2.size >= 2)
  }

  def coordinates(claim: Claim): Vector[Coordinate] = {
    val result = for {
      x <- claim.leftMargin until claim.leftMargin + claim.width
      y <- claim.topMargin until claim.topMargin + claim.tall
    } yield Coordinate(x, y)

    result.toVector
  }


}

case class Claim(id: Int, leftMargin: Int, topMargin: Int, width: Int, tall: Int)
