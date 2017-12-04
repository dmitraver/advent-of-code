package y2017

import com.github.dmitraver.adventofcode.y2017.DayFour
import org.scalatest.{FunSuite, Matchers}

class DayFourTest extends FunSuite with Matchers {

  test("Valid passwords 1") {
    DayFour.uniquenessCheck("a bb cc dd ee") shouldBe true
    DayFour.uniquenessCheck("aa bb cc dd aa") shouldBe false
    DayFour.uniquenessCheck("aa bb cc dd aaa") shouldBe true
  }

  test("Valid passwords 2") {
    DayFour.noAnagramsCheck("abcde fghij") shouldBe true
    DayFour.noAnagramsCheck("abcde xyz ecdab") shouldBe false
    DayFour.noAnagramsCheck("a ab abc abd abf abj") shouldBe true
    DayFour.noAnagramsCheck("iiii oiii ooii oooi oooo") shouldBe true
    DayFour.noAnagramsCheck("oiii ioii iioi iiio") shouldBe false
  }
}
