package com.github.dmitraver.adventofcode.y2018

object Day11 {

  def main(args: Array[String]): Unit = {
    println("Part one: " + partOne(3214))
  }

  def partOne(serialNumber: Int): (Int, Int) = {
    val grid = generateGrid(serialNumber, 300, 300)
    val coordinateWithPower = for {
      y <- 0 until 297
      x <- 0 until 297
    } yield ((y,x), calculateTotalPower(grid, (y, x)))

    val ((x,y), _) = coordinateWithPower.maxBy(_._2)
    (x + 1, y + 1)
  }

  private def generateGrid(serialNumber: Int, width: Int, height: Int): Vector[Vector[Int]] = {
    Vector.tabulate(width, height)((x,y) => powerLevel((y + 1,x + 1), serialNumber))
  }

  private def calculateTotalPower(grid: Vector[Vector[Int]], startCoordinate: (Int, Int)): Int = {
    val powers = for {
      y <- startCoordinate._2 until (startCoordinate._2 + 3)
      x <- startCoordinate._1 until (startCoordinate._1 + 3)
    } yield grid(y)(x)

    powers.sum
  }

  def powerLevel(coordinate: (Int, Int), serialNumber: Int): Int = {
    val rackId = coordinate._1 + 10
    val powerLevel = rackId * coordinate._2 + serialNumber
    val intermediate = (powerLevel * rackId).toString
    val hundredsDigit =
      if (intermediate.length < 3) 0
      else intermediate.charAt(intermediate.length - 3) - '0'
    hundredsDigit - 5
  }
}
