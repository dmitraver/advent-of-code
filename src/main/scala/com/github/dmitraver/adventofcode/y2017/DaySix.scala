package com.github.dmitraver.adventofcode.y2017

import scala.collection.mutable

object DaySix {

  private def findBankIndexWithBiggestBlock(banks: Vector[Int]): Int = {
    val max = banks.max
    banks.indexOf(max)
  }

  private def redistribute(banks: Vector[Int]): Vector[Int] = {
    val mutableBanks = banks.toArray
    val i = findBankIndexWithBiggestBlock(banks)
    val blocks = mutableBanks(i)
    mutableBanks(i) = 0

    def loop(currentBankIndex: Int, blocks: Int): Vector[Int] = {
      if (blocks == 0) mutableBanks.toVector
      else {
        val index = if (currentBankIndex >= mutableBanks.length) 0 else currentBankIndex
        val oldValue = mutableBanks(index)
        mutableBanks(index) = oldValue + 1
        loop(index + 1, blocks - 1)
      }
    }

    loop(i + 1, blocks)
  }

  def countRedistributionCycles(banks: Vector[Int]): Int = {
    val states = mutable.Set[Vector[Int]]()
    def loop(cycles: Int, banks: Vector[Int]): Int = {
      val newState = redistribute(banks)
      if (states.contains(newState)) cycles
      else {
        states += newState
        loop(cycles + 1, newState)
      }
    }

    loop(1, banks)
  }

  def main(args: Array[String]): Unit = {
    println("Cycles count: " + countRedistributionCycles(Vector(0, 5, 10, 0, 11, 14, 13, 4, 11, 8, 8, 7, 1, 4, 12, 11)))
  }
}
