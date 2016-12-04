package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.io.Source

object DayFour {

  val idAndChecksumPattern = "([0-9]+)\\[([a-zA-Z]+)\\]".r

  def main(args: Array[String]) {
    val source = ResourceLoader.fromResource("y2016/day4")
    println("Sum of sector ids of real rooms: " + sumOfRealRoomsSectorIds(source))
  }

  def sumOfRealRoomsSectorIds(source: Source) = {
    source.getLines().map(toRoom).filter(isReal).foldLeft(0)((sum, room) => sum + room.id)
  }

  def isReal(room: Room) = {
    val name = room.encryptedName.groupBy(_.toChar).map(p => (p._1, p._2.length)).toList.sortBy(x => (- x._2, x._1)).take(5).map(_._1).mkString
    name == room.checksum
  }

  def toRoom(encryptedRoomData: String) = {
    val parts = encryptedRoomData.split("-")
    val encryptedName = parts.dropRight(1)
    val idAndChecksumPattern(id, checksum) = parts.last
    Room(encryptedName.mkString(""), id.toInt, checksum)
  }


}

case class Room(encryptedName: String, id: Int, checksum: String)

