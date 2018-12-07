package y2018

import com.github.dmitraver.adventofcode.y2018.Day3
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class Day3Test extends FunSuite with Matchers {

  val input =
    """#1 @ 1,3: 4x4
      |#2 @ 3,1: 4x4
      |#3 @ 5,5: 2x2""".stripMargin

  test("Frequency") {
    Day3.partOne(Source.fromString(input).getLines().toVector) shouldBe 4
  }
}
