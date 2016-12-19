package y2016

import com.github.dmitraver.adventofcode.y2016.DayEighteen
import org.scalatest.{FunSuite, Matchers}

class DayEighteenTest extends FunSuite with Matchers {

  test("Find the number of safe tiles") {
    DayEighteen.countSafeTiles(".^^.^.^^^^", 10) shouldBe 38
  }
}
