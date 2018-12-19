package com.github.dmitraver.adventofcode.y2018

import atto.Atto._
import atto._
import com.github.dmitraver.adventofcode.utils.ResourceLoader
import com.github.dmitraver.adventofcode.y2018.Day16.Registers

object Day16 {

  type Registers = Vector[Int]

  private val opcodes = Set(Adds, Addi, Mulr, Muli, Banr, Bani, Borr, Bori, Setr, Seti, Gtir, Gtri, Gtrr, Eqir, Eqri, Eqrr)

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day16").getLines().mkString("\n")
    val parsed = parseInput(input)
    println("Part one: " + partOne(parsed))
  }

  private def parseInput(input: String): Vector[Sample] = {
    val parseRegisters = squareBrackets(sepBy(int, string(", ")))
    val beforeParser: Parser[Registers] = string("Before:") ~> many(spaceChar) ~> parseRegisters.map(ints => ints.toVector)
    val instrParser: Parser[InstructionRaw] = sepBy(int, spaceChar).map(ints => InstructionRaw(ints(0), ints(1), ints(2), ints(3)))
    val afterParser: Parser[Registers] = string("After:") ~> many(spaceChar) ~> parseRegisters.map(ints => ints.toVector)

    val sampleParser: Parser[Sample] = for {
      input  <- beforeParser
      _      <- string("\n")
      instr  <- instrParser
      _      <- string("\n")
      output <- afterParser
    } yield Sample(input, instr, output)

    sepBy(sampleParser, string("\n\n")).map(_.toVector).parse(input).done.option.get
  }

  def runInstruction(registers: Registers, instruction: Instruction): Registers = instruction.opcode match {
    case Adds => registers.updated(instruction.output, registers(instruction.inputOne) + registers(instruction.inputTwo))
    case Addi => registers.updated(instruction.output, registers(instruction.inputOne) + instruction.inputTwo)
    case Mulr => registers.updated(instruction.output, registers(instruction.inputOne) * registers(instruction.inputTwo))
    case Muli => registers.updated(instruction.output, registers(instruction.inputOne) * instruction.inputTwo)
    case Banr => registers.updated(instruction.output, registers(instruction.inputOne) & registers(instruction.inputTwo))
    case Bani => registers.updated(instruction.output, registers(instruction.inputOne) & instruction.inputTwo)
    case Borr => registers.updated(instruction.output, registers(instruction.inputOne) | registers(instruction.inputTwo))
    case Bori => registers.updated(instruction.output, registers(instruction.inputOne) | instruction.inputTwo)
    case Setr => registers.updated(instruction.output, registers(instruction.inputOne))
    case Seti => registers.updated(instruction.output, instruction.inputOne)
    case Gtir => registers.updated(instruction.output, if (instruction.inputOne > registers(instruction.inputTwo)) 1 else 0)
    case Gtri => registers.updated(instruction.output, if (registers(instruction.inputOne) > instruction.inputTwo) 1 else 0)
    case Gtrr => registers.updated(instruction.output, if (registers(instruction.inputOne) > registers(instruction.inputTwo)) 1 else 0)
    case Eqir => registers.updated(instruction.output, if (instruction.inputOne == registers(instruction.inputTwo)) 1 else 0)
    case Eqri => registers.updated(instruction.output, if (registers(instruction.inputOne) == instruction.inputTwo) 1 else 0)
    case Eqrr => registers.updated(instruction.output, if (registers(instruction.inputOne) == registers(instruction.inputTwo)) 1 else 0)
  }

  def tryInstruction(input: Registers, instruction: Instruction, output: Registers): Boolean = {
    runInstruction(input, instruction) == output
  }

  def countPossibleOpcodes(s: Sample): Int = {
    opcodes.count(opcode => tryInstruction(s.input,
      Instruction(opcode, s.instr.inputOne, s.instr.inputTwo, s.instr.output),
      s.output))
  }

  def partOne(input: Vector[Sample]): Int = {
    input.foldLeft(0)((acc, s) => acc + (if (countPossibleOpcodes(s) >= 3) 1 else 0))
  }
}

sealed trait Opcode
case object Adds extends Opcode
case object Addi extends Opcode
case object Mulr extends Opcode
case object Muli extends Opcode
case object Banr extends Opcode
case object Bani extends Opcode
case object Borr extends Opcode
case object Bori extends Opcode
case object Setr extends Opcode
case object Seti extends Opcode
case object Gtir extends Opcode
case object Gtri extends Opcode
case object Gtrr extends Opcode
case object Eqir extends Opcode
case object Eqri extends Opcode
case object Eqrr extends Opcode

case class Instruction(opcode: Opcode, inputOne: Int, inputTwo: Int, output: Int)
case class InstructionRaw(opcode: Int, inputOne: Int, inputTwo: Int, output: Int)

case class Sample(input: Registers, instr: InstructionRaw, output: Registers)



