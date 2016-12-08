package y2016

import com.github.dmitraver.adventofcode.y2016.{Screen, DayEight}
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayEightTest extends FunSuite with Matchers {

  test("Number of pixels lit") {
    DayEight.readAndApplyCommands(Screen(3, 7), Source.fromString("rect 3x2\nrotate column x=1 by 1\nrotate row y=0 by 4\nrotate column x=1 by 1")).countPixelsLit() shouldBe 6
  }
}
