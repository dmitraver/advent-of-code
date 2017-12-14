package y2017

import com.github.dmitraver.adventofcode.y2017.DayFourteen
import org.scalatest.{FunSuite, Matchers}

class DayFourteenTest extends FunSuite with Matchers {

  test("Count used squares") {
    DayFourteen.countUsedSquaresInGrid("flqrgnkx") shouldBe 8108
  }

}
