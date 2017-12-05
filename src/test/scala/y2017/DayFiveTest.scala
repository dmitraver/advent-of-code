package y2017

import com.github.dmitraver.adventofcode.y2017.DayFive
import org.scalatest.{FunSuite, Matchers}

class DayFiveTest extends FunSuite with Matchers {

  test("Count steps 1") {
    DayFive.countSteps(Vector(0, 3, 0, 1, -3)) shouldBe 5
  }
}
