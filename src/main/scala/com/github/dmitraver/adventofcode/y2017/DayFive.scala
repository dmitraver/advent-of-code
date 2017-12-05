package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayFive {

  def countSteps(jumps: Vector[Int]): Int = {
    val instructions = jumps.toArray

    def loop(currentInstruction: Int = 0, steps: Int = 0): Int = {
      val jumpOffset = instructions(currentInstruction)
      val jumpOffsetIndex = currentInstruction + jumpOffset
      if (jumpOffsetIndex < 0 || jumpOffsetIndex >= instructions.length) steps + 1
      else {
        instructions(currentInstruction) = jumpOffset + 1
        loop(jumpOffsetIndex, steps + 1)
      }
    }

    loop()
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day5").getLines().map(_.toInt).toVector
    println("Number of steps: " + countSteps(input))
  }
}
