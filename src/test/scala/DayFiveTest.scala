import com.github.dmitraver.adventofcode.DayFive
import org.scalatest.{FunSuite, Matchers}

class DayFiveTest extends FunSuite with Matchers {

  test("isNice should return correct result") {
    DayFive.isNice("ugknbfddgicrmopn") shouldBe true
    DayFive.isNice("aaa") shouldBe true
    DayFive.isNice("jchzalrnumimnmhp") shouldBe false
    DayFive.isNice("haegwjzuvuyypxyu") shouldBe false
    DayFive.isNice("dvszwmarrgswjxmb") shouldBe false
  }
}
