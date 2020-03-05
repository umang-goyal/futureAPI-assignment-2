package com.knoldus

import net.liftweb.json._


class JsonDataParser {
  implicit val formats: DefaultFormats.type = DefaultFormats
  def parseUser(jsonData: String): List[User] = {
    val parsedJsonData = net.liftweb.json.parse(jsonData)
    parsedJsonData.children map { userData =>
      userData.extract[User]
    }
  }

  def parseComments(jsonData: String): List[Comment]={
    val parsedJsonData = net.liftweb.json.parse(jsonData)
    parsedJsonData.children map { commentData =>
      commentData.extract[Comment]
    }
  }

  def parsePosts(jsonData: String): List[Post]={
    val parsedJsonData = net.liftweb.json.parse(jsonData)
    parsedJsonData.children map { postData =>
      postData.extract[Post]
    }
  }



  implicit def extract(json: JValue): String = json match {
    case JNothing => ""
    case data => data.extract[String]
  }
}
