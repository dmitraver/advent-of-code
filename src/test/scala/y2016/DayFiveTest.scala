package y2016

import com.github.dmitraver.adventofcode.y2016.DayFive
import org.scalatest.{FunSuite, Matchers}

class DayFiveTest extends FunSuite with Matchers {

  test("Decrypt password given door id") {
    DayFive.decryptPassword("abc") shouldBe "18f47a30"
  }
}
