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

  test("Total garbage length") {
    totalGarbageLength(garbageParser.parse("<>").option.get) shouldBe 0
    totalGarbageLength(garbageParser.parse("<random characters>").option.get) shouldBe 17
    totalGarbageLength(garbageParser.parse("<<<<>").option.get) shouldBe 3
    totalGarbageLength(garbageParser.parse("<{!>}>").option.get) shouldBe 2
    totalGarbageLength(garbageParser.parse("<!!>").option.get) shouldBe 0
    totalGarbageLength(garbageParser.parse("<!!!>>").option.get) shouldBe 0
    totalGarbageLength(garbageParser.parse("<{o\"i!a,<{i<a>").option.get) shouldBe 10
  }

}
