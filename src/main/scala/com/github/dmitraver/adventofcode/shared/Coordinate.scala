package com.github.dmitraver.adventofcode.shared

case class Coordinate(x: Int, y: Int) {

  def +(another: Coordinate) = {
    copy(x = x + another.x, y = y + another.y)
  }
}
