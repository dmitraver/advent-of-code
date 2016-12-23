package y2016

import com.github.dmitraver.adventofcode.y2016.DayTwentyThree
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayTwentyThreeTest extends FunSuite with Matchers {

  test("Register 'a' value") {
    DayTwentyThree.processInstructions(Source.fromString("cpy 2 a\ntgl a\ntgl a\ntgl a\ncpy 1 a\ndec a\ndec a").getLines().toList, Map('a' -> 0, 'b' -> 0, 'c' -> 0, 'd' -> 0))('a') shouldBe 3
  }
}
