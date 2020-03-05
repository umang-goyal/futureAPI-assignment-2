package com.knoldus

import org.scalamock.scalatest.MockFactory
import org.scalatest.funsuite.AnyFunSuite

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class UtilitiesUnitSpec extends AnyFunSuite with MockFactory {
  //implicit val formats: DefaultFormats.type = DefaultFormats
  val StubUserList = Future {
    List(User("1", "1", "3", "4", Address("5", "6", "7", "8", Geo("9", "10")), "11", "12", Company("13", "14", "15")),
      User("2", "2", "3", "4", Address("5", "6", "7", "8", Geo("9", "10")), "11", "12", Company("13", "14", "15")),
      User("3", "3", "3", "4", Address("5", "6", "7", "8", Geo("9", "10")), "11", "12", Company("13", "14", "15")))
  }
  val StubPostList = Future {
    List(Post("1", "1", "3", "4"),
      Post("1", "2", "3", "4"),
      Post("2", "3", "3", "4"),
      Post("3", "4", "3", "4"))
  }
  val StubCommentsList = Future {
    List(Comment("1", "1", "3", "4", "5"),
      Comment("1", "2", "3", "4", "5"),
      Comment("4", "3", "3", "4", "5"),
      Comment("2", "4", "3", "4", "5"),
      Comment("3", "5", "3", "4", "5"))
  }
  test("should return user with max post") {
    val mockUsers = mock[Users]
    val mockPosts = mock[Posts]
    val mockComments = mock[Comments]

    val utilities = new Utilities(mockUsers, mockPosts, mockComments)
    (mockUsers getData _).expects("https://jsonplaceholder.typicode.com/users").returning(StubUserList).once()
    (mockPosts getData _).expects("https://jsonplaceholder.typicode.com/posts").returning(StubPostList).once()
    (mockComments getData _).expects("https://jsonplaceholder.typicode.com/comments").returning(StubCommentsList).once()
    val res = Await.result(utilities.userWithMaxPosts, 10.seconds)
    utilities.userWithMaxPosts.map(res => assert(res == "1"))
  }

  test("should return user with max comments on post") {
    val mockUsers = mock[Users]
    val mockPosts = mock[Posts]
    val mockComments = mock[Comments]

    val utilities = new Utilities(mockUsers, mockPosts, mockComments)
    (mockUsers getData _).expects("https://jsonplaceholder.typicode.com/users").returning(StubUserList).once()
    (mockPosts getData _).expects("https://jsonplaceholder.typicode.com/posts").returning(StubPostList).once()
    (mockComments getData _).expects("https://jsonplaceholder.typicode.com/comments").returning(StubCommentsList).once()
    val res = Await.result(utilities.userWithMaxCommentsOnPost, 1.seconds)
    utilities.userWithMaxCommentsOnPost.map(result => assert(result == "1"))
  }
}
