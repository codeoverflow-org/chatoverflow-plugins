package org.codeoverflow.plugins.testall.tests

import org.codeoverflow.chatoverflow.api.io.input.SerialInput
import org.codeoverflow.chatoverflow.api.io.output.SerialOutput
import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirement
import org.codeoverflow.plugins.testall.{test, testallPlugin}

class serialtest(val plugin: testallPlugin,
                 val in: Requirement[SerialInput],
                 val out: Requirement[SerialOutput]) extends test(plugin, in, out) {

  override def name: String = "Serial Connection test"

  override def setup(): Unit = {
    in.get().registerDataAvailableEventHandler(e => log(e.getAsString))
    out.get().getPrintStream.println("#Hello World!")
  }

  override def loop(): Unit = {}

  override def shutdown(): Unit = log("Stopped")
}
