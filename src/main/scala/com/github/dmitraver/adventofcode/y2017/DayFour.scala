package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader


object DayFour {

  def uniquenessCheck(password: String): Boolean = {
    val passphrases = password.split(" ")
    passphrases.distinct.length == passphrases.size
  }

  def noAnagramsCheck(password: String): Boolean = {
    val passphrases = password.split(" ").map(_.sorted)
    passphrases.distinct.length == passphrases.length
  }

  def countValidPasswords(input: Vector[String])(check: String => Boolean): Int = {
    input count check
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day4").getLines().toVector
    println("Valid passwords 1: " + countValidPasswords(input)(uniquenessCheck))
    println("Valid passwords 2: " + countValidPasswords(input)(noAnagramsCheck))
  }
}
