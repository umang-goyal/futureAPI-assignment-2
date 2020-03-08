package com.knoldus

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import org.mockito.MockitoSugar
import org.scalatest.flatspec.AsyncFlatSpec

class UsersSpec extends AsyncFlatSpec with MockitoSugar {
  implicit val formats: DefaultFormats.type = DefaultFormats
  val user: User = User("1", "2", "3", "4", Address("5", "6", "7", "8", Geo("9", "10")), "11", "12", Company("13", "14", "15"))
  val mockedJsonString: String = write(user)
  val mockedUserList: List[User] = List(user)
  val mockJsonFile: JsonFile = mock[JsonFile]
  val mockJsonDataParser: JsonDataParser = mock[JsonDataParser]
  val users = new Users(mockJsonFile, mockJsonDataParser)

  "run test" should "return user list" in {
    when(mockJsonFile.getFeeds("https://jsonplaceholder.typicode.com/users")).thenReturn(mockedJsonString)
    when(mockJsonDataParser.parseUser(mockedJsonString)).thenReturn(mockedUserList)
    users.getData("https://jsonplaceholder.typicode.com/users")
      .map(result => assert(result == mockedUserList))
  }
}

