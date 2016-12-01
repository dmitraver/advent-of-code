package y2016

import com.github.dmitraver.adventofcode.y2016.DayOne
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayOneTest extends FunSuite with Matchers {

  test("Calculate distance to Easter Bunny HQ") {
    DayOne.easterBunnyHQDistance(Source.fromString("R2, L3")) shouldBe 5
    DayOne.easterBunnyHQDistance(Source.fromString("R2, R2, R2")) shouldBe 2
    DayOne.easterBunnyHQDistance(Source.fromString("R5, L5, R5, R3")) shouldBe 12
  }
}
