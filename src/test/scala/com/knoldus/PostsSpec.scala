package com.knoldus

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import org.scalamock.scalatest.MockFactory
import org.scalatest.funsuite.AnyFunSuite

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class PostsSpec extends AnyFunSuite with MockFactory {
  implicit val formats: DefaultFormats.type = DefaultFormats
  val p: Post = Post("1", "2", "3", "4")
  val mockedJsonString: String = write(p)
  val mockedPostsList = List(p)


  test("Posts unit Test") {
    val mockJsonFile = mock[JsonFile]
    val mockJsonDataParser = mock[JsonDataParser]
    val posts = new Posts(mockJsonFile, mockJsonDataParser)
    (mockJsonFile getFeeds _).expects("https://jsonplaceholder.typicode.com/posts").returning(mockedJsonString)
    (mockJsonDataParser parsePosts _).expects(mockedJsonString).returning(mockedPostsList)
    val res = Await.result(posts.getData("https://jsonplaceholder.typicode.com/posts"), 1.seconds)
    posts.getData("https://jsonplaceholder.typicode.com/posts").map(res => assert(res == mockedPostsList))
  }
}
