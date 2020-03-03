package com.knoldus

import org.scalatest.flatspec.AsyncFlatSpec

class UtilitiesSpec extends AsyncFlatSpec{

  "Run test" should "give user name with max post" in {
    Utilities.userWithMaxPosts.map(res => assert(res == "Clementina DuBuque"))
  }

  "Run test" should "give use with max comment on post" in {
    Utilities.userWithMaxCommentsOnPost.map(res => assert(res == "Clementina DuBuque"))
  }
}
