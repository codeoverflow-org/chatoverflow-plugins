package org.codeoverflow.plugins.testall.tests

import org.codeoverflow.plugins.testall.{test, testallPlugin}
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

class jsontest(private val plugin: testallPlugin) extends test(plugin) {

  private implicit val formats: DefaultFormats.type = DefaultFormats

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

  private def JSON_OBJECT: JObject = ("test1" -> "string") ~
    ("test_no_2" -> 4) ~
    ("test3" -> 1) ~
    ("test4" -> ("name" -> "skate702") ~ ("followers" -> 3))

  override def name: String = "json test"

  override def setup(): Unit = {
    try {
      classOf[jsontest].getFields
      log("Sandbox isn't working")
    } catch {
      case _: SecurityException => log("Sandbox is working")
    }

    try {
      parse(JSON_STRING)
      log ("Parsed")
    } catch {
      case e: Exception => log(s"${e.getClass.getName} - ${e.getMessage}")
    }

    try {
      JSON_OBJECT
      log ("Object")
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
      log("Test #3: " + (parse(JSON_STRING) \ "test3").extract[List[Int]].mkString(", "))
      log("Test #4: " + (JSON_OBJECT \\ "name").extract[String])
    } catch {
      case e: Exception => log(s"${e.getClass.getName} - ${e.getMessage}")
    }
  }

  override def loop(): Unit = {}

  override def shutdown(): Unit = {}
}
