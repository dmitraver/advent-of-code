package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.io.Source

object DayOne {

  def main(args: Array[String]): Unit = {
    val sourceOne = ResourceLoader.fromResource("y2016/day1")
    println("Distance is: " + easterBunnyHQDistance(sourceOne))
  }

  def easterBunnyHQDistance(source: Source) = {
    val finalCoordinate = source.getLines().flatMap(s => s.split(", ")).foldLeft(Coordinate(North(), 0, 0)) ((coordinate, nextMove) => coordinate.move(nextMove(0), nextMove.substring(1).toInt))
    Math.abs(finalCoordinate.x) + Math.abs(finalCoordinate.y)
  }
}

sealed trait Direction
case class North() extends Direction
case class South() extends Direction
case class West() extends Direction
case class East() extends Direction

case class Coordinate(direction: Direction, x: Int, y: Int) {

  def move(c: Char, distance: Int) = c match {
      case 'R' =>
        direction match {
          case North() => copy(East(), x + distance, y)
          case South() => copy(West(), x - distance, y)
          case West() => copy(North(), x, y + distance)
          case East() => copy(South(), x, y - distance)
        }
      case 'L' => direction match {
        case North() => copy(West(), x - distance, y)
        case South() => copy(East(), x + distance, y)
        case West() => copy(South(), x, y - distance)
        case East() => copy(North(), x, y + distance)
      }
  }
}
