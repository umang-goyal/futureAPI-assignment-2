package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

//when name was Posts it was giving error of type mismatch
class Posts(jsonFile: JsonFile, jsonDataParser: JsonDataParser) {
  def getData(url: String): Future[List[Post]] = {
    val postsData: Future[String] = Future {
      jsonFile.getFeeds(url)
    }

    val futureListOfPosts = postsData map (x => jsonDataParser.parsePosts(x))


    futureListOfPosts
  }
}

