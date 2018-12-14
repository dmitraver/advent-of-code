package com.github.dmitraver.adventofcode.y2018

import scala.annotation.tailrec

object Day14 {

  def main(args: Array[String]): Unit = {
    println("Day one: " + recipesNumbrt(Vector(3, 7), 0, 1, 260321).mkString)
  }

  @tailrec
  private def recipesNumbrt(recipes: Vector[Int], firstElfIndex: Int, secondElfIndex: Int, maxRecipes: Int): Vector[Int] = {
    if (recipes.length == maxRecipes + 10) {
      recipes.slice(maxRecipes,  maxRecipes + 10)
    } else {
      val combined = (recipes(firstElfIndex) + recipes(secondElfIndex)).toString
      val firstDigit = if (combined.length < 2) 0 else combined(0).asDigit
      val secondDigit = if (combined.length < 2) combined(0).asDigit else combined(1).asDigit
      val newRecipes = if (firstDigit == 0) Vector(secondDigit) else Vector(firstDigit, secondDigit)

      val updatedRecipes = recipes ++  newRecipes
      val a = (1 + updatedRecipes(firstElfIndex)) % updatedRecipes.size
      val nextFirstElfIndex = (firstElfIndex + a) % updatedRecipes.size

      val b = (1 + updatedRecipes(secondElfIndex)) % updatedRecipes.size
      val nextSecondElfIndex = (secondElfIndex + b) % updatedRecipes.size

      recipesNumbrt(updatedRecipes, nextFirstElfIndex, nextSecondElfIndex, maxRecipes)
    }
  }
}