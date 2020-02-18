package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

//when name was Posts it was giving error of type mismatch
object Posts {
def getData: List[Post]={
  val postsData: Future[String]= Future{
    JsonFile.getFeeds("https://jsonplaceholder.typicode.com/posts")
  }

  val futureListOfPosts = for{
    jsonData <- postsData
    listOfPosts <- Future(JsonDataParser.parsePosts(jsonData))
  } yield listOfPosts

  Await.result(futureListOfPosts,7.seconds)
}
}
