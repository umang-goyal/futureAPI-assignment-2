package com.knoldus


import twitter4j.{Twitter, TwitterFactory}


trait TwitterConfigurationBuilder {
//  val consumerKey: String
//  val consumerSecret: String
//  val token: String
//  val tokenSecret: String

  val twitter: Twitter = new TwitterFactory().getInstance()
}
