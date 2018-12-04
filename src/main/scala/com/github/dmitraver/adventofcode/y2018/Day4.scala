package com.github.dmitraver.adventofcode.y2018

import java.time.{LocalDate, LocalDateTime, LocalTime}

import com.github.dmitraver.adventofcode.utils.ResourceLoader
import com.github.dmitraver.adventofcode.utils.extensions._

object Day4 {

  private val BeginShiftEventRegexp = "\\[(\\d{4})\\-(\\d{2})\\-(\\d{2}) 00:(\\d{2})\\] Guard #(\\d+) begins shift".r
  private val FallsAsleepEventRegexp = "\\[(\\d{4})\\-(\\d{2})\\-(\\d{2}) 00:(\\d{2})\\] falls asleep".r
  private val WakeUpEventRegexp = "\\[(\\d{4})\\-(\\d{2})\\-(\\d{2}) 00:(\\d{2})\\] wakes up".r

  implicit val localDateOrdering: Ordering[LocalDateTime] = (x: LocalDateTime, y: LocalDateTime) => x compareTo y

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day4_1").getLines().toVector
    val records = parseInput(input)

    val map = foo(records)

    val result = map.map(value => value._1 -> value._2.foldLeft(0)((acc, range) => acc + range.end - range.start))
    println(result)

    val (guardId, mostAsleep) = result.maxBy(_._2)


    val ranges = map(guardId)
    val mask = Vector.fill(50)(0)
    val r = ranges.foldLeft(mask)((acc, range) => range.foldLeft(acc)((a, v) => a.updated(v - 1, a(v - 1) + 1)))
    println(r)
  }

  private def parseInput(input: Vector[String]): Vector[Record] = {
    input.map {
      case BeginShiftEventRegexp(year, month, day, minute, guard) => Record(toDateTime(year, month, day, minute), BeginShiftEvent(guard.toInt))
      case FallsAsleepEventRegexp(year, month, day, minute) => Record(toDateTime(year, month, day, minute), FallAsleepEvent)
      case WakeUpEventRegexp(year, month, day, minute) => Record(toDateTime(year, month, day, minute), WakeUpEvent)
    } sortBy(_.date)
  }

  private def toDateTime(year: String, month: String, day: String, minute: String): LocalDateTime = {
    LocalDateTime.of(LocalDate.of(year.toInt, month.toInt, day.toInt), LocalTime.of(0, minute.toInt))
  }

  private def foo(records: Vector[Record], map: Map[Int, Vector[Range]] = Map.empty, guardId: Int = 0): Map[Int, Vector[Range]] = records match {
    case Record(date, BeginShiftEvent(gid)) +: xs => if (!map.contains(gid)) foo(xs, map + (gid -> Vector()), gid) else foo(xs, map, gid)
    case Record(startTime, FallAsleepEvent) +: Record(endTime, WakeUpEvent) +: xs => foo(xs, map.adjust(guardId)(ranges => ranges :+ Range(startTime.getMinute, endTime.getMinute)), guardId)
    case _ => map
  }

  //private def find
}



sealed trait Event
case class BeginShiftEvent(guardId: Int) extends Event
case object FallAsleepEvent extends Event
case object WakeUpEvent extends Event

case class Record(date: LocalDateTime, event: Event)


