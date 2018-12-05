package com.github.dmitraver.adventofcode.y2018

import java.time.{LocalDate, LocalDateTime, LocalTime}

import com.github.dmitraver.adventofcode.utils.ResourceLoader
import com.github.dmitraver.adventofcode.utils.extensions._

object Day4 {

  private val BeginShiftEventRegexp = "\\[(\\d{4})\\-(\\d{2})\\-(\\d{2}) \\d{2}:(\\d{2})\\] Guard #(\\d+) begins shift".r
  private val FallsAsleepEventRegexp = "\\[(\\d{4})\\-(\\d{2})\\-(\\d{2}) 00:(\\d{2})\\] falls asleep".r
  private val WakeUpEventRegexp = "\\[(\\d{4})\\-(\\d{2})\\-(\\d{2}) 00:(\\d{2})\\] wakes up".r

  implicit val localDateOrdering: Ordering[LocalDateTime] = (x: LocalDateTime, y: LocalDateTime) => x compareTo y

  def main(args: Array[String]): Unit = {
    val input = ResourceLoader.fromResource("y2018/day4").getLines().toVector
    println("Part one " + partOne(input))
    println("Part two: " + partTwo(input))
  }

  private def parseInput(input: Vector[String]): Vector[Record] = {
    input.sorted.map {
      case BeginShiftEventRegexp(year, month, day, minute, guard) => Record(toDateTime(year, month, day, minute), BeginShiftEvent(guard.toInt))
      case FallsAsleepEventRegexp(year, month, day, minute) => Record(toDateTime(year, month, day, minute), FallAsleepEvent)
      case WakeUpEventRegexp(year, month, day, minute) => Record(toDateTime(year, month, day, minute), WakeUpEvent)
    }
  }

  private def toDateTime(year: String, month: String, day: String, minute: String): LocalDateTime = {
    LocalDateTime.of(LocalDate.of(year.toInt, month.toInt, day.toInt), LocalTime.of(0, minute.toInt))
  }

  private def groupShiftsByGuards(records: Vector[Record], map: Map[Int, Vector[Range]] = Map.empty, guardId: Int = 0): Map[Int, Vector[Range]] = records match {
    case Record(date, BeginShiftEvent(gid)) +: xs =>
      if (!map.contains(gid)) groupShiftsByGuards(xs, map + (gid -> Vector()), gid) else groupShiftsByGuards(xs, map, gid)
    case Record(startTime, FallAsleepEvent) +: Record(endTime, WakeUpEvent) +: xs => groupShiftsByGuards(xs, map.adjust(guardId)(ranges => ranges :+ Range(startTime.getMinute, endTime.getMinute)), guardId)
    case x +: xs => groupShiftsByGuards(xs, map, guardId) // we skip here
    case Vector() => map
  }

  def partOne(input: Vector[String]): Int = {
    val records = parseInput(input)
    val guardsToShifts = groupShiftsByGuards(records)
    val guardsToMinutesAsleep = guardsToShifts.map(value => value._1 -> value._2.foldLeft(0)((acc, range) => acc + range.end - range.start))
    val (guardId, asleepMins) = guardsToMinutesAsleep.maxBy(_._2)
    val ranges = guardsToShifts(guardId)

    val mask = Vector.fill(59)(0)
    val minute = ranges.foldLeft(mask)((acc, range) => range.foldLeft(acc)((a, v) => a.updated(v, a(v) + 1))).zipWithIndex.maxBy(_._1)._2
    guardId * minute
  }

  def partTwo(input: Vector[String]): Int = {
    val records = parseInput(input)
    val guardsToShifts = groupShiftsByGuards(records)
    val res = guardsToShifts.map { case (key, value) =>
      val mask = Vector.fill(59)(0)
      key -> value.foldLeft(mask)((acc, range) => range.foldLeft(acc)((a, v) => a.updated(v, a(v) + 1))).zipWithIndex.maxBy(_._1)
    }

    val (guardId, (value, minute)) = res.maxBy(_._2._1)
    guardId * minute
  }

}


sealed trait Event
case class BeginShiftEvent(guardId: Int) extends Event
case object FallAsleepEvent extends Event
case object WakeUpEvent extends Event

case class Record(date: LocalDateTime, event: Event)


