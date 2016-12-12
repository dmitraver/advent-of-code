package y2016

import com.github.dmitraver.adventofcode.y2016.DayTwelve
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayTwelveTest extends FunSuite with Matchers {

  test("Register 'a' value") {
    DayTwelve.processInstructions(Source.fromString("cpy 41 a\ninc a\ninc a\ndec a\njnz a 2\ndec a").getLines().toList, Map('a' -> 0, 'b' -> 0, 'c' -> 0, 'd' -> 0))('a') shouldBe 42
  }
}
