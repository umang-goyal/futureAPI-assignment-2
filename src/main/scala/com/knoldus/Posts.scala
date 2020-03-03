package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

//when name was Posts it was giving error of type mismatch
object Posts {
  def getData(url: String): Future[List[Post]] = {
    val postsData: Future[String] = Future {
      JsonFile.getFeeds(url)
    }

    val futureListOfPosts = postsData map (x => JsonDataParser.parsePosts(x))


    futureListOfPosts
  }
}

