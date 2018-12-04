package com.github.dmitraver.adventofcode.y2018

import com.github.dmitraver.adventofcode.utils.ResourceLoader
import fs2.Stream

object Day1 {
  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day1_1").getLines().toVector
    println("Frequency: " + frequency(input))
    println("First duplicate:" + frequency2(input))
  }

  def frequency(input: Vector[String]): Int = {
    input.map(_.toInt).sum
  }

  def frequency2(input: Vector[String]): Int = {
    val stream = Stream.emits(input).map(_.toInt).repeat.scan(0)(_ + _)
    firstDuplicate(stream)
  }

  private def firstDuplicate(stream: Stream[fs2.Pure, Int]): Int = {
    // TODO this is ugly af
    val seen = collection.mutable.Set[Int]()
    stream.dropWhile { e =>
      if (seen contains e)
        false
      else {
        seen add e
        true
      }
    }.head.compile.toVector(0)
  }
}
