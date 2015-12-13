import com.github.dmitraver.adventofcode.DayThree
import org.scalatest.{Matchers, FunSuite}

import scala.io.Source

class DayThreeTest extends FunSuite with Matchers {

  test("Count houses should return correct result") {
    DayThree.countHouses(Source.fromString(">").toStream) shouldBe 2
    DayThree.countHouses(Source.fromString("^>v<").toStream) shouldBe 4
    DayThree.countHouses(Source.fromString("^v^v^v^v^v").toStream) shouldBe 2
  }

  test("Count houses with robo should return correct result") {
    DayThree.countHousesWithRobo(Source.fromString("^v").toStream) shouldBe 3
    DayThree.countHousesWithRobo(Source.fromString("^>v<").toStream) shouldBe 3
    DayThree.countHousesWithRobo(Source.fromString("^v^v^v^v^v").toStream) shouldBe 11
  }
}
