package y2017

import atto.Atto._
import com.github.dmitraver.adventofcode.y2017.DayNine._
import org.scalatest.{FunSuite, Matchers}

class DayNineTest extends FunSuite with Matchers {

  test("Total score") {
    totalScore(groupParser.parse("{}").option.get) shouldBe 1
    totalScore(groupParser.parse("{{{}}}").option.get) shouldBe 6
    totalScore(groupParser.parse("{{},{}}").option.get) shouldBe 5
    totalScore(groupParser.parse("{{{},{},{{}}}}").option.get) shouldBe 16
    totalScore(groupParser.parse("{<a>,<a>,<a>,<a>}").option.get) shouldBe 1
    totalScore(groupParser.parse("{{<ab>},{<ab>},{<ab>},{<ab>}}").option.get) shouldBe 9
    totalScore(groupParser.parse("{{<!!>},{<!!>},{<!!>},{<!!>}}").option.get) shouldBe 9
    totalScore(groupParser.parse("{{<a!>},{<a!>},{<a!>},{<ab>}}").option.get) shouldBe 3
  }

}
