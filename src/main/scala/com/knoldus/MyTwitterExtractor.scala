package com.knoldus

import twitter4j.Twitter

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

object MyTwitterExtractor extends TwitterExtractor {

  val twitter: Twitter = MyTwitterConfig.twitter

  def tweetsCount(hashTag: String): Future[Int] = {

    val countOfTweets = getTweets(hashTag).map(tweets => tweets.length)
    countOfTweets

    }.fallbackTo(Future {
    -1
  })


  def getAvgLikesPerTweet(hashTag: String): Future[Int] = {

    val tweets = getTweets(hashTag)
    val likesCount = tweets.map(tweets => tweets.map(tweet => tweet.getFavoriteCount))
    val totalLikes = likesCount.map(likesCount => likesCount.sum)
    totalLikes.flatMap(totalLikes => tweets.map(tweets => totalLikes / tweets.size))
    }
    .fallbackTo(Future {
      -1
    })


  def getAvgReTweetsPerTweet(hashTag: String): Future[Int] = {

    val tweets = getTweets(hashTag)
    val reTweetCount = tweets.map(tweets => tweets.map(tweet => tweet.getRetweetCount))
    val totalReTweetCount = reTweetCount.map(reTweetCount => reTweetCount.sum)
    totalReTweetCount.flatMap(Count => tweets.map(tweets => Count / tweets.size))
    }.fallbackTo(Future {
    -1
  })

}
