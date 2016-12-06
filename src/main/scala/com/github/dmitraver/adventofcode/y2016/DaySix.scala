package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.io.Source

object DaySix {

  def main(args: Array[String]): Unit = {
    val sourceOne = ResourceLoader.fromResource("y2016/day6")
    println("Error-corrected message with most frequent element picking: " + decodeCorrectMessage(sourceOne))

    val sourceTwo = ResourceLoader.fromResource("y2016/day6")
    println("Error-corrected message with least frequent element picking: " + decodeCorrectMessage(sourceTwo, pickMostFrequent = false))
  }

  def decodeCorrectMessage(source: Source, pickMostFrequent: Boolean = true) = {
    source.getLines().toList.map(_.toList).transpose.map { line =>
      val frequencyMap = line.groupBy(_.toChar)
      if (pickMostFrequent) frequencyMap.maxBy(x => x._2.length)._1 else frequencyMap.minBy (x => x._2.length)._1
    }.mkString
  }
}
