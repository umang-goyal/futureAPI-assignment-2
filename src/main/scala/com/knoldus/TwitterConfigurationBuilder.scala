package com.knoldus


import twitter4j.{Twitter, TwitterFactory}

trait TwitterConfigurationBuilder {
  val twitter: Twitter = new TwitterFactory().getInstance()
}
