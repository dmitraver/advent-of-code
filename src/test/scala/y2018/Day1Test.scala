package y2018

import com.github.dmitraver.adventofcode.y2018.Day1
import org.scalatest.{FunSuite, Matchers}

class Day1Test extends FunSuite with Matchers {

  test("Frequency") {
    Day1.frequency(Vector("+1", "+1", "+1")) shouldBe 3
    Day1.frequency(Vector("+1", "+1", "-2")) shouldBe 0
    Day1.frequency(Vector("-1", "-2", "-3")) shouldBe -6
  }
}
