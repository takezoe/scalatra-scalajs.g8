package client

import scala.scalajs.js.JSApp
import org.scalajs.dom
import org.scalajs.jquery.jQuery
import dom.ext.Ajax
import scala.concurrent.ExecutionContext.Implicits.global
import model.Book
import upickle.default._

object SampleApp extends JSApp {
  def main(): Unit = {
    Ajax.post("/books").foreach{ xhr =>
      val books = read[Seq[Book]](xhr.responseText)
      val ul = jQuery("ul#books")
      books.foreach { book =>
        ul.append(s"<li>${book.title} - ${book.author.mkString(", ")}</li>")
      }
    }
  }
}
