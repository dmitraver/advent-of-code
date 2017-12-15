package com.github.dmitraver.adventofcode.y2017

object DayFifteen {

  type Generator[A] = (Long, Long) => A

  private def generate: Generator[Long] = {
    (seed, factor) =>
      (seed * factor) % 2147483647
  }

  private def generateN(n: Int): Generator[Iterator[Long]] = {
    (seed, factor) =>
      def go(n: Int, seed: Long): Stream[Long] = {
        if (n == 0) Stream.empty
        else {
          val next = generate(seed, factor)
          next #:: go(n - 1, next)
        }
      }

      go(n, seed).toIterator
  }

  def judgeCount(genAStartValue: Long, genBStartValue: Long): Long = {
    val pairs = 40000000
    val genA = generateN(pairs)(genAStartValue, 16807)
    val genB = generateN(pairs)(genBStartValue, 48271)
    val mask = (1 << 16) - 1
    genA.zip(genB).count(pair => (pair._1 & mask) == (pair._2 & mask))
  }

  def main(args: Array[String]): Unit = {
    println("Judge's count: " + judgeCount(277, 349))
  }
}