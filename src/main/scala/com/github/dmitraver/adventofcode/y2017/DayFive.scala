package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayFive {

  def countSteps(jumps: Vector[Int])(offset: Int => Int): Int = {
    val instructions = jumps.toArray

    def loop(currentInstruction: Int = 0, steps: Int = 0): Int = {
      val jumpOffset = instructions(currentInstruction)
      val jumpOffsetIndex = currentInstruction + jumpOffset
      if (jumpOffsetIndex < 0 || jumpOffsetIndex >= instructions.length) steps + 1
      else {
        instructions(currentInstruction) = offset(jumpOffset)
        loop(jumpOffsetIndex, steps + 1)
      }
    }

    loop()
  }

  def offsetIncrement(offset: Int): Int = offset + 1
  def offsetDecrement(offset: Int): Int = if (offset >= 3) offset - 1 else offset + 1

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day5").getLines().map(_.toInt).toVector
    println("Number of steps 1: " + countSteps(input)(offsetIncrement))
    println("Number of steps 2: " + countSteps(input)(offsetDecrement))
  }
}
