package y2016

import com.github.dmitraver.adventofcode.y2016.DayTwenty
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayTwentyTest extends FunSuite with Matchers {

  test("Lowest IP value") {
    DayTwenty.findLowestValueIP(Source.fromString("5-8\n0-2\n4-7").getLines().toList) shouldBe 3
   }
}
