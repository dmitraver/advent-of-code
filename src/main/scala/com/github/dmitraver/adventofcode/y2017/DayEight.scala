package com.github.dmitraver.adventofcode.y2017

import com.github.dmitraver.adventofcode.utils.ResourceLoader

case class Instruction(register: String, op: Int => Int, condRegister: String, cond: Int => Boolean)

object DayEight {

  private val InstructionPattern = "(\\w+)\\s(inc|dec)\\s(-?\\d+)\\sif\\s(\\w+)\\s(>=|<=|<|>|!=|==)\\s(-?\\d+)".r

  def parseInstruction(instruction: String): Instruction = {
    instruction match {
      case InstructionPattern(register, op, opValue, registerCond, registerCondOp, registerCondOpValue) =>
        Instruction(register, parseRegisterOp(op, opValue.toInt), registerCond, parseCondOp(registerCondOp, registerCondOpValue.toInt))
    }
  }

  private def parseCondOp(op: String, opValue: Int): Int => Boolean = {
    registerValue =>
      op match {
        case "<=" =>  registerValue <= opValue
        case ">=" =>  registerValue >= opValue
        case "==" =>  registerValue == opValue
        case "!=" =>  registerValue != opValue
        case "<" =>   registerValue < opValue
        case ">" =>   registerValue > opValue
      }
  }

  private def parseRegisterOp(op: String, opValue: Int): Int => Int = {
    registerValue =>
      op match {
        case "inc" => registerValue + opValue
        case "dec" => registerValue - opValue
      }
  }

  def findLargestRegisterValue(input: Vector[String]): (Int, Int) = {
    val instructions = input map parseInstruction
    val registers = instructions.foldLeft((0, Map[String, Int]())) { (acc, instruction) =>
      val registers = acc._2
      val condRegisterValue = registers.getOrElse(instruction.condRegister, 0)
      val registerValue = registers.getOrElse(instruction.register, 0)
      if (instruction.cond(condRegisterValue)) {
        val newRegisterValue = instruction.op(registerValue)
        val maxRegister = acc._1
        val maxRegisterValueSoFar = if(newRegisterValue > maxRegister) newRegisterValue else maxRegister
        (maxRegisterValueSoFar, registers + (instruction.register -> newRegisterValue))
      }
      else acc
    }

    (registers._1, registers._2.maxBy(_._2)._2)
  }

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2017/day8").getLines().toVector
    println("Largest register value: " + findLargestRegisterValue(input)._2)
    println("Largest register value seen at all: " + findLargestRegisterValue(input)._1)
  }
}
