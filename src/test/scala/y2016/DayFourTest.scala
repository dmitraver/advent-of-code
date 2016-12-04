package y2016

import com.github.dmitraver.adventofcode.y2016.{DayFour, DayOne}
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DayFourTest extends FunSuite with Matchers {

  test("Sum of the sector IDs of the real rooms") {
    DayFour.sumOfRealRoomsSectorIds(Source.fromString("aaaaa-bbb-z-y-x-123[abxyz]\na-b-c-d-e-f-g-h-987[abcde]\nnot-a-real-room-404[oarel]\ntotally-real-room-200[decoy]")) shouldBe 1514
  }
}
