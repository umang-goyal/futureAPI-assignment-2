package com.knoldus

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite

import scala.concurrent.ExecutionContext.Implicits.global


class CommentsSpec extends AnyFunSuite with MockitoSugar {
  implicit val formats: DefaultFormats.type = DefaultFormats
  val comment: Comment = Comment("1", "2", "3", "4", "5")
  val mockedJsonString: String = write(comment)
  val mockedCommentsList = List(comment)
  val mockJsonFile: JsonFile = mock[JsonFile]
  val mockJsonDataParser: JsonDataParser = mock[JsonDataParser]
  val comments = new Comments(mockJsonFile, mockJsonDataParser)

  test("Returns list of comments") {

    when(mockJsonFile getFeeds "https://jsonplaceholder.typicode.com/comments").thenReturn(mockedJsonString)
    when(mockJsonDataParser.parseComments(mockedJsonString)).thenReturn(mockedCommentsList)
    comments.getData("https://jsonplaceholder.typicode.com/comments").map(res => assert(res == mockedCommentsList))
  }
}
