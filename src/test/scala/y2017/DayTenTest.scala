package y2017

import com.github.dmitraver.adventofcode.y2017.DayTen
import org.scalatest.{FunSuite, Matchers}

class DayTenTest extends FunSuite with Matchers {

  test("Hash") {
    DayTen.hash(Vector(3, 4, 1, 5), 5) shouldBe 12
  }
}
