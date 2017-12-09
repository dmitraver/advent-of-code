package y2017

import com.github.dmitraver.adventofcode.y2017.DaySeven
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DaySevenTest extends FunSuite with Matchers {

  val input =
    """pbga (66)
      |xhth (57)
      |ebii (61)
      |havc (66)
      |ktlj (57)
      |fwft (72) -> ktlj, cntj, xhth
      |qoyq (66)
      |padx (45) -> pbga, havc, qoyq
      |tknk (41) -> ugml, padx, fwft
      |jptl (61)
      |ugml (68) -> gyxo, ebii, jptl
      |gyxo (61)
      |cntj (57)""".stripMargin

  test("Bottom program name") {
    val programs = DaySeven.parseInput(Source.fromString(input).getLines().toVector)
    DaySeven.findBottomProgram(programs) shouldBe "tknk"
  }

  test("Corrected program weight") {
    val programs = DaySeven.parseInput(Source.fromString(input).getLines().toVector)
    val bottomProgram = DaySeven.findBottomProgram(programs)
    DaySeven.getCorrectedProgramWeight(bottomProgram, programs) shouldBe 60
  }

}
