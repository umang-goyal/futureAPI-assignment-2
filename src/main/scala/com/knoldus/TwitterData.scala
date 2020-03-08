package com.knoldus

import twitter4j.Twitter

abstract class TwitterData(twitterConfigurationBuilder: TwitterConfigurationBuilder) {
  val twitter: Twitter
}