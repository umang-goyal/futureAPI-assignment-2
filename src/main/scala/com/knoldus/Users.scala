package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Users {
  def getData: Future[List[User]] = {
    val userData: Future[String] = Future {
      JsonFile.getFeeds("https://jsonplaceholder.typicode.com/users")
    }


    val futureListOfUser = userData map (x => JsonDataParser.parseUser(x))

    futureListOfUser
  }
}
