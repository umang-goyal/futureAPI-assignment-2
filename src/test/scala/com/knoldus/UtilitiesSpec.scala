package com.knoldus

import org.scalatest.flatspec.AsyncFlatSpec

class UtilitiesSpec extends AsyncFlatSpec {

  val jsonFile = new JsonFile
  val jsonDataParser = new JsonDataParser
  val users = new Users(jsonFile, jsonDataParser)
  val posts = new Posts(jsonFile, jsonDataParser)
  val comments = new Comments(jsonFile, jsonDataParser)
  val utilities = new Utilities(users, posts, comments)

  "Run test" should "give user name with max post" in {
    utilities.userWithMaxPosts.map(res => assert(res == "Clementina DuBuque"))
  }

  "Run test" should "give use with max comment on post" in {
    utilities.userWithMaxCommentsOnPost.map(res => assert(res == "Clementina DuBuque"))
  }
}
