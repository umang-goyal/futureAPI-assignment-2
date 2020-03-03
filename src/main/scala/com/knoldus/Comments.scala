package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Comments {
  def getData(url: String): Future[List[Comment]] = {
    val commentsData: Future[String] = Future {
      JsonFile.getFeeds(url)
    }

    val futureListOfComments = commentsData map (x => JsonDataParser.parseComments(x))

    futureListOfComments
  }
}
