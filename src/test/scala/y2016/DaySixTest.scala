package y2016

import com.github.dmitraver.adventofcode.y2016.DaySix
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DaySixTest extends FunSuite with Matchers {

  test("Error-corrected version with ") {
    DaySix.decodeCorrectMessage(Source.fromString("eedadn\ndrvtee\neandsr\nraavrd\natevrs\ntsrnev\nsdttsa\nrasrtv\nnssdts\nntnada\nsvetve\ntesnvt\nvntsnd\nvrdear\ndvrsen\nenarar")) shouldBe "easter"
    DaySix.decodeCorrectMessage(Source.fromString("eedadn\ndrvtee\neandsr\nraavrd\natevrs\ntsrnev\nsdttsa\nrasrtv\nnssdts\nntnada\nsvetve\ntesnvt\nvntsnd\nvrdear\ndvrsen\nenarar"), pickMostFrequent = false) shouldBe "advent"
  }
}
