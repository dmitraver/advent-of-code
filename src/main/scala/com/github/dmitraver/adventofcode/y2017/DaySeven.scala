package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.collection.mutable

case class Program(name: String, weight: Int, programsAbove: List[String])

object DaySeven {

  val topProgramRegexp = "(\\w+)\\s\\((\\d+)\\)".r
  val programRegexp = "(\\w+)\\s\\((\\d+)\\)\\s+->\\s+(.*)".r

  def parseInput(input: Vector[String]): Map[String, Program] = {
    val map = new mutable.HashMap[String, Program]()
    input.map {
      case topProgramRegexp(name, weight) => map.put(name, Program(name, weight.toInt, List()))
      case programRegexp(name, weight, values) => map.put(name, Program(name, weight.toInt, values.split(", ").toList))
    }

    map.toMap // well, we don't want mutable crap to be passed around
  }

  def findBottomProgram(programs: Map[String, Program]): String = {
    val values = programs.valuesIterator.map(_.programsAbove).flatten.toSet
    val keys = programs.keys
    keys.find(name => !values.contains(name)).get
  }

  def getHeight(programName: String, input: Map[String, Program]): Int = {
    val rootProgram = input(programName)
    val leaves = rootProgram.programsAbove
    if (leaves.isEmpty) rootProgram.weight
    else {
      rootProgram.weight + leaves.foldLeft(0) { (sum, next) =>
        sum + getHeight(next, input)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day7").getLines().toVector
    val programs = parseInput(input)
    val bottomProgram = findBottomProgram(programs)
    println("Bottom program name: " + bottomProgram)
  }
}
