package modifier

import java.io.File
import java.nio.file.{Files, Path}
import scala.annotation.tailrec

object FileModifier {
  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      println("usage: java -jar FileModifier.jar <filename> key1=value1 key2=value2")
    } else {
      val fileName = args.head
      val pairs = args.tail.toList.map { s =>
        val index = s.indexOf("=")
        if (index == -1) {
          println(s"Invalid key/value pair: $s (Expected key=value)")
          sys.exit(1)
        }
        val key = s.substring(0, index)
        val value = s.substring(index + 1)
        key -> value
      }
      modify(
        new File(fileName).toPath,
        pairs: _*
      )
    }
  }

  def modify(file: Path, pairs: (String, String)*): Unit = {
    var s = new String(Files.readAllBytes(file), "UTF-8").trim

    @tailrec
    def recurse(list: List[(String, String)]): Unit = list match {
      case Nil => // Finished
      case (key, value) :: tail =>
        val index = s.indexOf(key)
        if (index == -1) {
          s = s"$s\n$key=$value"
        } else {
          val endOfLine = s.indexOf("\n", index)
          val pre = s.substring(0, index)
          val post = if (endOfLine == -1) {
            ""
          } else {
            s.substring(endOfLine)
          }
          s = s"$pre$key=$value$post"
        }
        recurse(list.tail)
    }
    recurse(pairs.toList)

    println(s)
  }
}