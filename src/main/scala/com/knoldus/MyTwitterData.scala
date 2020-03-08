package com.knoldus


import twitter4j.{Query, Twitter}

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

case class TweetStatus(id: Long, favoriteCount: Int, reTweetCount: Int)

class MyTwitterData extends TwitterData(MyTwitterConfig) {
  val twitter: Twitter = MyTwitterConfig.twitter

  def getTweets(hashTag: String): Future[List[TweetStatus]] = Future {
    val hashTagQuery = new Query(hashTag)
    val result = twitter.search(hashTagQuery).getTweets.asScala.toList
    result.map(result => TweetStatus(result.getId, result.getFavoriteCount, result.getRetweetCount))
  }.fallbackTo {
    Future {
      List.empty[TweetStatus]
    }
  }
}