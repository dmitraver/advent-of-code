package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayThirteen {

  type Depth = Int
  type Range = Int
  type Time = Int

  val FirewallPattern = "(\\d+):\\s+(\\d+)".r

  def processInput(input: Vector[String]): Map[Depth, Range] = {
    input.foldLeft(Map.empty[Depth, Range]) { (acc, input) =>
      input match {
        case FirewallPattern(depth, range) => acc + (depth.toInt -> range.toInt)
      }
    }
  }

  def scannerPositionAtTime(time: Time, layer: Depth, layers: Map[Depth, Range]): Int = {
    layers.getOrElse(layer, -1) match {
      case -1 => -1
      case range =>
        val isEven = ((time / range) - 1) % 2 == 0
        val offset = time % range
        if (isEven) offset
        else range - 1 - offset
    }
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day13").getLines().toVector
    val layers = processInput(input)
    println(scannerPositionAtTime(8, 4, layers))
//    (0 to 6) foreach { i =>
//      println(i + " - " + isCaught(i, firewall))
//    }
  }
}
