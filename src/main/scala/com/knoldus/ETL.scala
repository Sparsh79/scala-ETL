import java.io.File
import java.util.Scanner

import com.knoldus.{FileSource, ReadAndWrite}

trait ReadData {
  val resource: ReadAndWrite

  def reading(path: String): String = resource.read(path)

  def writing(destination: String, data: String): String = resource.write(destination, data)

  def getWord(data: String): Array[String] = data.toLowerCase.split("[^a-z0-9']+")

  def getTotalWords(data: String): Int = getWord(data).length

  def getWordMap(data: String): Map[String, List[String]] = getWord(data).toList.groupBy(x => x)

  def getUniqueWords(path: String): String = getWordMap(reading(path)).toList.map(x => x._1).mkString(" ")

  def getWordCount(path: String): String = {
    getWordMap(reading(path)).map {
      case (key, values) => key + "->" + values
    }
  } mkString "\n\n\n\n\n"

  def caps(destination: String, data: String): String = writing(destination, data.toUpperCase)
}

class ETL extends ReadData {
  override val resource: ReadAndWrite = FileSource

  def getList(directory: String): List[File] = {
    val dir = new File(directory)
    if (dir.exists && dir.isDirectory) {
      dir.listFiles.filter(_.isFile).toList
    }
    else {
      List[File]()
    }


  }
}

object fileHandlig extends ETL {
  def main(args: Array[String]): Unit = {

    val resource: ReadAndWrite = FileSource

    val input = new Scanner(System.in)
    //println("enter the path  ")
    //val dirPath = input.next()

    val sourcePath = input.next()

    val data = reading(sourcePath)
    val data_upper = data.toUpperCase
    print(data_upper)
    val destinationPath = caps("/home/sparsh/whatever2/file.txt", data_upper)
    println(10)
    val unique = getUniqueWords("/home/sparsh/whatever/first/file.txt")
    println(unique)
    val counter = getWordCount("/home/sparsh/whatever/first/file.txt")
    print(counter)
    val newPath = writing("/home/sparsh/whatever2/file.txt", counter)
    print(newPath)
  }
}



