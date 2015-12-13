package com.github.dmitraver.adventofcode

case class Coordinate(x: Int, y: Int) {

  def +(another: Coordinate) = {
    copy(x = x + another.x, y = y + another.y)
  }
}

object DayThree {

  val toCoordinate = (direction: Char) => direction match {
    case '^' => Coordinate(0, 1)
    case 'v' => Coordinate(0, -1)
    case '>' => Coordinate(1, 0)
    case '<' => Coordinate(-1, 0)
  }

  def main(args: Array[String]) {
    val stream = ResourceLoader.fromResource("day3").toStream
    println("Houses: " + countHouses(stream))
  }

  def countHouses(stream: Stream[Char]) = {
    stream.map(toCoordinate).scan(Coordinate(0, 0))(_ + _).toSet.size
  }
}
