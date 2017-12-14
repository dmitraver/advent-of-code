package com.github.dmitraver.adventofcode.y2017

object DayFourteen {

  private def countUsedSquares(input: String): Int = {
    val hash = DayTen.denseHash(input)
    hash.toIterator.map(c => Integer.parseInt(c.toString, 16).toBinaryString).mkString.count(_ == '1')
  }

  def countUsedSquaresInGrid(key: String, rows: Int = 128): Int = {
    (0 until rows) map (i => s"$key-$i") map countUsedSquares sum
  }

  def main(args: Array[String]): Unit = {
    println("Used squares: " + countUsedSquaresInGrid("ffayrhll"))
  }
}
