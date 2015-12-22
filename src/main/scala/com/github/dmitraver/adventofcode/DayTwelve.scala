package com.github.dmitraver.adventofcode

import play.api.libs.json._

object DayTwelve {

  def main(args: Array[String]) {
    val input = getClass.getClassLoader.getResourceAsStream("day12")
    val json = Json.parse(input)
    println("Sum of all numbers:" + sumAllNumbers(json))
    println("Sum of all numbers without red:" + sumAllNumbersWithRedFiltering(json))
  }

  def sumAllNumbers(json: JsValue, sum: Double = 0): Double = json match {
    case JsObject(bindings) => bindings.foldLeft(sum) {
      case (total, (key, value)) => total + sumAllNumbers(value, sum)
    }
    case JsArray(values) => values.foldLeft(sum)((total, value) => total + sumAllNumbers(value, sum))
    case JsNumber(n) => n.toDouble
    case _ => 0
  }

  def sumAllNumbersWithRedFiltering(json: JsValue, sum: Double = 0): Double = json match {
    case JsObject(bindings) =>
      if (bindings.exists(_._2 == JsString("red"))) 0
      else {
        bindings.foldLeft(sum) {
          case (total, (key, value)) => total + sumAllNumbersWithRedFiltering(value, sum)
        }
      }
    case JsArray(values) => values.foldLeft(sum)((total, value) => total + sumAllNumbersWithRedFiltering(value, sum))
    case JsNumber(n) => n.toDouble
    case _ => 0
  }

}
