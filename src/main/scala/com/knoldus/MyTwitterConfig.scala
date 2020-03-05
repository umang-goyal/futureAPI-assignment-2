package com.knoldus

import com.typesafe.config.{Config, ConfigFactory}
import twitter4j.auth.AccessToken

object MyTwitterConfig extends TwitterConfigurationBuilder {

  val config: Config = ConfigFactory.load()
//  val consumerKey = "e6uS4phTxImI68qlA6h4V3zwR"
//  val consumerSecret = "M8b4Q3sudgU9mNZgJx1onUlqQYi5h5YCK1GVacjAc8yHDAohFc"
//  val token = "160922224-AKOoOasbqi3huqT7uyq4Og0Oqlucn8rKeD9IcUvU"
//  val tokenSecret = "7HgIJUmjOX2AZThvVp7RPWsZwOrW1ffpvkEpjeBSQynnH"


  twitter.setOAuthConsumer(config.getString("consumer.key"),
    config.getString("consumer.secret"))
  twitter.setOAuthAccessToken(new AccessToken(config.getString("token.key"),
    config.getString("token.secret")))
}

