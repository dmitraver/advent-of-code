package com.github.dmitraver.adventofcode.y2018

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object Day5 {
  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day5").getLines().toVector.head
    println("Part one: " + partOne(input))
    println("Part two: " + partTwo(input))
  }

  def partOne(input: String): Int = {
    input.foldRight(Vector[Char]())( (c, acc) =>
      acc match {
        case x +: xs if isReact(c, x) => xs
        case _ => c +: acc
      }
    ).mkString.length
  }

  def partTwo(input: String): Int = {
    ('a' to 'z').foldLeft(Vector[Int]())((acc, c) =>
      acc :+ partOne(input.filter(_.toLower != c))
    ).min
  }

  private def isReact(c1: Char, c2: Char): Boolean = {
    c1.toLower == c2.toLower && c1 != c2
  }
}
