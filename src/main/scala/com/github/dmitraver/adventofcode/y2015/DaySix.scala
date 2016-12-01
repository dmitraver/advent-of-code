package com.github.dmitraver.adventofcode.y2015

import com.github.dmitraver.adventofcode.shared.Coordinate
import com.github.dmitraver.adventofcode.utils.ResourceLoader

case class Grid(width: Int, height: Int) {

  private val grid = Array.ofDim[Boolean](width, height)

  def toggle(left: Coordinate, top: Coordinate) = {
    for {
      i <- left.x to top.x
      j <- left.y to top.y
    } grid(i)(j) = !grid(i)(j)
  }

  def turnOff(left: Coordinate, top: Coordinate) = {
    for {
      i <- left.x to top.x
      j <- left.y to top.y
    } grid(i)(j) = false
  }

  def turnOn(left: Coordinate, top: Coordinate) = {
    for {
      i <- left.x to top.x
      j <- left.y to top.y
    } grid(i)(j) = true
  }

  def countLitLights() = {
    grid.flatten.count(_ == true)
  }
}

object DaySix {

  val numbersPattern = "(\\d+)".r

  def main(args: Array[String]) {
    val grid = Grid(1000, 1000)
    ResourceLoader.fromResource("y2015/day6").getLines().foreach { str =>
      val numbers = numbersPattern.findAllIn(str).toList.map(_.toInt)
      val left = Coordinate(numbers(0), numbers(1))
      val top = Coordinate(numbers(2), numbers(3))

      if (str startsWith "toggle") grid.toggle(left, top)
      else if (str startsWith "turn on") grid.turnOn(left, top)
      else grid.turnOff(left, top)
    }

    println("Lights: " + grid.countLitLights())

  }


}
