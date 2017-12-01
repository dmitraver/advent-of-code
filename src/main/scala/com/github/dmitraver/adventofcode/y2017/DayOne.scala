package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayOne {

  def captchaOne(input: Vector[Char]): Int = {
    val nums = input map toInt
    val grouped = (nums :+ nums.head).sliding(2, 1)
    grouped.foldLeft(0) { (acc, next) =>
      if (next.size == 2 && next(0) == next(1)) acc + next.head
      else acc
    }
  }

  def captchaTwo(input: Vector[Char]): Int = {
    val nums = input map toInt zipWithIndex
    val offset = nums.length / 2
    nums.foldLeft(0) { (acc, current) =>
      val nextDigitPosition = (current._2 + offset) % nums.length
      if (current._1 == nums(nextDigitPosition)._1) acc + current._1
      else acc
    }
  }

  private def toInt(c: Char): Int = c - '0'

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day1_1").toVector
    println("Captcha part 1:" + captchaOne(input))
    println("Captcha part 2:" + captchaTwo(input))
  }
}
