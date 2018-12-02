package com.github.dmitraver.adventofcode.y2018

import com.github.dmitraver.adventofcode.utils.ResourceLoader
import fs2.Stream

object DayOne {
  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day1_1").getLines().toVector
    println("Frequency: " + frequency(input))
  }

  def frequency(input: Vector[String]): Int = {
    input.map(_.toInt).sum
  }

  def frequency2(input: Vector[String]): Int = {
    Stream.emits(input).repeat
    ???
  }
}
