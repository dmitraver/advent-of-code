package y2017

import com.github.dmitraver.adventofcode.y2017.DayTwelve
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayTwelveTest extends FunSuite with Matchers {

  val input =
    """|0 <-> 2
       |1 <-> 1
       |2 <-> 0, 3, 4
       |3 <-> 2, 4
       |4 <-> 2, 3, 6
       |5 <-> 6
       |6 <-> 4, 5""".stripMargin

  test("Count group size that has 0 in it") {
    val graph = DayTwelve.makeGraph(Source.fromString(input).getLines().toVector)
    DayTwelve.countGroupSize(graph) shouldBe 6
  }

}
