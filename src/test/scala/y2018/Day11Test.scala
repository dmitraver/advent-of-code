package y2018

import com.github.dmitraver.adventofcode.y2018.Day11
import org.scalatest.{FunSuite, Matchers}

class Day11Test extends FunSuite with Matchers {

  test("Power level calculation") {
    Day11.powerLevel((3, 5), 8) shouldBe 4
    Day11.powerLevel((122, 79), 57) shouldBe -5
    Day11.powerLevel((217, 196), 39) shouldBe 0
    Day11.powerLevel((101, 153), 71) shouldBe 4
  }

  test("Part one") {
    Day11.partOne(18) shouldBe (33,45)
    Day11.partOne(42) shouldBe (21,61)
  }
}
