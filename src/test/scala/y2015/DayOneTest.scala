package y2015

import com.github.dmitraver.adventofcode.y2015.DayOne
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayOneTest extends FunSuite with Matchers {

  test("Count floors should return correct result") {
    DayOne.countFloors(Source.fromString("(())")) shouldBe 0
    DayOne.countFloors(Source.fromString("()()")) shouldBe 0

    DayOne.countFloors(Source.fromString("(((")) shouldBe 3
    DayOne.countFloors(Source.fromString("(()(()(")) shouldBe 3
    DayOne.countFloors(Source.fromString("))(((((")) shouldBe 3

    DayOne.countFloors(Source.fromString("())")) shouldBe -1
    DayOne.countFloors(Source.fromString("))(")) shouldBe -1

    DayOne.countFloors(Source.fromString(")))")) shouldBe -3
    DayOne.countFloors(Source.fromString(")())())")) shouldBe -3
  }

  test("Find basement should return correct result") {
    DayOne.findBasement(Source.fromString(")")) shouldBe 1
    DayOne.findBasement(Source.fromString("()())")) shouldBe 5
  }

}
