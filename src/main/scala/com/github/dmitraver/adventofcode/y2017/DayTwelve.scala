package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayTwelve {

  private val Pattern = "(\\d+)\\s+<->\\s+(.*)".r

  def makeGraph(input: Vector[String]): Map[Int, Vector[Int]] = {
    input.foldLeft(Map.empty[Int, Vector[Int]]) {(acc, input) =>
      input match {
        case Pattern(node, adjacentNodes) => acc + (node.toInt -> adjacentNodes.split(", ").map(_.toInt).toVector)
        case _ => acc
      }
    }
  }

  def countGroupSize(graph: Map[Int, Vector[Int]]): Int = {
    val visited = Array.fill(graph.size)(false)
    def dfs(node: Int): Unit = {
      visited(node) = true
      val adjacentNodes = graph(node)
      adjacentNodes foreach { node =>
        if (!visited(node)) dfs(node)
      }
    }

    dfs(0)
    visited.count(_ == true)
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day12").getLines().toVector
    val graph = makeGraph(input)
    println("Group with 0 size:" + countGroupSize(graph))
  }
}
