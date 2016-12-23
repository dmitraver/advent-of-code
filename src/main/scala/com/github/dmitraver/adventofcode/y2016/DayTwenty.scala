package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayTwenty {

  def main(args: Array[String]) {
    val intervals = ResourceLoader.fromResource("y2016/day20").getLines().toList
    println("Lowest value IP: " + findLowestValueIP(intervals))
  }

  def findLowestValueIP(intervals: List[String]) = {
    val sortedIntervals = intervals.map(toInterval).sortBy(x => x._1)
    sortedIntervals.foldLeft((0L, 0L))((acc, interval) => if (isIntervalsOverlap(acc, interval)) mergeIntervals(acc, interval) else acc)._2 + 1
  }

  private def isIntervalsOverlap(first: (Long, Long), second: (Long, Long)) = {
    val (x, y) = first
    val (x1, y1) = second
    y + 1 >= x1
  }

  private def mergeIntervals(first: (Long, Long), second: (Long, Long)) = {
    val (x, y) = first
    val (x1, y1) = second
    (x min x1, y max y1)
  }

  private def toInterval(input: String) = {
    val startAndEnd = input.split("-")
    (startAndEnd(0).toLong, startAndEnd(1).toLong)
  }
}


