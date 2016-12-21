package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayTwentyOne {

  val swapPositionsPattern = "swap position (\\d*) with position (\\d*)".r
  val swapLettersPattern = "swap letter (\\w) with letter (\\w)".r
  val rotateLeftPattern = "rotate left (\\d*) steps?".r
  val rotateRightPattern = "rotate right (\\d*) steps?".r
  val rotateOnLetterPositionPattern = "rotate based on position of letter (\\w)".r
  val reversePositionsPattern = "reverse positions (\\d*) through (\\d*)".r
  val movePositionPattern = "move position (\\d*) to position (\\d*)".r


  def main(args: Array[String]): Unit = {
    val instructions = ResourceLoader.fromResource("y2016/day21").getLines().toList
    println("Scrambled password:" + scramblePassword("abcdefgh", instructions))
  }

  def scramblePassword(password: String, instructions: List[String]): String = instructions match {
    case Nil => password
    case swapPositionsPattern(x, y) :: tail => scramblePassword(swapPositions(password, x.toInt, y.toInt), tail)
    case swapLettersPattern(x, y) :: tail => scramblePassword(swapLetters(password, x(0), y(0)), tail)
    case rotateLeftPattern(steps) :: tail => scramblePassword(rotateLeft(password, steps.toInt), tail)
    case rotateRightPattern(steps) :: tail => scramblePassword(rotateRight(password, steps.toInt), tail)
    case rotateOnLetterPositionPattern(x) :: tail => scramblePassword(rotateOnLetterPosition(password, x(0)), tail)
    case reversePositionsPattern(x, y) :: tail => scramblePassword(reversePositions(password, x.toInt, y.toInt), tail)
    case movePositionPattern(x, y) :: tail => scramblePassword(movePosition(password, x.toInt, y.toInt), tail)
  }

  private def swapPositions(input: String, positionX: Int, positionY: Int) = {
    val chars = input.toCharArray
    val tmp = input(positionX)
    chars(positionX) = chars(positionY)
    chars(positionY) = tmp
    new String(chars)
  }

  private def swapLetters(input: String, letterX: Char, letterY: Char) = {
    val positionX = input.indexOf(letterX)
    val positionY = input.indexOf(letterY)
    swapPositions(input, positionX, positionY)
  }

  private def rotateLeft(input: String, steps: Int) = {
    val positions = steps % input.length
    input.drop(positions) + input.take(positions)
  }

  private def rotateRight(input: String, steps: Int) = {
    val positions = steps % input.length
    input.takeRight(positions) + input.dropRight(positions)
  }

  private def rotateOnLetterPosition(input: String, letter: Char) = {
    var letterIndex = input.indexOf(letter)
    if (letterIndex >= 4) letterIndex = letterIndex + 1
    rotateRight(input, letterIndex + 1)
  }

  private def reversePositions(input: String, start: Int, end: Int) = {
    val first = input.substring(0, start)
    val last = input.substring(end + 1)
    val middle = input.substring(start, end + 1)
    first + middle.reverse + last
  }

  private def movePosition(input: String, startPosition: Int, endPosition: Int) = {
    val chars = input.toCharArray
    val letter = chars(startPosition)
    var current = startPosition
    while (current != chars.length - 1) {
      chars(current) = chars(current + 1)
      current = current + 1
    }

    current = input.length - 1
    while (current != endPosition) {
      chars(current) = chars(current - 1)
      current = current - 1
    }

    chars(endPosition) = letter
    new String(chars)
  }
}
