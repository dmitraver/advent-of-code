package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayTwelve {

  val copyValueCommandPattern = "cpy (\\d*) (\\w)".r
  val copyRegisterCommandPattern = "cpy (\\w) (\\w)".r
  val incrementCommandPattern = "inc (\\w)".r
  val decrementCommandPattern = "dec (\\w)".r
  val jumpRegisterCommandPattern = "jnz ([a-z]) (-?\\d*)".r
  val jumpValueCommandPattern = "jnz (\\d*) (-?\\d*)".r

  def main(args: Array[String]): Unit = {
    val instructions = ResourceLoader.fromResource("y2016/day12").getLines().toList
    val registersOne = Map('a' -> 0, 'b' -> 0, 'c' -> 0, 'd' -> 0)
    println("(Part 1) Register 'a': " + processInstructions(instructions, registersOne)('a'))

    val registersTwo = registersOne.updated('c', 1)
    println("(Part 2) Register 'a': " + processInstructions(instructions, registersTwo)('a'))
  }

  def processInstructions(instructions: List[String], registers: Map[Char, Int], currentInstruction: Int = 0): Map[Char, Int] = {
    if (currentInstruction >= instructions.length) return registers

    val instruction = instructions(currentInstruction)
    val (instructionOffset, updatedRegisters) = processInstruction(instruction, registers)
    processInstructions(instructions, updatedRegisters, currentInstruction + instructionOffset)
  }

  private def processInstruction(instruction: String, registers: Map[Char, Int]): (Int, Map[Char, Int]) = instruction match {
    case copyValueCommandPattern(value, register) => (1, registers.updated(register(0), value.toInt))
    case copyRegisterCommandPattern(sourceRegister, destinationRegister) => (1, registers.updated(destinationRegister(0), registers(sourceRegister(0))))
    case incrementCommandPattern(register) => (1, registers.updated(register(0), registers(register(0)) + 1))
    case decrementCommandPattern(register) => (1, registers.updated(register(0), registers(register(0)) - 1))
    case jumpRegisterCommandPattern(register, offset) =>
      if (registers(register(0)) != 0) (offset.toInt, registers) else (1, registers)
    case jumpValueCommandPattern(value, offset) =>
      if (value.toInt != 0) (offset.toInt, registers) else (1, registers)
  }

}
