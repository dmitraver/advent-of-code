package y2016

import com.github.dmitraver.adventofcode.y2016.DayFifteen
import org.scalatest.{FunSuite, Matchers}

class DayFifteenTest extends FunSuite with Matchers {

  test("Find correct time to press the button") {
    DayFifteen.findTimeToPressButton(List("Disc #1 has 5 positions; at time=0, it is at position 4.", "Disc #2 has 2 positions; at time=0, it is at position 1.")) shouldBe 5
  }
}
