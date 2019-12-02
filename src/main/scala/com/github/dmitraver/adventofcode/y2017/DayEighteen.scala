package com.github.dmitraver.adventofcode.y2017

//sealed trait Command
//class SndCommand(frequency) extends Command
//
//sealed trait Command
//class SndCommand extends Command {
//  private val pattern = "snd ([\\w|\\d+]+)".r
//
//  def unapply(s: String): Option[(String, String, String)] = s match {
//    case pattern(left, right, output) => Some(left, right, output)
//    case _ => None
//  }
//}

object DayEighteen {

  type Register = Char

  val sndCommandPattern = "snd ([\\w|\\d+]+)".r
  val setCommandPattern = "set (\\w) ([\\w|\\d+]+)".r
  val addCommandPattern = "add (\\w) ([\\w|\\d+]+)".r
  val modCommandPattern = "mod (\\w) ([\\w|\\d+]+)".r
  val rcvCommandPattern = "rcv ([\\w|\\d+]+)".r
  val jgzCommandPattern = "jgz ([\\w|\\d+]+) ([\\w|\\d+]+)".r

  def processCommands(commands: Vector[String]): Int = {
    ???
  }

  def main(args: Array[String]): Unit = {

  }
}
