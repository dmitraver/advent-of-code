package y2017

import com.github.dmitraver.adventofcode.y2017.DaySeventeen.bufferStream
import org.scalatest.{FunSuite, Matchers}

class DaySeventeenTest extends FunSuite with Matchers {

  test("Value after 2017") {
    val buffer = bufferStream(3).drop(2017).head
    val index = buffer.indexOf(2017)
    buffer(index + 1) shouldBe 638
  }

}
