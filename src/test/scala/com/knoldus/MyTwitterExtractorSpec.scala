package com.knoldus

import org.scalatest.flatspec.AsyncFlatSpec

import twitter4j.{Query, _}

class MyTwitterExtractorSpec extends AsyncFlatSpec {
  val hashTag: String = "#happy"

  "getTweets" should "retrieve tweets" in {

    val actualResult = MyTwitterExtractor.getTweets(hashTag)
    val actualBool = actualResult.map(list => list.nonEmpty)

    val expected = true

    actualBool.map(result => assert(result == expected))
  }


  "numberOfTweet" should "give number of tweet" in {

    val actualResult = MyTwitterExtractor.tweetsCount(hashTag)
    val expectedResult = 15

    actualResult.map(actual => assert(actual == expectedResult))

  }

  "getAvgLikesPerTweet" should "give number of average likes" in {

    val actualResult = MyTwitterExtractor.getAvgLikesPerTweet(hashTag)
    val expectedCount = 0

    actualResult.map(result => assert(result >= expectedCount))

  }


  "getAvgReTweetsPerTweet" should "give number of average re-tweets" in {

    val actualResult = MyTwitterExtractor.getAvgReTweetsPerTweet(hashTag)
    val expectedCount = 0

    actualResult.map(result => assert(result >= expectedCount))

  }
}
