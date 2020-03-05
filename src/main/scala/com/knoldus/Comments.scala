package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Comments(jsonFile: JsonFile, jsonDataParser: JsonDataParser) {
  def getData(url: String): Future[List[Comment]] = {
    val commentsData: Future[String] = Future {
      jsonFile.getFeeds(url)
    }

    val futureListOfComments = commentsData map (x => jsonDataParser.parseComments(x))

    futureListOfComments
  }
}
