package com.knoldus

import com.typesafe.config.{Config, ConfigFactory}
import twitter4j.auth.AccessToken

object MyTwitterConfig extends TwitterConfigurationBuilder {

  val config: Config = ConfigFactory.load()


  twitter.setOAuthConsumer(config.getString("consumer.key"),
    config.getString("consumer.secret"))
  twitter.setOAuthAccessToken(new AccessToken(config.getString("token.key"),
    config.getString("token.secret")))
}

