package com.github.dmitraver.adventofcode.y2017


object DayTen {

  private def reverse(vector: Vector[Int], start: Int, length: Int): Vector[Int] = {
    val end = start + length
    if (length > vector.size) vector
    else if (end >= vector.size) {
      val offset = end - vector.size
      val middle = vector.slice(offset, start)

      val reversed = (vector.drop(start) ++ vector.take(offset)).reverse
      reversed.drop(vector.size - start) ++ middle ++ reversed.take(vector.size - start)
    } else vector.take(start) ++ vector.slice(start, end).reverse ++ vector.drop(end)
  }

  def hash(lengths: Vector[Int], inputLength: Int): Int = {
    def loop(currentPosition: Int = 0, lengthIndex: Int = 0, skipSize: Int = 0, vector: Vector[Int] = Vector.range(0, inputLength)): Int = {
      if (lengthIndex == lengths.size) vector(0) * vector(1)
      else {
        val length = lengths(lengthIndex)
        val offset = currentPosition % vector.size
        val reversed = reverse(vector, offset, length)
        loop(currentPosition + length + skipSize, lengthIndex + 1, skipSize + 1, reversed)
      }
    }

    loop()
  }

  def main(args: Array[String]): Unit = {
    val lengths = Vector(106,118,236,1,130,0,235,254,59,205,2,87,129,25,255,118)
    println("Hash:" + hash(lengths, 256))
  }

}
