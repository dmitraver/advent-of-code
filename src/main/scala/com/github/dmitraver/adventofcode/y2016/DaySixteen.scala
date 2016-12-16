package com.github.dmitraver.adventofcode.y2016

object DaySixteen {

  def main(args: Array[String]) {
    println("Checksum for disk 1: " + calculateChecksumForDisk("10001001100000001", 272))
    println("Checksum for disk 2: " + calculateChecksumForDisk("10001001100000001", 35651584))
  }

  def applyDragonCurve(input: String, desiredLength: Int): String = {
    if (input.length >= desiredLength) input.substring(0, desiredLength)
    else applyDragonCurve(input + '0' + input.reverse.map(c => if (c == '1') '0' else '1').mkString, desiredLength)
  }

  def calculateChecksum(input: String): String = {
    val checksum = input.sliding(2,2).map(pair => if (pair(0) == pair(1)) "1" else "0").mkString
    if (checksum.length % 2 != 0) checksum else calculateChecksum(checksum)
  }

  def calculateChecksumForDisk(input: String, desiredDiskLength: Int) = calculateChecksum(applyDragonCurve(input, desiredDiskLength))
}
