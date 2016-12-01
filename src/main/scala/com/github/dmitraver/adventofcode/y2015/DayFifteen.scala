package com.github.dmitraver.adventofcode.y2015

import com.github.dmitraver.adventofcode.utils.ResourceLoader

case class Ingredient(name: String, capacity: Int, durability: Int, flavor: Int, texture: Int, calories: Int)

object DayFifteen {

  val pattern = "([a-zA-Z]+): capacity (-?[0-9]+), durability (-?[0-9]+), flavor (-?[0-9]+), texture (-?[0-9]+), calories (-?[0-9]+)".r

  def main(args: Array[String]) {
    val ingredients = ResourceLoader.fromResource("y2015/day15").getLines().map{
      case pattern(name, capacity, durability, flavor, texture, calories) =>
        Ingredient(name, capacity.toInt, durability.toInt, flavor.toInt, texture.toInt, calories.toInt)
    }
  }


}
