package y2017

import com.github.dmitraver.adventofcode.y2017.{DayOne, DayTwo}
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayTwoTest extends FunSuite with Matchers {

  private val input =
    """|5 1 9 5
       |7 5 3
       |2 4 6 8""".stripMargin

  test("Checksum part 1") {
    DayTwo.calculateChecksum(Source.fromString(input).getLines()) shouldBe 18
  }
}
