package org.codeoverflow.plugins.testall.tests

import org.codeoverflow.plugins.testall.{test, testallPlugin}
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

class jsontest(private val plugin: testallPlugin) extends test(plugin) {

  private implicit val formats = DefaultFormats

  private val JSON_STRING =
    """{
      | "test1": "string",
      | "test_no_2": 4,
      | "test3": [1, 2, 3],
      | "test4": {
      |   "name": "skate702",
      |   "followers": 3
      | }
      |}""".stripMargin

  private val JSON_OBJECT: JObject = ("testNo1" -> "string") ~
    ("test_no_2" -> 4) ~
    ("test3" -> List(1, 2, 3)) ~
    ("test4" -> ("name" -> "skate702") ~ ("followers" -> 3))

  private case class Streamer(name: String, followers: Int)

  override def name: String = "json test"

  override def setup(): Unit = {
    try {
      parse(JSON_STRING)
      log ("Parsed")
    } catch {
      case e: Exception => log(s"${e.getClass.getName} - ${e.getMessage}")
    }

    try {
      log(compact(render(JSON_OBJECT)))
      log(pretty(render(JSON_OBJECT)))
      log ("Rendered")
    } catch {
      case e: Exception => log(s"${e.getClass.getName} - ${e.getMessage}")
    }

    try {
      log("Test #1: " + (JSON_OBJECT \ "test1").extract[String])
      log("Test #2: " + (JSON_OBJECT.camelizeKeys \ "testNo2").extract[Int].toString)
      log("Test #3: " + (JSON_OBJECT \ "test3").extract[List[Int]].mkString(", "))
      log("Test #4.1: " + (JSON_OBJECT \\ "name").extract[String])
      log("Test #4.2: " + (JSON_OBJECT \ "test4").extract[Streamer])
    } catch {
      case e: Exception => log(s"${e.getClass.getName} - ${e.getMessage}")
    }
  }

  override def loop(): Unit = {}

  override def shutdown(): Unit = {}
}
