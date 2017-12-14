package com.github.dmitraver.adventofcode.y2017


object DayTen {

  def reverse(vector: Vector[Int], start: Int, length: Int): Vector[Int] = {
    val l = length % vector.size
    val len = if (l == 0) vector.size else l
    val end = start + len
    if (end > vector.size) {
      val offset = end - vector.size
      val middle = vector.slice(offset, start)

      val reversed = (vector.drop(start) ++ vector.take(offset)).reverse
      reversed.drop(vector.size - start) ++ middle ++ reversed.take(vector.size - start)
    } else vector.take(start) ++ vector.slice(start, end).reverse ++ vector.drop(end)
  }

  def hash(lengths: Vector[Int]): Int = {
    def loop(current: Int = 0, i: Int = 0, skipSize: Int = 0, vector: Vector[Int] = Vector.range(0, 5)): Int = {
      if (i == lengths.size) vector(0) * vector(1)
      else {
        val length = lengths(i)
        val reversed = reverse(vector, current, length)
        loop(current + length + skipSize, i + 1, skipSize + 1, reversed)
      }
    }

    loop()
  }

  def main(args: Array[String]): Unit = {
    val lenghts = Vector(3, 4, 1, 5)
    println(hash(lenghts))
    //println(reverse(Vector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 0, 42)) // 6 7 8 9 10 1 2 3 4 5-> 5 4 3 2 1 10 9 8 7 6
  }

}
