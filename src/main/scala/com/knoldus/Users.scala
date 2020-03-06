package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Users(jsonFile: JsonFile, jsonDataParser: JsonDataParser) {

  def getData(url: String): Future[List[User]] = {
    val userData: Future[String] = Future {
      jsonFile.getFeeds(url)
    }


    val futureListOfUser = userData map (x => jsonDataParser.parseUser(x))

    futureListOfUser
  }
}

