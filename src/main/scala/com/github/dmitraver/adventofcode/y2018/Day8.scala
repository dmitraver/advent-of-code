package com.github.dmitraver.adventofcode.y2018

import atto.Atto._
import atto._
import com.github.dmitraver.adventofcode.utils.ResourceLoader


object Day8 {
  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day8").getLines().toVector.head
    println("Part one: " + partOne(input))
    println("Part two: " + partTwo(input))
  }

  def partOne(input: String): Int = {
    parserPartOne().parse(input).feed("").option.get
  }

  private def parserPartOne(): Parser[Int] = {
    for {
      nodes   <- token(int)
      entries <- token(int)
      childs  <- manyN(nodes, parserPartOne()).map(_.sum)
      metas   <- manyN(entries, token(int)).map(_.sum)
    } yield childs + metas
  }

  def partTwo(input: String): Int = {
    parserPartTwo().parse(input).feed("").option.get
  }

  def parserPartTwo(): Parser[Int] = {
    for {
      nodes   <- token(int)
      entries <- token(int)
      childs  <- manyN(nodes, parserPartTwo()).map(_.toVector)
      metas   <- manyN(entries, token(int)).map(_.toVector)
    } yield {
      if (childs.isEmpty) metas.sum
      else {
        metas collect {
          case i if i <= childs.size => childs(i - 1)
        } sum
      }
    }
  }
}
