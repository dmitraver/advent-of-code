package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.collection.mutable

case class Program(name: String, weight: Int, programsAbove: List[String])

object DaySeven {

  val topProgramRegexp = "(\\w+)\\s\\((\\d+)\\)".r
  val programRegexp = "(\\w+)\\s\\((\\d+)\\)\\s+->\\s+(.*)".r

  def parseInput(input: Vector[String]): Map[String, List[String]] = {
    val map = new mutable.HashMap[String, List[String]]()
    input.map {
      case topProgramRegexp(name, weight) => map.put(name, List())
      case programRegexp(name, weight, values) => map.put(name, values.split(", ").toList)
    }

    map.toMap // well, we don't want mutable crap to be passed around
  }

  def findBottomProgram(input: Vector[String]): Option[String] = {
    val programsDependencies = parseInput(input)
    val values = programsDependencies.valuesIterator.flatten.toSet
    val keys = programsDependencies.keys
    keys.find(name => !values.contains(name))
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day7").getLines().toVector
    println("Bottom program name: " + findBottomProgram(input))
  }
}
