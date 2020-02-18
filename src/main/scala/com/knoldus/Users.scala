package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object Users {
  def getData: List[User] ={
    val userData: Future[String] = Future {
      JsonFile.getFeeds("https://jsonplaceholder.typicode.com/users")
    }
    val futureListOfUsers: Future[List[User]] = for {
      jsonData <- userData
      listOfUsers <- Future(JsonDataParser.parseUser(jsonData))
    } yield listOfUsers

    futureListOfUsers.value.get.get
    //Await.result(futureListOfUsers, 7.seconds)
    //userList
  }
}

