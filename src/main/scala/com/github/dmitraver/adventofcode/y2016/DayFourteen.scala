package com.github.dmitraver.adventofcode.y2016

import org.apache.commons.codec.digest.DigestUtils

import scala.collection.mutable

object DayFourteen {

  val threeLettersPattern = "(\\w)\\1\\1".r

  def main(args: Array[String]): Unit = {
    println("Last index of 64th OTP key: " + findIndexOfTheLastKey("jlmsuwbz"))
    println("Last index of 64th OTP key extended: " + findIndexOfTheLastKeyExtended("jlmsuwbz"))
  }

  def sum: Int => Int = {
    x: Int => x + 1
  }

  def findIndexOfTheLastKey(salt: String) = {
    Stream.from(0).filter(i => hasKey(i, salt, computeDigest)).take(64).last
  }

  def findIndexOfTheLastKeyExtended(salt: String) = {
    Stream.from(0).filter(i => hasKey(i, salt, computeDigestWithMemo)).take(64).last
  }

  def computeDigest: String => String = {
    input: String => DigestUtils.md5Hex(input)
  }

  val computeDigestWithMemo = Memo.withHashMap {
    input: String => Function.chain(List.fill(2017)(computeDigest)) (input)
  }

  def hasKey(index: Int, salt: String, digest: String => String) = {
    threeLettersPattern.findFirstIn(digest(salt + index)) match {
      case Some(triplet) => (index + 1 to index + 1000).exists(i => digest(salt + i) contains triplet.substring(0, 1) * 5)
      case None => false
    }
  }

}

object Memo {

  def withHashMap[I, O](f: I => O) = new mutable.HashMap[I, O] {
    override def apply(key: I) = getOrElseUpdate(key, f(key))
  }
}
