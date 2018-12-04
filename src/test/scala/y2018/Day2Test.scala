package y2018

import com.github.dmitraver.adventofcode.y2018.Day2
import org.scalatest.{FunSuite, Matchers}

class Day2Test extends FunSuite with Matchers {

  test("Checksum") {
    Day2.checksum(Vector("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")) shouldBe 12
  }

  test("Common letters") {
    Day2.commonLetters(Vector(
      "abcde",
      "fghij",
      "klmno",
      "pqrst",
      "fguij",
      "axcye",
      "wvxyz")) shouldBe "fgij"
  }
}
