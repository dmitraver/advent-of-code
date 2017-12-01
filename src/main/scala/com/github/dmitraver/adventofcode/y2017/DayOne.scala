package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayOne {

  def captcha(input: Vector[Char]): Int = {
    val nums = input map toInt
    val grouped = (nums :+ nums.head).sliding(2, 1)
    grouped.foldLeft(0) { (acc, next) =>
      if (next.size == 2 && next(0) == next(1)) acc + next.head
      else acc
    }
  }

  private def toInt(c: Char): Int = c - '0'

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day1_1")
    val result = captcha(input.toVector)
    println("Captcha part 1:" + result)
  }
}
