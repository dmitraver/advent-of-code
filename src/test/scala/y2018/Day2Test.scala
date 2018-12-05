package y2018

import com.github.dmitraver.adventofcode.y2018.Day2
import org.scalatest.{FunSuite, Matchers}

class Day2Test extends FunSuite with Matchers {

  test("Checksum") {
    Day2.checksum(Vector("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")) shouldBe 12
  }
}
