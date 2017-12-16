package com.github.dmitraver.adventofcode.y2017

object DayFifteen {

  type Generator[A] = (Long, Long) => A

  private def generate: Generator[Long] = {
    (seed, factor) =>
      (seed * factor) % 2147483647
  }

  private def generateN: Generator[Iterator[Long]] = {
    (seed, factor) =>
      def go(seed: Long): Stream[Long] = {
          val next = generate(seed, factor)
          next #:: go(next)
      }

      go(seed).toIterator
  }

  def judgeCount(genAStartValue: Long, genBStartValue: Long): Long = {
    val pairs = 40000000
    val genA = generateN(genAStartValue, 16807).take(pairs)
    val genB = generateN(genBStartValue, 48271).take(pairs)
    val mask = (1 << 16) - 1
    genA.zip(genB).count(pair => (pair._1 & mask) == (pair._2 & mask))
  }

  def judgeFinalCount(genAStartValue: Long, genBStartValue: Long): Long = {
    val pairs = 5000000
    val genA = generateN(genAStartValue, 16807).filter(_ % 4 == 0).take(pairs)
    val genB = generateN(genBStartValue, 48271).filter(_ % 8 == 0).take(pairs)
    val mask = (1 << 16) - 1
    genA.zip(genB).count(pair => (pair._1 & mask) == (pair._2 & mask))
  }

  def main(args: Array[String]): Unit = {
    println("Judge's count: " + judgeCount(277, 349))
    println("Judge's final count: " + judgeFinalCount(277, 349))
  }
}