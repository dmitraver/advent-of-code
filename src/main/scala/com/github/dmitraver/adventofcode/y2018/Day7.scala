package com.github.dmitraver.adventofcode.y2018

import com.github.dmitraver.adventofcode.utils.ResourceLoader
import com.github.dmitraver.adventofcode.utils.extensions._

import scala.collection.immutable.TreeMap

object Day7 {

  private val StepsOrderRegexp = "Step (\\w) must be finished before step (\\w) can begin.".r

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day7").getLines().toVector
    println("Instr order: " + partOne(input))
  }

  def partOne(input: Vector[String]): String = {
    val parsed = parseInput(input)
    val dependenciesMap = buildDependencyMap(parsed)
    solvePartOne(dependenciesMap)
  }

  private def parseInput(input: Vector[String]): Vector[(Char, Char)] = {
    input.map {
      case StepsOrderRegexp(a, b) => (a.charAt(0), b.charAt(0))
    }
  }

  private def buildDependencyMap(input: Vector[(Char, Char)]): TreeMap[Char, Set[Char]] = {
    input.foldLeft(TreeMap.empty[Char, Set[Char]]) { (acc, a) =>
      val updated = acc.adjust(a._2, Some(Set(a._1)))(set => set + a._1)
      if (!updated.contains(a._1))
        updated + (a._1 -> Set())
      else updated
    }
  }

  private def solvePartOne(dependencies: TreeMap[Char, Set[Char]], instrOrder: Vector[Char] = Vector.empty): String = {
    if (dependencies.isEmpty) instrOrder.mkString
    else {
      val instrWithoutDependency = dependencies.find(_._2.isEmpty)
       instrWithoutDependency match {
         case Some((instruction, deps)) =>
           val updated = dependencies - instruction
           val result = updated.map { case (k, v) =>
             k -> (v - instruction)
           }

           solvePartOne(result, instrOrder :+ instruction)

         case None => "" // cycle dependency
       }
    }
  }

}
