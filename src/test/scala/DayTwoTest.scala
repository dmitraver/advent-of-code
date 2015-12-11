import com.github.dmitraver.adventofcode.{DayTwo, DayOne}
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayTwoTest extends FunSuite with Matchers {

   test("Count total paper should return correct result") {
     DayTwo.totalPaper(Source.fromString("2x3x4")) shouldBe 58
     DayTwo.totalPaper(Source.fromString("1x1x10")) shouldBe 43
   }

  test("Count total ribbon should return correct result") {
    DayTwo.totalRibbon(Source.fromString("2x3x4")) shouldBe 34
    DayTwo.totalRibbon(Source.fromString("1x1x10")) shouldBe 14
  }

 }
