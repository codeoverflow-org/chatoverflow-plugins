package org.codeoverflow.plugins.testall.tests

import org.codeoverflow.chatoverflow.api.io.event.chat.twitch.TwitchChatMessageReceiveEvent
import org.codeoverflow.chatoverflow.api.io.input.chat.TwitchChatInput
import org.codeoverflow.chatoverflow.api.io.output.chat.TwitchChatOutput
import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirement
import org.codeoverflow.plugins.testall.{test, testallPlugin}

class twitchtest(val plugin: testallPlugin,
                 val in: Requirement[TwitchChatInput],
                 val out: Requirement[TwitchChatOutput]) extends test(plugin, in, out) {

  override def name: String = "Twitch test"

  override def setup(): Unit = {
    in.get.setChannel("skate702")
    out.get.setChannel("skate702")
    in.get.registerChatMessageReceiveEventHandler(onMessage)
    out.get.sendChatMessage("Say hi to codeoverflow!")
  }

  private def onMessage(event: TwitchChatMessageReceiveEvent): Unit = {
    val msg = event.getMessage
    if (msg.getMessage.toLowerCase.contains("chatoverflow")) {
      out.get().sendChatMessage(s"Hi ${msg.getAuthor.getDisplayName}!")
    }
  }

  override def loop(): Unit = {
    in.get.getLastMessages(plugin.getLoopInterval)
      .forEach(msg => log(s"${msg.getAuthor}: ${msg.getMessage}"))
  }

  override def shutdown(): Unit = log("Stopped")
}
