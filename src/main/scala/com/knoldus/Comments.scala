package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object Comments {
  def getData: List[Comment] = {
    val commentsData: Future[String] = Future {
      JsonFile.getFeeds("https://jsonplaceholder.typicode.com/comments")
    }


    val futureListOfComments: Future[List[Comment]] = for {
      jsonData <- commentsData
      listOfComments <- Future(JsonDataParser.parseComments(jsonData))
    } yield listOfComments

    Await.result(futureListOfComments, 7.seconds)
  }
}
