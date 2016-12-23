package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayTwentyThree {

  val copyValueCommandPattern = "cpy (-?\\d*) (\\w)".r
  val copyRegisterCommandPattern = "cpy (\\w) (\\w)".r
  val incrementCommandPattern = "inc (\\w)".r
  val decrementCommandPattern = "dec (\\w)".r
  val jumpRegisterValueCommandPattern = "jnz ([a-z]) (-?\\d*)".r
  val jumpValueValueCommandPattern = "jnz (\\d*) (-?\\d*)".r
  val jumpValueRegisterCommandPattern = "jnz (\\d*) (\\w)".r
  val toggleValueCommandPattern = "tgl (-?\\d*)".r
  val toggleCommandPattern = "tgl (\\w)".r

  val oneArgumentCommand = "(\\w*) (\\w*)".r
  val twoArgumentsCommand = "(\\w*) (\\w*) (\\w*)".r

  def main(args: Array[String]): Unit = {
    val instructions = ResourceLoader.fromResource("y2016/day23").getLines().toList
    val registersOne = Map('a' -> 7, 'b' -> 0, 'c' -> 0, 'd' -> 0)
    println("(Part 1) Register 'a': " + processInstructions(instructions, registersOne)('a'))
  }

  def processInstructions(instructions: List[String], registers: Map[Char, Int], currentInstruction: Int = 0): Map[Char, Int] = {
    if (currentInstruction >= instructions.length) return registers

    val instruction = instructions(currentInstruction)
    instruction match {
      case copyValueCommandPattern(value, register) => processInstructions(instructions, registers.updated(register(0), value.toInt), currentInstruction + 1)
      case copyRegisterCommandPattern(sourceRegister, destinationRegister) => processInstructions(instructions, registers.updated(destinationRegister(0), registers(sourceRegister(0))), currentInstruction + 1)
      case incrementCommandPattern(register) => processInstructions(instructions, registers.updated(register(0), registers(register(0)) + 1), currentInstruction + 1)
      case decrementCommandPattern(register) => processInstructions(instructions, registers.updated(register(0), registers(register(0)) - 1), currentInstruction + 1)
      case jumpRegisterValueCommandPattern(register, offset) =>
        var nextInstrOffset = 1
        if (registers(register(0)) != 0) nextInstrOffset = offset.toInt
        processInstructions(instructions, registers, currentInstruction + nextInstrOffset)
      case jumpValueValueCommandPattern(value, offset) =>
        var nextInstrOffset = 1
        if (value.toInt != 0) nextInstrOffset = offset.toInt
        processInstructions(instructions, registers, currentInstruction + nextInstrOffset)
      case jumpValueRegisterCommandPattern(value, register) =>
        var nextInstrOffset = 1
        if (value.toInt != 0) nextInstrOffset = registers(register(0))
        processInstructions(instructions, registers, currentInstruction + nextInstrOffset)
      case toggleCommandPattern(register) =>
        val offset = registers(register(0))
        if (offset == 0) processInstructions(instructions.updated(currentInstruction, s"inc $register"), registers, currentInstruction + 1)
        else if (currentInstruction + offset < 0 || currentInstruction + offset >= instructions.length) processInstructions(instructions, registers, currentInstruction + 1)
        else {
          val instruction = instructions(currentInstruction + offset)
          val toggledInstruction = toggleInstruction(instruction)
          processInstructions(instructions.updated(currentInstruction + offset, toggledInstruction), registers, currentInstruction + 1)
        }

      case toggleValueCommandPattern(value) =>
        val offset = value.toInt
        if (offset == 0) processInstructions(instructions, registers, currentInstruction + 1)
        else if (currentInstruction + offset < 0 || currentInstruction + offset >= instructions.length) processInstructions(instructions, registers, currentInstruction + 1)
        else {
          val instruction = instructions(currentInstruction + offset)
          val toggledInstruction = toggleInstruction(instruction)
          processInstructions(instructions.updated(currentInstruction + offset, toggledInstruction), registers, currentInstruction + 1)
        }
    }
  }

  private def toggleInstruction(instruction: String) = instruction match {
    case oneArgumentCommand(cmd, arg) if cmd == "inc" => s"dec $arg"
    case oneArgumentCommand(cmd, arg) => s"inc $arg"
    case twoArgumentsCommand(cmd, argOne, argTwo) if cmd == "jnz" => s"cpy $argOne $argTwo"
    case twoArgumentsCommand(cmd, argOne, argTwo) => s"jnz $argOne $argTwo"
    case _ => instruction
  }

  private def processInstruction(instruction: String, registers: Map[Char, Int]): (Int, Map[Char, Int]) = instruction match {
    case copyValueCommandPattern(value, register) => (1, registers.updated(register(0), value.toInt))
    case copyRegisterCommandPattern(sourceRegister, destinationRegister) => (1, registers.updated(destinationRegister(0), registers(sourceRegister(0))))
    case incrementCommandPattern(register) => (1, registers.updated(register(0), registers(register(0)) + 1))
    case decrementCommandPattern(register) => (1, registers.updated(register(0), registers(register(0)) - 1))
    case jumpRegisterValueCommandPattern(register, offset) =>
      if (registers(register(0)) != 0) (offset.toInt, registers) else (1, registers)
    case jumpValueValueCommandPattern(value, offset) =>
      if (value.toInt != 0) (offset.toInt, registers) else (1, registers)
  }
}
