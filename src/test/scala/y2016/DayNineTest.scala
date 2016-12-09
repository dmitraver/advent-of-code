package y2016

import com.github.dmitraver.adventofcode.y2016.DayNine
import org.scalatest.{FunSuite, Matchers}

class DayNineTest extends FunSuite with Matchers {

  test("Decompress strings") {
    DayNine.decompress("ADVENT") shouldBe "ADVENT"
    DayNine.decompress("A(1x5)BC") shouldBe "ABBBBBC"
    DayNine.decompress("(3x3)XYZ") shouldBe "XYZXYZXYZ"
    DayNine.decompress("(6x1)(1x3)A") shouldBe "(1x3)A"
    DayNine.decompress("X(8x2)(3x3)ABCY") shouldBe "X(3x3)ABC(3x3)ABCY"
    DayNine.decompress("A(2x2)BCD(2x2)EFG") shouldBe "ABCBCDEFEFG"
  }
}
