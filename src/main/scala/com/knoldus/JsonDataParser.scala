package com.knoldus

import net.liftweb.json._

case class Geo(lat : String,lng : String)
case class Address(street : String,suite : String,city : String,zipcode : String,geo : Geo)
case class Company(name : String,catchPhrase : String,bs : String)

case class User(id : String,name : String,username : String,email : String,address: Address,phone : String,website: String,company: Company)

case class Comment(postId: String, id: String, name:String, email:String, body:String)

case class Post(userId: String, id: String, title:String, body:String)


object JsonDataParser {
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
