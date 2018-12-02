package com.github.dmitraver.adventofcode.y2018

import com.github.dmitraver.adventofcode.utils.ResourceLoader
import fs2.Stream

object DayTwo {
  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day2_1").getLines().toVector
    println("Checksum: " + checksum(input))
  }

  def checksum(input: Vector[String]): Int = {
    val countsTwoOrThree = input.map { ids =>
      val counts = ids.groupBy(c => c).map(a => (a._1, a._2.length))
      counts.foldLeft((0, 0))( (acc, elem) =>
        if (elem._2 == 2) (1, acc._2) else if (elem._2 == 3) (acc._1, 1) else acc )
    }

    val checksum = countsTwoOrThree.foldLeft((0,0))((acc, elem) => (acc._1 + elem._1, acc._2 + elem._2))
    checksum._1 * checksum._2
  }
}
