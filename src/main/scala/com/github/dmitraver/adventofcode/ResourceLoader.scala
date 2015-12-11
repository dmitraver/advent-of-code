package com.github.dmitraver.adventofcode

import scala.io.Source

object ResourceLoader {

  /**
   * Loads file from resources folder by name.
   * @param name name of the file to load
   */
  def fromResource(name: String) = {
    Source.fromInputStream(ResourceLoader.getClass.getClassLoader.getResourceAsStream(name))
  }
}
