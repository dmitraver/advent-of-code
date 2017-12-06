package y2017

import com.github.dmitraver.adventofcode.y2017.DaySix
import org.scalatest.{FunSuite, Matchers}

class DaySixTest extends FunSuite with Matchers {

  test("Count cycles 1") {
    DaySix.countRedistributionCycles(Vector(0, 2, 7, 0)) shouldBe 5
  }
}
