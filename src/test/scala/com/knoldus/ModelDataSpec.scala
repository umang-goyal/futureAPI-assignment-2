package com.knoldus

import org.scalatest.funsuite.AnyFunSuite

class ModelDataSpec extends AnyFunSuite{
  val user = User("1", "1", "3", "4", Address("5", "6", "7", "8", Geo("9", "10")), "11", "12", Company("13", "14", "15"))
  val StubUserList = List(user)
  val post = Post("1", "2", "3", "4")
  val StubPostList = List(post)
  val comment = Comment("1", "1", "3", "4", "5")
  val StubCommentsList = List(comment)

  test("should return list UserAndPost"){
    val actualResult = ModelData.postPerUser(StubUserList,StubPostList)
    val expectedResult = List(UsersAndPosts(user,StubPostList))
    assert(expectedResult==actualResult)
  }

  test("should return list of PostAndComments"){
    val actualValue = ModelData.commentsPerPost(StubPostList,StubCommentsList)
    val expectedResult = List(PostsAndComments(post,StubCommentsList))
  }
}

