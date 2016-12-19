package com.github.dmitraver.adventofcode.y2016

object DayNineteen {

  def main(args: Array[String]): Unit = {
    val elfs = Array.fill(3001330)(1)
    println("Elf with all presents: " + findLastElfWithAllPresents(elfs))
  }

  def findLastElfWithAllPresents(elfs: Array[Int], currentElfId: Int = 0): Int = {
    if (currentElfId >= elfs.length) findLastElfWithAllPresents(elfs, 0)
    else if (!hasPresents(elfs, currentElfId)) findLastElfWithAllPresents(elfs, currentElfId + 1)
    else {
      val success = stealNextElfsPresents(elfs, currentElfId, currentElfId + 1)
      if (!success) currentElfId + 1 else findLastElfWithAllPresents(elfs, currentElfId + 1)
    }
  }

  private def hasPresents(elfs: Array[Int], elfId: Int) = elfs(elfId) > 0

  private def stealNextElfsPresents(elfs: Array[Int], thiefId: Int, nextElfId: Int): Boolean = {
    if (thiefId == nextElfId) false
    else if (nextElfId >= elfs.length) stealNextElfsPresents(elfs, thiefId, 0)
    else if(!hasPresents(elfs, nextElfId)) stealNextElfsPresents(elfs, thiefId, nextElfId + 1)
    else {
      val nextElfsPresents = elfs(nextElfId)
      elfs(nextElfId) = 0
      elfs(thiefId) = elfs(thiefId) + nextElfsPresents
      true
    }
  }

}
