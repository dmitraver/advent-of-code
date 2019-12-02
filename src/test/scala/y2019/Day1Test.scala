package y2019

import com.github.dmitraver.adventofcode.y2019.Day1
import org.scalatest.{FunSuite, Matchers}

class Day1Test extends FunSuite with Matchers {

  test("Count total required fuel") {
    Day1.countRequiredFuel(12) shouldEqual 2
    Day1.countRequiredFuel(14) shouldEqual 2
    Day1.countRequiredFuel(1969) shouldEqual 654
    Day1.countRequiredFuel(100756) shouldEqual 33583
  }

  test("Count total required fuel recursive") {
    Day1.countRequiredFuelRecursive(14) shouldEqual 2
    Day1.countRequiredFuelRecursive(1969) shouldEqual 966
    Day1.countRequiredFuelRecursive(100756) shouldEqual 50346
  }
}
