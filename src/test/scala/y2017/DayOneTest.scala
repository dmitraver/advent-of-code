package y2017

import com.github.dmitraver.adventofcode.y2017.DayOne
import org.scalatest.{FunSuite, Matchers}

class DayOneTest extends FunSuite with Matchers {

  test("Captcha part 1") {
    DayOne.captcha("1122".toVector) shouldEqual 3
    DayOne.captcha("1111".toVector) shouldEqual 4
    DayOne.captcha("1234".toVector) shouldEqual 0
    DayOne.captcha("91212129".toVector) shouldEqual 9
  }
}
