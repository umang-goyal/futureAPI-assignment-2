package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Users {

  def getData(url: String): Future[List[User]] = {
    val userData: Future[String] = Future {
      JsonFile.getFeeds(url)
    }


    val futureListOfUser = userData map (x => JsonDataParser.parseUser(x))

    futureListOfUser
  }
}
