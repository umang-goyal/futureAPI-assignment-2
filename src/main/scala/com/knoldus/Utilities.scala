package com.knoldus

class Utilities(userList: List[User], postList: List[Post],  commentsList: List[Comment]) {
  val postPerUser: List[UsersAndPosts] = ModelData.postPerUser(userList, postList)
  val commentsPerPost: List[PostsAndComments] = ModelData.commentsPerPost(postList, commentsList)


  val userWithMaxPosts: String = postPerUser.sorted.reverse.head.user.name
  val userWithMaxCommentsOnPost: List[User] = userList.filter(_.id==commentsPerPost.sorted.reverse.head.post.userId)
}