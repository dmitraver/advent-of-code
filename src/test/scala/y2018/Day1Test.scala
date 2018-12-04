package y2018

import com.github.dmitraver.adventofcode.y2018.Day1
import org.scalatest.{FunSuite, Matchers}

class Day1Test extends FunSuite with Matchers {

  test("Frequency") {
    Day1.frequency(Vector("+1", "+1", "+1")) shouldBe 3
    Day1.frequency(Vector("+1", "+1", "-2")) shouldBe 0
    Day1.frequency(Vector("-1", "-2", "-3")) shouldBe -6
  }

  test("Repeated frequency") {
    Day1.frequency2(Vector("+1", "+1")) shouldBe 0
    Day1.frequency2(Vector("+3", "+3", "+4", "-2", "-4")) shouldBe 10
    Day1.frequency2(Vector("-6", "+3", "+8", "+5", "-6")) shouldBe 5
    Day1.frequency2(Vector("+7", "+7", "-2", "-7", "-4")) shouldBe 14
  }

}
