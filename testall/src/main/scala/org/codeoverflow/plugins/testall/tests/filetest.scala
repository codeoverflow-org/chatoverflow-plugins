package org.codeoverflow.plugins.testall.tests

import org.codeoverflow.chatoverflow.api.io.input.FileInput
import org.codeoverflow.chatoverflow.api.io.output.FileOutput
import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirement
import org.codeoverflow.plugins.testall.{test, testallPlugin}

/**
  * Test the various different file system actions provided by FileInput and FileOutput
  */
class filetest(val plugin: testallPlugin, val in: Requirement[FileInput], val out: Requirement[FileOutput]) extends test(plugin, in, out) {

  override def name: String = "Filesystem test"

  override def setup(): Unit = {
    log("Testing creation of folder")
    log(out.get.createDirectory("test").toString)

    log("Testing creation of folder #2")
    log(out.get.createDirectory("test2/test2").toString)

    log("Testing creation of folder #3")
    log(out.get.createDirectory("ich/bin/ein/folder").toString)

    log("Tesitng if file exists")
    log(out.get.exists("test.json").toString)

    log("Tesitng if file exists (non existant)")
    log(out.get.exists("test2.json").toString)


    log("Test file reading:")
    val file = in.get.getFile("test.json")
    log(if (file.isPresent) file.get() else "false")

    log("Test file writing:")
    log(out.get.saveFile(
      """{
        |  "type": "test",
        |  "result": "success"
        |}
      """.stripMargin, "out.json").toString)

    log("Tesitng if folder exists")
    log(out.get.exists("test/").toString)
    log(out.get.exists("test").toString)

    log("Testing deletion of a folder")
    log(out.get.delete("test").toString)
    log(out.get.delete("test2/test2").toString)
    log(out.get.delete("ich/bin/ein/folder").toString)

    log("Testing deletion of a file")
    log(out.get.delete("out.json").toString)
  }

  override def loop(): Unit = {}

  override def shutdown(): Unit = log("Stopped")
}
