package y2018

import com.github.dmitraver.adventofcode.y2018.Day5
import org.scalatest.{FunSuite, Matchers}

class Day5Test extends FunSuite with Matchers {

  test("Part one") {
    Day5.partOne("dabAcCaCBAcCcaDA") shouldBe 10
  }

  test("Part two") {
    Day5.partTwo("dabAcCaCBAcCcaDA") shouldBe 4
  }
}
