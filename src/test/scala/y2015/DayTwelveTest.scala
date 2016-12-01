package y2015

import com.github.dmitraver.adventofcode.y2015.DayTwelve
import org.scalatest.{FunSuite, Matchers}
import play.api.libs.json.Json

class DayTwelveTest extends FunSuite with Matchers {

  test("sumAllNumbers should return correct result") {
    DayTwelve.sumAllNumbers(Json.parse("[1,2,3]")) shouldBe 6
    DayTwelve.sumAllNumbers(Json.parse("{\"a\":2,\"b\":4}")) shouldBe 6

    DayTwelve.sumAllNumbers(Json.parse("[[[3]]]")) shouldBe 3
    DayTwelve.sumAllNumbers(Json.parse("{\"a\":{\"b\":4},\"c\":-1}")) shouldBe 3

    DayTwelve.sumAllNumbers(Json.parse("{\"a\":[-1,1]}")) shouldBe 0
    DayTwelve.sumAllNumbers(Json.parse("[-1,{\"a\":1}]")) shouldBe 0

    DayTwelve.sumAllNumbers(Json.parse("[]")) shouldBe 0
    DayTwelve.sumAllNumbers(Json.parse("{}")) shouldBe 0
  }

  test("sumAllNumbersWithRedFiltering should return correct result") {
    DayTwelve.sumAllNumbersWithRedFiltering(Json.parse("[1,2,3]")) shouldBe 6
    DayTwelve.sumAllNumbersWithRedFiltering(Json.parse("[1,{\"c\":\"red\",\"b\":2},3]")) shouldBe 4

    DayTwelve.sumAllNumbersWithRedFiltering(Json.parse("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}")) shouldBe 0
    DayTwelve.sumAllNumbersWithRedFiltering(Json.parse("[1,\"red\",5]")) shouldBe 6
  }
}
