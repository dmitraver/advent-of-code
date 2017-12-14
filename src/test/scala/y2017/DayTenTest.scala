package y2017

import com.github.dmitraver.adventofcode.y2017.DayTen
import org.scalatest.{FunSuite, Matchers}

class DayTenTest extends FunSuite with Matchers {

  test("Hash") {
    DayTen.hash(Vector(3, 4, 1, 5), 5) shouldBe 12
  }

  test("Dense hash") {
    DayTen.denseHash("") shouldBe "a2582a3a0e66e6e86e3812dcb672a272"
    DayTen.denseHash("AoC 2017") shouldBe "33efeb34ea91902bb2f59c9920caa6cd"
    DayTen.denseHash("1,2,3") shouldBe "3efbe78a8d82f29979031a4aa0b16a9d"
    DayTen.denseHash("1,2,4") shouldBe "63960835bcdc130f0b66d7ff4f6a5a8e"
  }
}
