package com.github.dmitraver.adventofcode.y2018

import atto.Atto._
import atto._
import com.github.dmitraver.adventofcode.utils.ResourceLoader


object Day8 {
  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day8").getLines().toVector.head
     println("Part one: " + partOne(input))
  }

  private def partOne(input: String): Int = {
    parsePartOne().parse(input).done.feed("").option.get
  }

  private def parsePartOne(): Parser[Int] = {
    for {
      nodes   <- token(int)
      entries <- token(int)
      childs  <- manyN(nodes, parsePartOne()).map(_.sum)
      metas   <- manyN(entries, token(int)).map(_.sum)
    } yield childs + metas
  }
}
