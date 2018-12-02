package y2018

import com.github.dmitraver.adventofcode.y2018.DayOne
import org.scalatest.{FunSuite, Matchers}

class DayOneTest extends FunSuite with Matchers {

  test("Frequency") {
    DayOne.frequency(Vector("+1", "+1", "+1")) shouldBe 3
    DayOne.frequency(Vector("+1", "+1", "-2")) shouldBe 0
    DayOne.frequency(Vector("-1", "-2", "-3")) shouldBe -6
  }
}
