package y2017

import com.github.dmitraver.adventofcode.y2017.DayEight
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayEightTest extends FunSuite with Matchers {

  val input =
    """b inc 5 if a > 1
      |a inc 1 if b < 5
      |c dec -10 if a >= 1
      |c inc -20 if c == 10""".stripMargin

  test("Largest register value") {
    DayEight.findLargestRegisterValue(Source.fromString(input).getLines().toVector)._2 shouldBe 1
    DayEight.findLargestRegisterValue(Source.fromString(input).getLines().toVector)._1 shouldBe 10
  }

}
