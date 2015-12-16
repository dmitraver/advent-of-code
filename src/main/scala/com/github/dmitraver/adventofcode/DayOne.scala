package com.github.dmitraver.adventofcode

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.io.Source

object DayOne {

  val toFloor = (parenthesis: Char) => if (parenthesis == '(') 1 else -1

  def main(args: Array[String]) {
    val sourceOne = ResourceLoader.fromResource("day1")
    println("Floors: " + countFloors(sourceOne))

    val sourceTwo = ResourceLoader.fromResource("day1")
    println("Basement: " + findBasement(sourceTwo))
  }

  def countFloors(source: Source) = {
    source.foldLeft(0) ((total, char) => total + toFloor(char))
  }

  def findBasement(source: Source) = {
    source.toStream.map(toFloor).scan(0)(_ + _).takeWhile(_ != -1).length
  }
}
