package com.knoldus

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import org.scalatest.funsuite.AnyFunSuite

class JsonDataParserSpec extends AnyFunSuite {
  implicit val formats: DefaultFormats.type = DefaultFormats
  val user: User = User("1", "2", "3", "4", Address("5", "6", "7", "8", Geo("9", "10")), "11", "12", Company("13", "14", "15"))
  val stubUsersJsonString: String = write(user)
  val mockedUserList: List[User] = List(user)
  val jsonDataParser = new JsonDataParser

  val post: Post = Post("1", "2", "3", "4")
  val stubPostsJsonString: String = write(post)
  val mockedPostsList = List(post)

  val comment: Comment = Comment("1", "2", "3", "4", "5")
  val stubCommentsJsonString: String = write(comment)
  val mockedCommentsList = List(comment)

  test("should return list of type User") {
    val actualResult = jsonDataParser.parseUser(s"[$stubUsersJsonString]")
    val expectedResult = mockedUserList
    assert(expectedResult == actualResult)
  }

  test("should return list of type Post") {
    val actualResult = jsonDataParser.parsePosts(s"[$stubPostsJsonString]")
    val expectedResult = mockedPostsList
    assert(expectedResult == actualResult)
  }

  test("should return list of type Comment") {
    val actualResult = jsonDataParser.parseComments(s"[$stubCommentsJsonString]")
    val expectedResult = mockedCommentsList
    assert(expectedResult == actualResult)
  }
}
