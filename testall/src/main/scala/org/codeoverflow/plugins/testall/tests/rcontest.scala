package org.codeoverflow.plugins.testall.tests

import org.codeoverflow.chatoverflow.api.io.input.RconInput
import org.codeoverflow.chatoverflow.api.io.output.RconOutput
import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirement
import org.codeoverflow.plugins.testall.{test, testallPlugin}

class rcontest(val plugin: testallPlugin,
               val in: Requirement[RconInput],
               val out: Requirement[RconOutput]) extends test(plugin, in, out) {

  override def name: String = "RCON test"

  override def setup(): Unit = {
    out.get.sendCommand("say Hello World!")
    log(s"Players list: ${in.get.getCommandOutput("list")}")
  }

  override def loop(): Unit = {}

  override def shutdown(): Unit = {}
}
