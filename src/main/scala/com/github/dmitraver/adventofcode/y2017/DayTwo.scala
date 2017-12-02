package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayTwo {

  val minMaxDiffCalc = (row: Vector[Int]) => row.max - row.min

  def calculateChecksum(input: Stream[String])(row: Vector[Int] => Int): Int = {
    input.map { str =>
      val nums = str.split("\\s+").map(_.toInt).toVector
      row(nums)
    }.sum
  }

  def evenlyDivisibleCalc(input: Vector[Int]): Int = {
    val sorted = input.sorted

    def loop(index: Int): Int = {
      if(index == sorted.length) -1
      else {
        val value = sorted(index)
        (0 until index) find(i => value % sorted(i) == 0) match {
          case Some(x) => value / sorted(x)
          case None => loop(index + 1)
        }
      }
    }

    loop(0)
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day2").getLines().toStream
    val checksumOne = calculateChecksum(input)(minMaxDiffCalc)
    val checksumTwo = calculateChecksum(input)(evenlyDivisibleCalc)
    println("Checksum one: " + checksumOne)
    println("Checksum two: " + checksumTwo)
  }

}
