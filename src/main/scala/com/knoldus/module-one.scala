package com.knoldus

import java.io._
import java.nio.file.{Files, Paths}

import scala.io.Source

trait ReadAndWrite {
  def ifExists(string: String): Boolean

  def read(path: String): String

  def write(destination: String, data: String): String
}

object FileSource extends ReadAndWrite {

  override def ifExists(path: String): Boolean = Files.exists(Paths.get(path))

  override def read(path: String): String = {
    Source.fromFile(path).mkString
  }

  override def write(destination: String, data: String): String = {
    val writer = new PrintWriter(new File(destination))
    writer.write(data)
    writer.close()
    "\ndata written to the file"
  }

}
