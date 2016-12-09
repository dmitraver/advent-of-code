package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayNine {

  def main(args: Array[String]): Unit = {
    val source = ResourceLoader.fromResource("y2016/day9")
    println("Decompressed file length: " + source.getLines().map(decompress(_).length).sum)
  }

  def decompress(input: String, output: StringBuilder = new StringBuilder(), position: Int = 0): String = {
    var current = position
    if (current >= input.length) {
      return output.toString
    } else if (input(current) == ' ') {
      current = current + 1
    } else if (input(current) == '(') {
      current = current + 1
      var start = current
      while(input(current) != 'x') {
        current = current + 1
      }

      val charsToRepeat = input.substring(start, current).toInt

      start = current + 1
      while(input(current) != ')') {
        current = current + 1
      }

      val repetitionNumber = input.substring(start, current).toInt

      current = current + 1
      val repeated = input.substring(current, current + charsToRepeat) * repetitionNumber
      output append repeated
      current = current + charsToRepeat
      return decompress(input, output, current)
    } else {
      output append input(current)
    }

    decompress(input, output, current + 1)
  }
}
