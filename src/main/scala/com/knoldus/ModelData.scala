package com.knoldus


case class Geo(lat : String,lng : String)
case class Address(street : String,suite : String,city : String,zipcode : String,geo : Geo)
case class Company(name : String,catchPhrase : String,bs : String)
case class User(id : String,name : String,username : String,email : String,address: Address,phone : String,website: String,company: Company)
case class Comment(postId: String, id: String, name:String, email:String, body:String)
case class Post(userId: String, id: String, title:String, body:String)
case class UsersAndPosts(user: User, posts: List[Post]) extends Ordered[UsersAndPosts] {
  def compare(that: UsersAndPosts): Int = this.posts.length compare that.posts.length
}
case class PostsAndComments(post: Post, comments: List[Comment]) extends Ordered[PostsAndComments] {
  def compare(that: PostsAndComments): Int = this.comments.length compare that.comments.length
}

class ModelData {

  def postPerUser(userList: List[User], postList: List[Post]): List[UsersAndPosts] = {

    @scala.annotation.tailrec
    def inner(userList: List[User], postPerUser: List[UsersAndPosts]): List[UsersAndPosts] = {

      userList match {
        case Nil => postPerUser
        case user :: Nil => postPerUser :+ UsersAndPosts(user, postList.filter(_.userId == user.id))
        case user :: rest => inner(rest, postPerUser :+ UsersAndPosts(user, postList.filter(_.userId == user.id)))
      }
    }

    inner(userList, List.empty[UsersAndPosts])
  }


  def commentsPerPost(postList: List[Post], commentsList: List[Comment]): List[PostsAndComments] = {
    @scala.annotation.tailrec
    def inner(postList: List[Post], commentsPerPost: List[PostsAndComments]): List[PostsAndComments] = {

      postList match {
        case Nil => commentsPerPost
        case post :: Nil => commentsPerPost :+ PostsAndComments(post, commentsList.filter(_.postId == post.id))
        case post :: rest => inner(rest, commentsPerPost :+ PostsAndComments(post, commentsList.filter(_.postId == post.id)))

      }
    }

    inner(postList, List.empty[PostsAndComments])
  }
}




