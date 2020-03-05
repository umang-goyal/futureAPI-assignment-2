package com.knoldus


import twitter4j.{Query, Status, Twitter}

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions



trait TwitterExtractor {

  val twitter: Twitter

  def getTweets(hashTag: String): Future[List[Status]] = Future {
    val hashTagQuery = new Query(hashTag)
    val result = twitter.search(hashTagQuery).getTweets.asScala.toList
    result
  }.fallbackTo {
    Future {
      List.empty[Status]
    }
  }
}