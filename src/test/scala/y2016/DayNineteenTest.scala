package y2016

import com.github.dmitraver.adventofcode.y2016.DayNineteen
import org.scalatest.{FunSuite, Matchers}

class DayNineteenTest extends FunSuite with Matchers {

  test("Elf with all the presents") {
    DayNineteen.findLastElfWithAllPresents(Array.fill(5)(1)) shouldBe 3
  }
}
