package com.knoldus

import org.mockito.MockitoSugar
import org.scalatest.flatspec.AsyncFlatSpec

import scala.concurrent.Future

class TwitterOperationsSpec extends AsyncFlatSpec with MockitoSugar {
  val stubTweetStatusList = List(TweetStatus(123, 0, 230), TweetStatus(1235916, 0, 0), TweetStatus(5978, 1, 0), TweetStatus(11652642, 0, 675), TweetStatus(12354, 0, 675), TweetStatus(123598, 0, 4), TweetStatus(1291673, 0, 4), TweetStatus(11958272, 0, 5), TweetStatus(42927876L, 0, 0), TweetStatus(5772928L, 0, 11108), TweetStatus(135208961, 0, 0), TweetStatus(123116893, 0, 0), TweetStatus(409089, 0, 9861), TweetStatus(98870016, 0, 178), TweetStatus(125718017, 0, 121))
  val mockedMyTwitterData: MyTwitterData = mock[MyTwitterData]
  val obj = new TwitterOperations(mockedMyTwitterData)
  val hashTag = "#happy"

  "run test" should "return tweet count" in {
    when(mockedMyTwitterData.getTweets(hashTag)).thenReturn(Future {
      stubTweetStatusList
    })
    obj.tweetsCount("#happy").map(res => assert(res == 15))
  }


  "run test" should "return avg likes per tweet" in {
    when(mockedMyTwitterData.getTweets(hashTag)).thenReturn(Future {
      stubTweetStatusList
    })
    obj.getAvgLikesPerTweet(hashTag).map(res => assert(res == 0))
  }


  "run test" should "return avg re tweet per tweet" in {
    when(mockedMyTwitterData.getTweets(hashTag)).thenReturn(Future {
      stubTweetStatusList
    })
    obj.getAvgReTweetsPerTweet(hashTag).map(res => assert(res == 1524))
  }
}
