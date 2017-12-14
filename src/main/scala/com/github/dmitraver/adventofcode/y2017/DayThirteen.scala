package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayThirteen {

  type Depth = Int
  type Range = Int
  type Time = Int

  private val FirewallPattern = "(\\d+):\\s+(\\d+)".r

  def processInput(input: Vector[String]): Map[Depth, Range] = {
    input.foldLeft(Map.empty[Depth, Range]) { (acc, input) =>
      input match {
        case FirewallPattern(depth, range) => acc + (depth.toInt -> range.toInt)
      }
    }
  }

  private def scannerPositionAtTime(time: Time, layer: Depth, layers: Map[Depth, Range]): Int = {
    layers.getOrElse(layer, -1) match {
      case -1 => -1
      case range =>
        val x = (range - 1) * 2
        val offset = time % x
        if (offset <= range - 1) offset
        else x - offset

    }
  }

  private def isCaught(time: Time, layer: Depth, layers: Map[Depth, Range]): Boolean = {
    scannerPositionAtTime(time, layer, layers) == 0
  }

  def getSeverity(layers: Map[Depth, Range]): Int = {
    (0 to layers.maxBy(_._1)._1).foldLeft(0) { (acc, time) =>
      if(isCaught(time, time, layers)) acc + (time * layers(time))
      else acc
    }
  }

  def calculateDelay(layers: Map[Depth, Range]): Int = {
    val delays = Stream.from(0, 1)
    val maxDepth = layers.maxBy(_._1)._1
    delays.dropWhile { delay =>
      val delaywithIndex = (delay to delay +  maxDepth).zipWithIndex
      delaywithIndex exists { case (delay, i) =>
        isCaught(delay, i, layers)
      }
    }.head
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day13").getLines().toVector
    val layers = processInput(input)
    println("Severity: " + getSeverity(layers))
    println("Delay: "+ calculateDelay(layers))
  }
}
