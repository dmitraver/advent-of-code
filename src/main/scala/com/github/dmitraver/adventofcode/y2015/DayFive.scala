package com.github.dmitraver.adventofcode.y2015

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.io.Source

object DayFive {
  def main(args: Array[String]) {
    val source = ResourceLoader.fromResource("y2015/day5")
    println("Number of nice strings: " + countNiceStrings(source))
  }

  def countNiceStrings(source: Source) = {
    source.getLines().count(isNice)
  }

  def isNice(str: String): Boolean = {
    !str.contains("ab") && !str.contains("cd") && !str.contains("pq") && !str.contains("xy") &&
      (str.count(c => "aeiou".contains(c)) > 2) && str.sliding(2).exists(str => str.charAt(0) == str.charAt(1))
  }
}
