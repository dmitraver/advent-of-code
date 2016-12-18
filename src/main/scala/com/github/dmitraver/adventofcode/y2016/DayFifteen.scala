package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader


object DayFifteen {

  val diskPattern = "Disc #(\\d*) has (\\d*) positions; at time=0, it is at position (\\d*)\\.".r

  def main(args: Array[String]) {
    val instructions = ResourceLoader.fromResource("y2016/day15").getLines().toList
    println("First time to press the button: " + findTimeToPressButton(instructions))
  }

  def findTimeToPressButton(instructions: List[String]) = {
    val disks = init(instructions)
    Stream.from(0).find(time => isRightTime(time, disks)).get
  }

  private def isRightTime(time: Int, disks: List[Disk]) = {
    disks.map(disk => disk.atTime(time + disk.diskNumber).currentPosition).toSet.size == 1
  }

  private def init(instructions: List[String]): List[Disk] = instructions match {
    case Nil => Nil
    case diskPattern(diskNumber, positions, initialPosition) :: tail =>
      Disk(diskNumber.toInt, positions.toInt, initialPosition.toInt, initialPosition.toInt) :: init(tail)
  }
}

case class Disk(diskNumber: Int, positions: Int, initialPosition: Int, currentPosition: Int)  {
  def atTime(time: Int) = copy(currentPosition =  (initialPosition + time) % positions)
}




