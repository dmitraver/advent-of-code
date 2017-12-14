package y2017

import com.github.dmitraver.adventofcode.y2017.DayThirteen
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayThirteenTest extends FunSuite with Matchers {

  val input =
    """0: 3
      |1: 2
      |4: 4
      |6: 4""".stripMargin

  test("Severity") {
    val layers = DayThirteen.processInput(Source.fromString(input).getLines().toVector)
    DayThirteen.getSeverity(layers) shouldBe 24
  }

  test("Delay") {
    val layers = DayThirteen.processInput(Source.fromString(input).getLines().toVector)
    DayThirteen.calculateDelay(layers) shouldBe 10
  }

}
