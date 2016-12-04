package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

import scala.io.Source

object DayThree {

  val pattern = "[ \\t]+([0-9]*)[ \\t]+([0-9]*)[ \\t]+([0-9]*)".r

  def main(args: Array[String]) {
    val sourceOne = ResourceLoader.fromResource("y2016/day3")
    println("Number of valid triangles:" + countValidTriangles(sourceOne))

    val sourceTwo = ResourceLoader.fromResource("y2016/day3")
    println("Number of valid vertical triangles:" + countValidTrianglesVertically(sourceTwo))
  }

  def countValidTriangles(source: Source) = {
    source.getLines().map { str =>
      val pattern(first, second, third) = str
      (first.toInt, second.toInt, third.toInt)
    }.count(isTriangle)
  }

  def countValidTrianglesVertically(source: Source) = {
    source.getLines().sliding(3,3).map { rows =>
      val pattern(a, b, c) = rows(0)
      val pattern(d, e, f) = rows(1)
      val pattern(g, h, i) = rows(2)
      List((a.toInt, d.toInt, g.toInt), (b.toInt, e.toInt, h.toInt), (c.toInt, f.toInt, i.toInt))
    }.toList.flatten.count(isTriangle)
  }

  def isTriangle(sides: Tuple3[Int, Int, Int]) = {
    val a = sides._1
    val b = sides._2
    val c = sides._3

    a + b > c && a + c > b && b + c > a
  }

}
