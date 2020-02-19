package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object Utilities {

  val postPerUserList: Future[List[UsersAndPosts]] = for {
    listOfUser <- Users.getData
    listOfPost <- Posts.getData
  } yield ModelData.postPerUser(listOfUser, listOfPost)

  val userWithMaxPosts: Future[String] = postPerUserList.map(x => getUserWithMaxPosts(x))


  val commentsPerPostList: Future[List[PostsAndComments]] = for {
    listOfPost <- Posts.getData
    listOfComments <- Comments.getData
  } yield ModelData.commentsPerPost(listOfPost, listOfComments)

  val userWithMaxCommentsOnPost: Future[String] = Users.getData.flatMap(x => {
    commentsPerPostList.map(y => getUserWithMaxCommentsOnPost(x, y))
  })

  def getUserWithMaxPosts(list: List[UsersAndPosts]): String = {
    list.sorted.reverse.head.user.name
  }

  def getUserWithMaxCommentsOnPost(userList: List[User], list: List[PostsAndComments]): String = {
    userList.filter(_.id == list.sorted.reverse.head.post.userId).head.name
  }

}