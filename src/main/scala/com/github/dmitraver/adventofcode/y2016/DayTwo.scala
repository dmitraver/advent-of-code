package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.io.Source


object DayTwo {

  private val keypad = Array(
    Array(7, 8, 9),
    Array(4, 5, 6),
    Array(1, 2, 3))

  def main(args: Array[String]): Unit = {
    val source = ResourceLoader.fromResource("y2016/day2")
    println("Bathroom code is: " + getBathroomCode(source))
  }

  def getBathroomCode(source: Source) = {
    def getBathroomCode(instructions: List[String], code: List[Int] = List(), initialCoordinate: Coordinate = Coordinate(1, 1)): List[Int] = instructions match {
      case Nil => code
      case head :: tail =>
        val lastCoordinate = head.foldLeft(initialCoordinate) { (previous: Coordinate, direction: Char) =>
          var nextCoordinate = previous.move(direction)
          if (!nextCoordinate.withinRange(2, 2)) {
            nextCoordinate = previous
          }

          nextCoordinate
        }

        getBathroomCode(tail, code :+ keypad(lastCoordinate.y)(lastCoordinate.x), lastCoordinate)
    }

    getBathroomCode(source.getLines().toList)
  }


}

case class Coordinate(x: Int, y: Int) {
  def move(direction: Char) = direction match {
      case 'U' => up()
      case 'D' => down()
      case 'R' => right()
      case 'L' => left()
  }

  def withinRange(xRange: Int, yRange: Int) = {
    this.x >= 0 && this.x <= xRange && this.y >= 0 && this.y <= yRange
  }

  def up() = copy(x, y + 1)
  def right() = copy(x + 1, y)
  def left() = copy(x - 1, y)
  def down() = copy(x, y - 1)
}
