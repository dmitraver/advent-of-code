package com.github.dmitraver.adventofcode.y2019

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object Day1 {
  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2019/day1_1").getLines().toVector
    println("Total fuel required: " + countTotalFuel(input, countRequiredFuel))
    println("Total fuel required (recursive): " + countTotalFuel(input, countRequiredFuelRecursive))
  }

  def countTotalFuel(masses: Vector[String], fuelCalc: Int => Int): Int = {
    masses.map(_.toInt).map(fuelCalc).sum
  }

  def countRequiredFuelRecursive(mass: Int): Int = {
    val fuel = countRequiredFuel(mass)
    if (fuel <= 0) 0
    else fuel + countRequiredFuelRecursive(fuel)
  }

  def countRequiredFuel(mass: Int): Int = mass / 3 - 2
}
