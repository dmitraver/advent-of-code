package com.github.dmitraver.adventofcode.y2018

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object Day2 {
  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day2_1").getLines().toVector
    println("Checksum: " + checksum(input))
    println("Common letters: " + commonLetters(input))
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

  def commonLetters(input: Vector[String]): String = {
    val allCommonLetters = for {
      x <- input
      y <- input
    } yield commonLetters(x, y)

    allCommonLetters.find(!_.isEmpty).getOrElse("")
  }

  private def commonLetters(s1: String, s2: String): String = {
    val zipped = s1 zip s2 map { case (a, b) => a == b } zipWithIndex
    val countDiff = zipped count (!_._1)
    if (countDiff == 1) {
      val index = zipped find { case (b, i) => !b } map (_._2) getOrElse -1
      s1.take(index) + s1.drop(index + 1)
    } else ""
  }
}
