package com.github.dmitraver.adventofcode.y2016

import org.apache.commons.codec.digest.DigestUtils

object DayFive {

  val hexPrefix = "00000"

  def main(args: Array[String]): Unit = {
    println("Password: " + decryptPassword("cxdnnyjw"))
  }

  def decryptPassword(doorId: String, passwordLength: Int = 8, passwordCharPosition: Int = 5) = {
    Stream.from(1).map(x => DigestUtils.md5Hex(doorId + x)).filter(hex => hex.startsWith(hexPrefix)).map(hex => hex(passwordCharPosition)).take(passwordLength).mkString
  }
}
