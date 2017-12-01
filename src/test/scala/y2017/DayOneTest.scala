package y2017

import com.github.dmitraver.adventofcode.y2017.DayOne
import org.scalatest.{FunSuite, Matchers}

class DayOneTest extends FunSuite with Matchers {

  test("Captcha part 1") {
    DayOne.captchaOne("1122".toVector) shouldEqual 3
    DayOne.captchaOne("1111".toVector) shouldEqual 4
    DayOne.captchaOne("1234".toVector) shouldEqual 0
    DayOne.captchaOne("91212129".toVector) shouldEqual 9
  }

  test("Captcha part 2") {
    DayOne.captchaTwo("1212".toVector) shouldEqual 6
    DayOne.captchaTwo("1221".toVector) shouldEqual 0
    DayOne.captchaTwo("123425".toVector) shouldEqual 4
    DayOne.captchaTwo("123123".toVector) shouldEqual 12
    DayOne.captchaTwo("12131415".toVector) shouldEqual 4
  }
}
