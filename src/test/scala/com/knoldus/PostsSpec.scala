package com.knoldus

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite

import scala.concurrent.ExecutionContext.Implicits.global

class PostsSpec extends AnyFunSuite with MockitoSugar {
  implicit val formats: DefaultFormats.type = DefaultFormats
  val post: Post = Post("1", "2", "3", "4")
  val mockedJsonString: String = write(post)
  val mockedPostsList = List(post)
  val mockJsonFile: JsonFile = mock[JsonFile]
  val mockJsonDataParser: JsonDataParser = mock[JsonDataParser]
  val posts = new Posts(mockJsonFile, mockJsonDataParser)

  test("Returns list of post") {

    when(mockJsonFile.getFeeds("https://jsonplaceholder.typicode.com/posts")).thenReturn(mockedJsonString)
    when(mockJsonDataParser.parsePosts(mockedJsonString)).thenReturn(mockedPostsList)
    posts.getData("https://jsonplaceholder.typicode.com/posts").map(res => assert(res == mockedPostsList))
  }
}
