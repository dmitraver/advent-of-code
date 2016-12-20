package com.github.dmitraver.adventofcode.y2016

object DayThirteen {

  val designerMagicNumber = 1350
  val boundX = 100
  val boundY = 100

  def main(args: Array[String]): Unit = {
    val maze = Array.tabulate(boundX, boundY) ((x, y) => if (isOpenSpace((y,x))) "." else "#")
    println("Shortest path is : " + findShortestPathLength(maze, start = (1,1), target = (39,31)))
  }

  def findShortestPathLength(maze: Array[Array[String]], start: (Int, Int), target: (Int, Int)) = {
    val visited = collection.mutable.Map.empty[(Int, Int), Boolean]

    def bfs(maze: Array[Array[String]], target: (Int, Int), path: Int = 0, moves: List[(Int, Int, Int)] = List((0, 0, 0))): Int = moves match {
      case Nil => -1
      case (x,y, distance) :: tail if (x,y) == target => distance
      case (x, y, distance) :: tail =>
        visited += ((x,y) -> true)
        val nextMoves = List((x + 1, y, distance + 1), (x - 1, y, distance + 1), (x, y + 1, distance + 1), (x, y - 1, distance + 1)).filter(coordinate => isValid((coordinate._1,coordinate._2), maze, visited))
        bfs(maze, target, path + 1, tail ++ nextMoves)
    }

    bfs(maze, target, 0, List((start._1, start._2, 0)))
  }

  private def isValid(coordinate: (Int, Int), maze: Array[Array[String]], visited: collection.mutable.Map[(Int, Int), Boolean]) = {
    val (x, y) = coordinate
    x >= 0 && x < boundX && y >= 0 && y < boundY && maze(x)(y) != "#" && !visited.getOrElse(coordinate, false)
  }

  private def isOpenSpace(coordinate: (Int, Int)) = {
    val (x, y) = coordinate
    val number = x * x + 3 * x + 2 * x * y + y + y * y + designerMagicNumber
    number.toBinaryString.count(_ == '1') % 2 == 0
  }

  private def printMaze(maze: Array[Array[Int]]) = {
    maze.foreach { row =>
      row.foreach(print)
      println()
    }
  }
}
