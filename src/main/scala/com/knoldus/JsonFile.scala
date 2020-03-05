package com.knoldus

import org.apache.commons.io.IOUtils
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder

class JsonFile {
//  private val homeURL = "https://graph.facebook.com/v2.0/"
//  private val accessToken = "CAAVHCxa"
//  private val url = s"$homeURL${"25"}/feed?method=GET&format=json&access_token=${accessToken}"\

  def getFeeds(url: String): String = {
    val request = new HttpGet(url)
    val client = HttpClientBuilder.create().build()
    val response = client.execute(request)
    IOUtils.toString(response.getEntity.getContent)
  }

}
