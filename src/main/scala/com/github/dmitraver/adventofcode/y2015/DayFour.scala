package com.github.dmitraver.adventofcode.y2015

import org.apache.commons.codec.digest.DigestUtils

object DayFour {

  def main(args: Array[String]) {
    val secret = "ckczppom"
    println("Number one: " + findNumberForNZerosHashPrefix(secret, 5))
    println("Number two: " + findNumberForNZerosHashPrefix(secret, 6))
  }

  def findNumberForNZerosHashPrefix(secret: String, n: Int) = {
    val prefix = "0" * n
    Iterator.from(1).dropWhile(n => !DigestUtils.md5Hex(secret + n).startsWith(prefix)).next
  }
}
