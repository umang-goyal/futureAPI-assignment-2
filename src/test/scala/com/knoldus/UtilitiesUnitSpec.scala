package com.knoldus

import org.mockito.MockitoSugar
import org.scalatest.flatspec.AsyncFlatSpec

import scala.concurrent.Future

class UtilitiesUnitSpec extends AsyncFlatSpec with MockitoSugar {
  val user1: User = User("1", "1", "3", "4", Address("5", "6", "7", "8", Geo("9", "10")), "11", "12", Company("13", "14", "15"))
  val user2: User = User("2", "2", "3", "4", Address("5", "6", "7", "8", Geo("9", "10")), "11", "12", Company("13", "14", "15"))
  val user3: User = User("3", "3", "3", "4", Address("5", "6", "7", "8", Geo("9", "10")), "11", "12", Company("13", "14", "15"))
  val post1: Post = Post("1", "1", "3", "4")
  val post2: Post = Post("1", "2", "3", "4")
  val post3: Post = Post("3", "4", "3", "4")
  val comment1: Comment = Comment("1", "1", "3", "4", "5")
  val comment2: Comment = Comment("1", "2", "3", "4", "5")
  val comment3: Comment = Comment("1", "3", "3", "4", "5")
  val comment4: Comment = Comment("2", "4", "3", "4", "5")
  val comment5: Comment = Comment("3", "5", "3", "4", "5")

  val StubUserList = List(user1, user2, user3)
  val StubPostList = List(post1, post2, post3)
  val StubCommentsList = List(comment1, comment2, comment3, comment4, comment5)
  val mockUsers: Users = mock[Users]
  val mockPosts: Posts = mock[Posts]
  val mockComments: Comments = mock[Comments]
  val mockModelData: ModelData = mock[ModelData]
  val utilities = new Utilities(mockUsers, mockPosts, mockComments, mockModelData)

  "run test" should "return with max post" in {
    when(mockUsers.getData("https://jsonplaceholder.typicode.com/users")).thenReturn(Future {
      StubUserList
    })
    when(mockPosts.getData("https://jsonplaceholder.typicode.com/posts")).thenReturn(Future {
      StubPostList
    })
    when(mockComments.getData("https://jsonplaceholder.typicode.com/comments")).thenReturn(Future {
      StubCommentsList
    })
    when(mockModelData.postPerUser(StubUserList, StubPostList)).thenReturn(List(UsersAndPosts(user1, List(post1)),
      UsersAndPosts(user2, List(post2)),
      UsersAndPosts(user3, List(post3))))
    utilities.userWithMaxPosts.map(res => assert(res == "3"))
  }

  "run test" should "return user with max comments on post" in {
    when(mockUsers.getData("https://jsonplaceholder.typicode.com/users")).thenReturn(Future {
      StubUserList
    })
    when(mockPosts.getData("https://jsonplaceholder.typicode.com/posts")).thenReturn(Future {
      StubPostList
    })
    when(mockComments.getData("https://jsonplaceholder.typicode.com/comments")).thenReturn(Future {
      StubCommentsList
    })
    when(mockModelData.commentsPerPost(StubPostList, StubCommentsList)).thenReturn(List(PostsAndComments(post1, List(comment1, comment2, comment3)),
      PostsAndComments(post2, List(comment4)), PostsAndComments(post3, List(comment5))))
    utilities.userWithMaxCommentsOnPost.map(result => assert(result == "1"))
  }
}
