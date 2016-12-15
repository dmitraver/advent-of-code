package y2016

import com.github.dmitraver.adventofcode.y2016.DayFourteen
import org.scalatest.{FunSuite, Matchers}

class DayFourteenTest extends FunSuite with Matchers {

  test("Index that produces 64th one-time pad key") {
    DayFourteen.findIndexOfTheLastKey("abc") shouldBe 22728
  }
}
