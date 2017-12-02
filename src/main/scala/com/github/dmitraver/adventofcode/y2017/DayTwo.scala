package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayTwo {

  def calculateChecksum(input: Iterator[String]): Int = {
    input.map { str =>
      val nums = str.split("\\s+").map(_.toInt)
      nums.max - nums.min
    }.sum
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day2")
    val checksum = calculateChecksum(input.getLines())
    println("Checksum: " + checksum)
  }

}
