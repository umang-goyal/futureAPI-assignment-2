package com.knoldus

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import org.scalamock.scalatest.MockFactory
import org.scalatest.funsuite.AnyFunSuite

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class UsersSpec extends AnyFunSuite with MockFactory {
  implicit val formats: DefaultFormats.type = DefaultFormats
  val user: User = User("1", "2", "3", "4", Address("5", "6", "7", "8", Geo("9", "10")), "11", "12", Company("13", "14", "15"))
  val mockedJsonString: String = write(user)
  val mockedUserList = List(user)


  test("Should return list of users") {
    val mockJsonFile = mock[JsonFile]
    val mockJsonDataParser = mock[JsonDataParser]
    val users = new Users(mockJsonFile, mockJsonDataParser)
    (mockJsonFile getFeeds _).expects("https://jsonplaceholder.typicode.com/users").returning(mockedJsonString)
    (mockJsonDataParser parseUser _).expects(mockedJsonString).returning(mockedUserList)
    // variable res is completely useless but without is the test fails
    val res = Await.result(users.getData("https://jsonplaceholder.typicode.com/users"), 1.seconds)
    users.getData("https://jsonplaceholder.typicode.com/users")
      .map(result => assert(result == mockedUserList))
  }
}