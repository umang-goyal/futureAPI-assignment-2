package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

class TwitterOperations(twitterData: MyTwitterData) {

  def tweetsCount(hashTag: String): Future[Int] = {
    val countOfTweets = twitterData.getTweets(hashTag).map(tweets => tweets.length)
    countOfTweets
    }.fallbackTo(Future {
    -1
  })


  def getAvgLikesPerTweet(hashTag: String): Future[Int] = {
    val tweets = twitterData.getTweets(hashTag)
    val likesCount = tweets.map(tweets => tweets.map(tweet => tweet.favoriteCount))
    val totalLikes = likesCount.map(likesCount => likesCount.sum)
    totalLikes.flatMap(totalLikes => tweets.map(tweets => totalLikes / tweets.size))
    }
    .fallbackTo(Future {
      -1
    })


  def getAvgReTweetsPerTweet(hashTag: String): Future[Int] = {
    val tweets = twitterData.getTweets(hashTag)
    val reTweetCountList = tweets.map(tweets => tweets.map(tweet => tweet.reTweetCount))
    val totalReTweetCount = reTweetCountList.map(reTweetCount => reTweetCount.sum)
    totalReTweetCount.flatMap(Count => tweets.map(tweets => Count / tweets.length))
    }.fallbackTo(Future {
    -1
  })

}
