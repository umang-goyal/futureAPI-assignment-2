package com.knoldus

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import org.scalamock.scalatest.MockFactory
import org.scalatest.funsuite.AnyFunSuite

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._


class CommentsSpec extends AnyFunSuite with MockFactory{
  implicit val formats: DefaultFormats.type = DefaultFormats
  val c: Comment = Comment("1", "2", "3", "4", "5")
  val mockedJsonString: String = write(c)
  val mockedCommentsList = List(c)

  test("Comments unit Test") {
    val mockJsonFile = mock[JsonFile]
    val mockJsonDataParser = mock[JsonDataParser]
    val comments = new Comments(mockJsonFile, mockJsonDataParser)
    (mockJsonFile getFeeds _).expects("https://jsonplaceholder.typicode.com/comments").returning(mockedJsonString)
    (mockJsonDataParser parseComments  _).expects(mockedJsonString).returning(mockedCommentsList)
    val res = Await.result(comments.getData("https://jsonplaceholder.typicode.com/comments"), 1.seconds)
    comments.getData("https://jsonplaceholder.typicode.com/comments").map(res => assert(res == mockedCommentsList))
  }
}
