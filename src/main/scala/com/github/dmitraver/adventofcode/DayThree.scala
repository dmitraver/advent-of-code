package com.github.dmitraver.adventofcode

import com.github.dmitraver.adventofcode.shared.Coordinate
import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DayThree {

  val toCoordinate = (direction: Char) => direction match {
    case '^' => Coordinate(0, 1)
    case 'v' => Coordinate(0, -1)
    case '>' => Coordinate(1, 0)
    case '<' => Coordinate(-1, 0)
  }

  def main(args: Array[String]) {
    val streamOne = ResourceLoader.fromResource("day3").toStream
    println("Houses: " + countHouses(streamOne))

    val streamTwo = ResourceLoader.fromResource("day3").toStream
    println("Houses with robo:" + countHousesWithRobo(streamTwo))
  }

  def countHouses(stream: Stream[Char]) = {
    stream.map(toCoordinate).scan(Coordinate(0, 0))(_ + _).toSet.size
  }

  def countHousesWithRobo(stream: Stream[Char]) = {
    val (santa, roboSanta) = stream.zipWithIndex.partition(_._2 % 2 == 0)
    val santaSet = santa.map(indexedCoordinate => toCoordinate(indexedCoordinate._1)).scan(Coordinate(0, 0))(_ + _).toSet
    val roboSantaSet = roboSanta.map(indexedCoordinate => toCoordinate(indexedCoordinate._1)).scan(Coordinate(0, 0))(_ + _).toSet
    santaSet.union(roboSantaSet).size
  }
}
