package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader
import Improvements._

object DayTen {

  val initInstructionPattern = "value (\\d*) goes to bot (\\d*)".r

  def main(args: Array[String]): Unit = {
    val instructions = ResourceLoader.fromResource("y2016/day10").getLines().toList
    val (initInstructions, giveChipInstructions) = instructions.partition {
      case initInstructionPattern(value, bot) => true
      case _ => false
    }

    val botsWithValues = init(initInstructions)
    println(botsWithValues)
  }

  def init(instructions: List[String], botsWithValues: Map[Int, List[Int]] = Map.empty): Map[Int, List[Int]] = instructions match {
    case initInstructionPattern(value, bot) :: tail => init(tail, botsWithValues.adjust(bot.toInt, Some(List(value.toInt))) (values => value.toInt :: values))
    case Nil => botsWithValues
  }


}

object Improvements {

  implicit class MapImprovements[A, B](map: Map[A, B]) {

    def adjust(key: A, default: Option[B] = None)(f: B => B): Map[A, B] = {
      map.get(key).fold(default.fold(map)(defaultValue => map.updated(key, defaultValue))) (value => map.updated(key, f(value)))
    }
  }
}


