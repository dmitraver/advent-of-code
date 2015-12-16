package com.github.dmitraver.adventofcode

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.io.Source

object DayTwo {

  def main(args: Array[String]) {
    val sourceOne = ResourceLoader.fromResource("day2")
    println("Total paper: " + totalPaper(sourceOne))

    val sourceTwo = ResourceLoader.fromResource("day2")
    println("Total ribbon: " + totalRibbon(sourceTwo))
  }

  def totalPaper(source: Source) = {
    source.getLines().foldLeft(0) ((total, current) => total + (current.split("x").map(_.toInt) match {
      case Array(length, width, height) =>
        val x = length * width
        val y = width * height
        val z = height * length
        2 * (x + y + z) + (x min y min z)
    }))
  }

  def totalRibbon(source: Source) = {
    source.getLines().foldLeft(0) ((total, current) => total + (current.split("x").map(_.toInt).sorted match {
      case Array(x, y, z) => 2 * x + 2 * y  + x * y * z
    }))
  }

}
