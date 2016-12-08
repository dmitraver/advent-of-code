package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.io.Source

object DayEight {

  val rectCommandPattern = "rect (\\d*)x(\\d*)".r
  val rotateRowCommandPattern = "rotate row y=(\\d*) by (\\d*)".r
  val rotateColumnCommandPattern = "rotate column x=(\\d*) by (\\d*)".r

  def main(args: Array[String]): Unit = {
    val source = ResourceLoader.fromResource("y2016/day8")
    val screen = readAndApplyCommands(Screen(50,6), source)
    println("Pixels lit: " + screen.countPixelsLit())
    screen.display()
  }

  def readAndApplyCommands(screen: Screen, source: Source) = {
    source.getLines().foldLeft(screen) { case (screen, command) =>
      command match {
        case rectCommandPattern(wide, tall) =>
          screen.turnOnRect(wide.toInt, tall.toInt)
        case rotateRowCommandPattern(row, by) => screen.rotateRowRight(row.toInt, by.toInt)
        case rotateColumnCommandPattern(column, by) => screen.rotateColumnDown(column.toInt, by.toInt)
      }
    }
  }

}

case class Screen(wide: Int, tall: Int) {
  val screen = Array.fill(tall, wide)('.')

  def turnOnRect(wide: Int, tall: Int) = {
    for(i <- 0 until wide) {
      for(j <- 0 until tall) {
        screen(j)(i) = '#'
      }
    }
    this
  }

  def rotateColumnDown(columnNumber: Int, rotateBy: Int) = {
    val column = screen.map(_(columnNumber))
    val (first, last) = column.splitAt(tall - (rotateBy % tall))
    val shiftedColumn = last ++ first
    screen.zipWithIndex.foreach { case (row, i) =>
     row(columnNumber) = shiftedColumn(i)
    }
    this
  }

  def rotateRowRight(rowNumber: Int, rotateBy: Int) = {
    val row = screen(rowNumber)
    val (first, last) = row.splitAt(wide - (rotateBy % wide))
    screen(rowNumber) = last ++ first
    this
  }

  def display() = {
    for(i <- 0 until tall) {
      for(j <- 0 until wide) {
        print(screen(i)(j))
      }

      println()
    }
  }

  def countPixelsLit() = {
    screen.flatten.count(_ == '#')
  }
}
