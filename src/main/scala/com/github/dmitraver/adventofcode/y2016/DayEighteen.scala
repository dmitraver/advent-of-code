package com.github.dmitraver.adventofcode.y2016

object DayEighteen {

  def main(args: Array[String]): Unit = {
    println("Number of safe tiles in 40 rows: " + countSafeTiles(".^^^.^.^^^^^..^^^..^..^..^^..^.^.^.^^.^^....^.^...^.^^.^^.^^..^^..^.^..^^^.^^...^...^^....^^.^^^^^^^", 40))
    println("Number of safe tiles in 400000 rows: " + countSafeTiles(".^^^.^.^^^^^..^^^..^..^..^^..^.^.^.^^.^^....^.^...^.^^.^^.^^..^^..^.^..^^^.^^...^...^^....^^.^^^^^^^", 400000))
  }

  def countSafeTiles(firstRow: String, numberOfRows: Int) = {
    Stream.iterate(firstRow, numberOfRows)(row => makeNextRow(row)).map(_.count(c => if (c == '.') true else false)).sum
  }

  private def makeNextRow(row: String): String = ("." + row + ".").sliding(3,1).map(toTrailOrSafeTile).mkString

  private def toTrailOrSafeTile(input: String) = if (input == "..^" || input == ".^^" || input == "^^." || input == "^..") "^" else "."
}
