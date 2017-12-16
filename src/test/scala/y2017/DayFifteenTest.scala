package y2017

import com.github.dmitraver.adventofcode.y2017.DayFifteen
import org.scalatest.{FunSuite, Matchers}

class DayFifteenTest extends FunSuite with Matchers {

  test("Judges count") {
    DayFifteen.judgeCount(65, 8921) shouldBe 588
  }

  test("Judges final count") {
    DayFifteen.judgeFinalCount(65, 8921) shouldBe 309
  }

}
