package y2017

import com.github.dmitraver.adventofcode.y2017.{DayOne, DayTwo}
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayTwoTest extends FunSuite with Matchers {

  private val inputPartOne =
    """|5 1 9 5
       |7 5 3
       |2 4 6 8""".stripMargin

  private val inputPartTwo =
    """|5 9 2 8
       |9 4 7 3
       |3 8 6 5""".stripMargin

  test("Checksum part 1") {
    DayTwo.calculateChecksum(Source.fromString(inputPartOne).getLines().toStream)(DayTwo.minMaxDiffCalc) shouldBe 18
  }

  test("Checksum part 2") {
    DayTwo.calculateChecksum(Source.fromString(inputPartTwo).getLines().toStream)(DayTwo.evenlyDivisibleCalc) shouldBe 9
  }
}
