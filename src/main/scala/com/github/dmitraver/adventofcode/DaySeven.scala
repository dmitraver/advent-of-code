package com.github.dmitraver.adventofcode

sealed trait Command
object AND extends Command {
  val pattern = "([a-z0-9]+) AND ([a-z0-9]+) -> ([a-z]+)".r

  def unapply(s: String): Option[(String, String, String)] = s match {
    case pattern(left, right, output) => Some(left, right, output)
    case _ => None
  }
}

object OR extends Command {
  val pattern = "([a-z0-9]+) OR ([a-z0-9]+) -> ([a-z]+)".r

  def unapply(s: String): Option[(String, String, String)] = s match {
    case pattern(left, right, output) => Some(left, right, output)
    case _ => None
  }
}

object NOT extends Command {
  val pattern = "NOT ([a-z]+) -> ([a-z]+)".r

  def unapply(s: String): Option[(String, String)] = s match {
    case pattern(left, output) => Some (left, output)
    case _ => None
  }
}

object LSHIFT extends Command {
  val pattern = "([a-z]+) LSHIFT ([0-9]+) -> ([a-z]+)".r

  def unapply(s: String): Option[(String, Int, String)] = s match {
    case pattern(left, value, output) => Some(left, value.toInt, output)
    case _ => None
  }
}

object RSHIFT extends Command {
  val pattern = "([a-z]+) RSHIFT ([0-9]+) -> ([a-z]+)".r

  def unapply(s: String): Option[(String, Int, String)] = s match {
    case pattern(left, value, output) => Some(left, value.toInt, output)
    case _ => None
  }
}

object SIGNAL extends Command {
  val pattern = "([a-z0-9]+) -> ([a-z]+)".r

  def unapply(s: String): Option[(String, String)] = s match {
    case pattern(value, output) => Some(value, output)
    case _ => None
  }
}

object DaySeven {

  def main(args: Array[String]) {
  }
}