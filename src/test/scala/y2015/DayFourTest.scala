package y2015

import com.github.dmitraver.adventofcode.y2015.DayFour
import org.scalatest.{FunSuite, Matchers}

class DayFourTest extends FunSuite with Matchers {

  test("Find number for 5 zero hash prefix") {
    DayFour.findNumberForNZerosHashPrefix("abcdef", 5) shouldBe 609043
    DayFour.findNumberForNZerosHashPrefix("pqrstuv", 5) shouldBe 1048970
  }
}
