package y2016

import com.github.dmitraver.adventofcode.y2016.DaySixteen
import org.scalatest.{FunSuite, Matchers}

class DaySixteenTest extends FunSuite with Matchers {

  test("Apply dragon curve extension") {
    DaySixteen.applyDragonCurve("1", 3) shouldBe "100"
    DaySixteen.applyDragonCurve("0", 3) shouldBe "001"
    DaySixteen.applyDragonCurve("11111", 11) shouldBe "11111000000"
    DaySixteen.applyDragonCurve("111100001010", 25) shouldBe "1111000010100101011110000"
   }

  test("Calculate checksum") {
    DaySixteen.calculateChecksum("110010110100") shouldBe "100"
  }

  test("Calculate checksum for disk") {
    DaySixteen.calculateChecksumForDisk("10000", 20) shouldBe "01100"
  }
}
