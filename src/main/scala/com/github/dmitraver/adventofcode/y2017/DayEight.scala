package com.github.dmitraver.adventofcode.y2017

/**
  * bfwe inc 5 if ava > 1
  * c dec -10 if a >= 1
  */
case class Instruction(register: String, op: String, opValue: Int, condition: String => Boolean)

object DayEight {

  private val InstructionPattern = "(\\w+)\\s(inc|dec)\\s(-?\\d+)\\sif\\s(\\w+)\\s(<|>|>=|<=|!=|==)\\s(\\d)".r

  def parseInstruction(instruction: String): Instruction = {
    instruction match {
      case InstructionPattern(register, op, opValue, registerCond, registerCondOp, registerCondOpValue) =>

    }
  }

  def main(args: Array[String]): Unit = {

  }
}
