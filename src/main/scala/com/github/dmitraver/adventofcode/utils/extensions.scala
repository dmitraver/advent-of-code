package com.github.dmitraver.adventofcode.utils

object extensions {
  implicit class MapImprovements[A, B](map: Map[A, B]) {
    def adjust(key: A, default: Option[B] = None)(f: B => B): Map[A, B] = {
      map.get(key).fold(default.fold(map)(defaultValue => map.updated(key, defaultValue))) (value => map.updated(key, f(value)))
    }
  }

}
