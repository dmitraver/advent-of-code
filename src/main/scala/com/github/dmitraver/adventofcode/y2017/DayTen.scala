package com.github.dmitraver.adventofcode.y2017

case class RoundResult(currentPosition: Int, skipSize: Int, vector: Vector[Int])

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
    val result = hashingRound(0, 0, Vector.range(0, inputLength), lengths).vector
    result(0) * result(1)
  }

  def hashingRound(currentPosition: Int, skipSize: Int, vector: Vector[Int], lengths: Vector[Int]): RoundResult = {
    def go(currentPosition: Int, lengthIndex: Int, skipSize: Int, vector: Vector[Int]): RoundResult = {
      if (lengthIndex == lengths.size) RoundResult(currentPosition, skipSize, vector)
      else {
        val length = lengths(lengthIndex)
        val offset = currentPosition % vector.size
        val reversed = reverse(vector, offset, length)
        go(currentPosition + length + skipSize, lengthIndex + 1, skipSize + 1, reversed)
      }
    }

    go(currentPosition, 0, skipSize, vector)

  }

  private def strToBytes(str: String): Vector[Int] = str.map(_.toInt).toVector

  def sparseHash(input: String) = {
    val lengths = strToBytes(input) ++ Vector(17, 31, 73, 47, 23)
    def go(rounds: Int, currentPosition: Int, skipSize: Int, vector: Vector[Int]): RoundResult = {
      if (rounds == 0) RoundResult(currentPosition, skipSize, vector)
      else {
        val result = hashingRound(currentPosition, skipSize, vector, lengths)
        go(rounds - 1, result.currentPosition, result.skipSize, result.vector)
      }
    }

    go(64, 0, 0, Vector.range(0, 256))
  }

  def denseHash(input: String): String = {
    val hash = sparseHash(input).vector
    hash.grouped(16).map(xor).map("%02x" format _).mkString
  }

  private def xor(vector: Vector[Int]): Int = vector.foldLeft(0)((acc, i) => acc ^ i)

  def main(args: Array[String]): Unit = {
    val lengths = Vector(106,118,236,1,130,0,235,254,59,205,2,87,129,25,255,118)
    println("Hash:" + hash(lengths, 256))
    println("Dense hash:" + denseHash("106,118,236,1,130,0,235,254,59,205,2,87,129,25,255,118"))
  }

}
