package com.github.dmitraver.adventofcode.y2018

import com.github.dmitraver.adventofcode.utils.extensions._

import scala.annotation.tailrec

object Day9 {

  def main(args: Array[String]): Unit = {
    println("Day one: " + playMarbleGame(Vector(0), Map.empty, 0, 1, 411, 0, 7117).maxBy(_._2))\
  }

  @tailrec
  private def playMarbleGame(chain: Vector[Int], playersWithScores: Map[Int, Int], currentIndex: Int, currentPlayer: Int, maxPlayers: Int, currentMarble: Int, maxMarble: Int): Map[Int, Int] = {
    if (currentMarble == maxMarble) playersWithScores
    else {
      val nextMarble = currentMarble + 1
      val nextPlayer = if (currentPlayer >= maxPlayers) 1 else currentPlayer + 1
      if (nextMarble % 23 == 0) {
        val probablyNext = currentIndex - 7
        val marbleToRemoveIndex = if (probablyNext < 0) chain.size + probablyNext else probablyNext
        val adjusted = playersWithScores.adjust(currentPlayer, Some(nextMarble + chain(marbleToRemoveIndex)))(score => score + nextMarble + chain(marbleToRemoveIndex))
        val nextIndex = marbleToRemoveIndex
        val left = chain.take(marbleToRemoveIndex)
        val right = chain.drop(marbleToRemoveIndex + 1)
        playMarbleGame(left ++ right, adjusted, nextIndex, nextPlayer, maxPlayers, nextMarble, maxMarble)
      } else {
        val nextIndex = if (currentIndex == chain.size - 1) 1 else currentIndex + 2
        val (left, right) = chain.splitAt(nextIndex)
        playMarbleGame(left ++ Vector(nextMarble) ++ right, playersWithScores, nextIndex, nextPlayer, maxPlayers, nextMarble, maxMarble)
      }
    }
  }
}
