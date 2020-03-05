package com.knoldus

object Driver extends App{

  val hashTag: String = "#happy"

  val x = MyTwitterExtractor.getTweets(hashTag)
  val y = MyTwitterExtractor.tweetsCount(hashTag)
  val z = MyTwitterExtractor.getAvgLikesPerTweet(hashTag)
  val a = MyTwitterExtractor.getAvgReTweetsPerTweet(hashTag)
  Thread.sleep(10000)
  println( x )
  println("/////////////////////////////////////////////////")
  println(y)
  println("/////////////////////////////////////////////////")
  println(z)
  println("/////////////////////////////////////////////////")
  println(a)
}
