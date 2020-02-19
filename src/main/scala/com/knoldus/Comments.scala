package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Comments {
  def getData: Future[List[Comment]] = {
    val commentsData: Future[String] = Future {
      JsonFile.getFeeds("https://jsonplaceholder.typicode.com/comments")
    }

    val futureListOfComments = commentsData map (x => JsonDataParser.parseComments(x))

    futureListOfComments
  }
}
