package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Utilities(users: Users, posts: Posts, comments: Comments, modelData: ModelData) {

  val fallbackFuture: Future[String] = Future {
    "Something went wrong"
  }

  def postPerUserList: Future[List[UsersAndPosts]] = for {
    listOfUser <- users.getData("https://jsonplaceholder.typicode.com/users")
    listOfPost <- posts.getData("https://jsonplaceholder.typicode.com/posts")
  } yield modelData.postPerUser(listOfUser, listOfPost)

  def userWithMaxPosts: Future[String] = postPerUserList.map(x => getUserWithMaxPosts(x)) fallbackTo fallbackFuture

  private def getUserWithMaxPosts(list: List[UsersAndPosts]): String = {
    list.sorted.reverse.head.user.name
  }

  def commentsPerPostList: Future[List[PostsAndComments]] = for {
    listOfPost <- posts.getData("https://jsonplaceholder.typicode.com/posts")
    listOfComments <- comments.getData("https://jsonplaceholder.typicode.com/comments")
  } yield modelData.commentsPerPost(listOfPost, listOfComments)

  def userWithMaxCommentsOnPost: Future[String] = users.getData("https://jsonplaceholder.typicode.com/users").flatMap(x => {
    commentsPerPostList.map(y => getUserWithMaxCommentsOnPost(x, y))
  }) fallbackTo fallbackFuture

  private def getUserWithMaxCommentsOnPost(userList: List[User], list: List[PostsAndComments]): String = {
    userList.filter(_.id == list.sorted.reverse.head.post.userId).head.name
  }

}
