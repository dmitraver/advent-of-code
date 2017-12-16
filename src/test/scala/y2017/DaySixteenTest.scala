package y2017

import com.github.dmitraver.adventofcode.y2017.DaySixteen
import org.scalatest.{FunSuite, Matchers}

class DaySixteenTest extends FunSuite with Matchers {

  test("Order after dance") {
    DaySixteen.dance("abcde", DaySixteen.parseMoves(Vector("s1", "x3/4", "pe/b"))) shouldBe "baedc"
  }
}
