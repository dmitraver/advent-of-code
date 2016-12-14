package y2016

import com.github.dmitraver.adventofcode.y2016.DaySeven
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

class DaySevenTest extends FunSuite with Matchers {

  test("Count IPs with TLS") {
    DaySeven.countIPSSupportingTLS(Source.fromString("abba[mnop]qrst\nabcd[bddb]xyyx\naaaa[qwer]tyui\nioxxoj[asdfgh]zxcvbn").getLines().toList) shouldBe 2
  }

  test("Count IPs with SSL") {
    DaySeven.countIPSSupportingSSL(Source.fromString("aba[bab]xyz\nxyx[xyx]xyx\naaa[kek]eke\nzazbz[bzb]cdb").getLines().toList) shouldBe 3
  }
}
