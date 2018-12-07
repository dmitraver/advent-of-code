package y2018

import com.github.dmitraver.adventofcode.y2018.{Day5, Day7}
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class Day7Test extends FunSuite with Matchers {

  val input =
    """Step C must be finished before step A can begin.
      |Step C must be finished before step F can begin.
      |Step A must be finished before step B can begin.
      |Step A must be finished before step D can begin.
      |Step B must be finished before step E can begin.
      |Step D must be finished before step E can begin.
      |Step F must be finished before step E can begin.""".stripMargin

  test("Part one") {
    Day7.partOne(Source.fromString(input).getLines().toVector) shouldBe "CABDFE"
  }
}
