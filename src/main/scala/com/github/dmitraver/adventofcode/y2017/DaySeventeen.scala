package com.github.dmitraver.adventofcode.y2017

object DaySeventeen {

  def insertionIndex(currentIndex: Int, step: Int, bufferSize: Int): Int = {
    (currentIndex + step) % bufferSize
  }

  def insertAfter(index: Int, buffer: Vector[Int], element: Int): Vector[Int] = {
    (buffer.take(index + 1) :+ element) ++ buffer.drop(index + 1)
  }

  def bufferStream(step: Int): Stream[Vector[Int]] = {
    def go(currentIndex: Int, currentValue: Int, buffer: Vector[Int]): Stream[Vector[Int]] = {
      val index = insertionIndex(currentIndex, step, buffer.size)
      val newBuffer = insertAfter(index, buffer, currentValue)
      newBuffer #:: go(index + 1, currentValue + 1, newBuffer)
    }

    go(0, 1, Vector(0))
  }

  def main(args: Array[String]): Unit = {
    val buffer = bufferStream(367).drop(2017).head
    val index = buffer.indexOf(2017)
    println(buffer(index + 1))
  }
}
