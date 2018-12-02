package y2018

import com.github.dmitraver.adventofcode.y2018.DayTwo
import org.scalatest.{FunSuite, Matchers}

class DayTwoTest extends FunSuite with Matchers {

  test("Checksum") {
    DayTwo.checksum(Vector("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")) shouldBe 12
  }
}
