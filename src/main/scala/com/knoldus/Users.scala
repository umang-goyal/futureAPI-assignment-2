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

object Driver extends App{
  val jsonFile = new JsonFile
  val jsonDataParser = new JsonDataParser
  val users = new Users(jsonFile, jsonDataParser)
  val posts = new Posts(jsonFile, jsonDataParser)
  val comments = new Comments(jsonFile, jsonDataParser)
  val utilities = new Utilities(users, posts, comments)

  val x = jsonFile.getFeeds("https://jsonplaceholder.typicode.com/users")
    //users.getData("https://jsonplaceholder.typicode.com/users")
  Thread.sleep(5000)
  println(x)
}
