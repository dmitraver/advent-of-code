package y2019

import com.github.dmitraver.adventofcode.y2019.Day2
import org.scalatest.{FunSuite, Matchers}

class Day2Test extends FunSuite with Matchers {

  test("Day 1 part 1") {
    Day2.runProgram(parseInput("1,0,0,0,99"), 0) shouldEqual Vector(2,0,0,0,99)
    Day2.runProgram(parseInput("2,3,0,3,99"), 0) shouldEqual Vector(2,3,0,6,99)
    Day2.runProgram(parseInput("2,4,4,5,99,0"), 0) shouldEqual Vector(2,4,4,5,99,9801)
    Day2.runProgram(parseInput("1,1,1,4,99,5,6,0,99"), 0) shouldEqual Vector(30,1,1,4,2,5,6,0,99)
  }

  private def parseInput(str: String): Vector[Int] = str.split(",").map(_.toInt).toVector
}
