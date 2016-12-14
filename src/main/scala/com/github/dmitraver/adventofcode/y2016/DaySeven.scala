package com.github.dmitraver.adventofcode.y2016

import com.github.dmitraver.adventofcode.utils.ResourceLoader

object DaySeven {

  val hypernetSeqPattern = "\\[(\\w*)\\]".r

  def main(args: Array[String]): Unit = {
    val addresses = ResourceLoader.fromResource("y2016/day7").getLines().toList
    println("Number of IPs that support TLS: " + countIPSSupportingTLS(addresses))
    println("Number of IPs that support SSL: " + countIPSSupportingSSL(addresses))
  }

  def countIPSSupportingTLS(input: List[String]) = {
    input.map(supportsTLS).count(_ == true)
  }

  def countIPSSupportingSSL(input: List[String]) = {
    input.map(supportsSSL).count(_ == true)
  }

  def supportsTLS(ip: String) = {
    val hasABBAinHypernet = hypernetSeqPattern.findAllMatchIn(ip).map(_.group(1)).exists(containsABBA)
    val hasABBAinIP = ip.split(hypernetSeqPattern.regex).exists(containsABBA)
    !hasABBAinHypernet && hasABBAinIP
  }

  def supportsSSL(ip: String) = {
    val hypernetSequences = hypernetSeqPattern.findAllMatchIn(ip).map(_.group(1)).toList
    val supernetSequences = ip.split(hypernetSeqPattern.regex)
    supernetSequences.flatMap(findABA).exists { aba: String =>
      val bab = abaToBab(aba)
      hypernetSequences.exists(_.contains(bab))
    }
  }

  def abaToBab(aba: String) = Array(aba(1), aba(0), aba(1)).mkString

  def containsABBA(input: String) = {
    input.sliding(4,1).exists(hasABBA)
  }

  def findABA(input: String) = {
    input.sliding(3,1).filter(hasABA)
  }

  def hasABBA(input: String) = {
    if (input.length != 4) false
    else input(0) != input(1) && input(0) == input(3) && input(1) == input(2)
  }

  def hasABA(input: String) = {
    if (input.length != 3) false
    else input(0) != input(1) && input(0) == input(2)
  }
}
