package y2018

import com.github.dmitraver.adventofcode.y2018.Day8
import org.scalatest.{FunSuite, Matchers}

class Day8Test extends FunSuite with Matchers {

  test("Part one") {
    Day8.partOne("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2") shouldBe 138
  }

  test("Part two") {
    Day8.partTwo("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2") shouldBe 66
  }

}
