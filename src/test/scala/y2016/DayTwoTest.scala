package y2016

import com.github.dmitraver.adventofcode.y2016.DayTwo
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayTwoTest extends FunSuite with Matchers {

  test("Find bathroom code") {
    DayTwo.getBathroomCode(Source.fromString("ULL\nRRDDD\nLURDL\nUUUUD")) shouldBe List(1,9,8,5)
  }
}
