package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.io.Source

object DayFour {

  val idAndChecksumPattern = "([0-9]+)\\[([a-zA-Z]+)\\]".r
  val letters = ('a' to 'z').toArray

  def main(args: Array[String]) {
    val sourceOne = ResourceLoader.fromResource("y2016/day4")
    println("Sum of sector ids of real rooms: " + sumOfRealRoomsSectorIds(sourceOne))

    val sourceTwo = ResourceLoader.fromResource("y2016/day4")
    println("Sector id of north pole objects room: " + findSectorIdOfNorthPoleObjectsRoom(sourceTwo))
  }

  def sumOfRealRoomsSectorIds(source: Source) = {
    source.getLines().map(toRoom).filter(isReal).foldLeft(0)((sum, room) => sum + room.id)
  }

  def findSectorIdOfNorthPoleObjectsRoom(source: Source) = {
    source.getLines().map(toEncryptedRoomNameWithId).map(roomNameWithId => (decryptRoomName(roomNameWithId._1, roomNameWithId._2), roomNameWithId._2)).find(roomWithId => isNorthPoleObjectsRoom(roomWithId._1)).get._2
  }

  def toEncryptedRoomNameWithId(encryptedData: String) = {
    val parts = encryptedData.split("-")
    val idAndChecksumPattern(id, checksum) = parts.last
    val roomNameParts = parts.dropRight(1)
    (roomNameParts, id.toInt)
  }

  def decryptRoomName(roomName: Array[String], sectorId: Int) = roomName.map(str => rotateEveryCharOfString(str, sectorId)).mkString(" ")

  def isNorthPoleObjectsRoom(roomName: String) = roomName == "northpole object storage"

  def rotateEveryCharOfString(str: String, rotations: Int) = str.map(c => rotateChar(c, rotations)).mkString

  def rotateChar(c: Char, rotations: Int): Char = {
    letters((letters.indexOf(c) + rotations % letters.length) % letters.length)
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

