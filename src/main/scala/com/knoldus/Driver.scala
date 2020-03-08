package com.knoldus

object Driver extends App{

  val hashTag: String = "#happy"
  val myTDobj = new MyTwitterData
  val obj = new TwitterOperations(myTDobj)
  val x = myTDobj.getTweets(hashTag)
  val y = obj.tweetsCount(hashTag)
  val z = obj.getAvgLikesPerTweet(hashTag)
  val a = obj.getAvgReTweetsPerTweet(hashTag)
  Thread.sleep(10000)
  println( x )
  println("/////////////////////////////////////////////////")
  println(y)
  println("/////////////////////////////////////////////////")
  println(z)
  println("/////////////////////////////////////////////////")
  println(a)
}
