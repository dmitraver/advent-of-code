package com.github.dmitraver.adventofcode.y2018

import com.github.dmitraver.adventofcode.shared.Coordinate
import com.github.dmitraver.adventofcode.utils.ResourceLoader

object Day6 {

  private val CoordinateRegexp = "(\\d+), (\\d+)".r

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day6").getLines().toVector
    println("Biggest area: " + partOne(input))
  }

  def partOne(input: Vector[String]): Int = {
    val coordinates = parseInput(input)
    val x = coordinates.maxBy(_.x).x
    val y = coordinates.maxBy(_.y).y
    val max = Math.max(x, y)

    val grid = for {
      y <- 0 to max
      x <- 0 to max
    } yield Coordinate(x, y)


    val closestToCoordinates = grid.map(c => closestCoordinate(c, coordinates)).filter(_.isDefined).map(_.get).groupBy(c => c._1).map(r => r._1 -> r._2.map(_._2))
    val finiteDistance = closestToCoordinates.filterNot(_._2.exists(c => isOnBorder(c, max)))
    finiteDistance.map(_._2.size).max
  }

  private def isOnBorder(c: Coordinate, max: Int): Boolean = {
    c.x == 0 || c.y == 0 || c.x == max || c.y == max
  }

  private def closestCoordinate(a: Coordinate, coordinates: Vector[Coordinate]): Option[(Coordinate, Coordinate)] = {
    val distances = coordinates.map(c => (manhattanDistance(a, c), c, a))
    val min = distances.minBy(_._1)
    if (distances.count(_._1 == min._1) == 1) Some((min._2, min._3))
    else None
  }

  private def manhattanDistance(a: Coordinate, b: Coordinate): Int = {
    Math.abs(a.x - b.x) + Math.abs(a.y - b.y)
  }

  private def parseInput(input: Vector[String]): Vector[Coordinate] = {
    input.map {
      case CoordinateRegexp(x, y) => Coordinate(x.toInt, y.toInt)
    }
  }
}
