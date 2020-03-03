package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Utilities {

  val fallbackFuture: Future[String] = Future {
    "Something went wrong"
  }

  val postPerUserList: Future[List[UsersAndPosts]] = for {
    listOfUser <- Users.getData("https://jsonplaceholder.typicode.com/users")
    listOfPost <- Posts.getData("https://jsonplaceholder.typicode.com/posts")
  } yield ModelData.postPerUser(listOfUser, listOfPost)

  val userWithMaxPosts: Future[String] = postPerUserList.map(x => getUserWithMaxPosts(x)) fallbackTo fallbackFuture


  val commentsPerPostList: Future[List[PostsAndComments]] = for {
    listOfPost <- Posts.getData("https://jsonplaceholder.typicode.com/posts")
    listOfComments <- Comments.getData("https://jsonplaceholder.typicode.com/comments")
  } yield ModelData.commentsPerPost(listOfPost, listOfComments)

  val userWithMaxCommentsOnPost: Future[String] = Users.getData("https://jsonplaceholder.typicode.com/users").flatMap(x => {
    commentsPerPostList.map(y => getUserWithMaxCommentsOnPost(x, y))
  }) fallbackTo fallbackFuture

  def getUserWithMaxPosts(list: List[UsersAndPosts]): String = {
    list.sorted.reverse.head.user.name
  }

  def getUserWithMaxCommentsOnPost(userList: List[User], list: List[PostsAndComments]): String = {
    userList.filter(_.id == list.sorted.reverse.head.post.userId).head.name
  }

}
