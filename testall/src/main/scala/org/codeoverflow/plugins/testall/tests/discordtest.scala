package org.codeoverflow.plugins.testall.tests

import java.awt.Color

import org.codeoverflow.chatoverflow.api.io.dto.chat.discord.{DiscordChatMessage, DiscordEmbed}
import org.codeoverflow.chatoverflow.api.io.input.chat.DiscordChatInput
import org.codeoverflow.chatoverflow.api.io.output.chat.DiscordChatOutput
import org.codeoverflow.chatoverflow.api.io.parameter.StringParameter
import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirement
import org.codeoverflow.plugins.testall.{test, testallPlugin}

import scala.collection.JavaConverters._

class discordtest(val plugin: testallPlugin,
                  val in: Requirement[DiscordChatInput],
                  val out: Requirement[DiscordChatOutput],
                  val channel: Requirement[StringParameter]) extends test(plugin, in, out) {

  override def name: String = "Discord test"
  
  override def setup(): Unit = {
    in.get.setChannel(channel.get.get)
    out.get.setChannel(channel.get.get)
    println(s"Input connected to channel ${in.get().getChannelId}")
    println(s"Output connected to channel ${out.get().getChannelId}")
    in.get.registerMessageHandler(onMessage)
    in.get.registerMessageEditHandler(onMessageEdit)
    in.get.registerMessageDeleteHandler(onMessageDelete)
    out.get.sendChatMessage("Hey I'm working! \uD83C\uDF89")
    out.get().sendFile("../config/config.xml")
    out.get().sendFile("allowed_file.png")
    out.get().sendChatMessage(DiscordEmbed.Builder()
      .withColor(Color.RED)
      .withDescription("test")
      .withAuthor("skateShiny", null, "https://cdn.discordapp.com/emojis/496389587329875981.png?v=1")
      .build())
    log(s"$name started successfully")
  }

  def onMessage(message: DiscordChatMessage): Unit = {
    if (message.getMessage == "/messages") {
      var s = "**Recent messages:**\n"
      s += in.get.getLastMessages(1000*60).asScala.mkString("\n")
      s += "\n\n**Recent private messages:**\n"
      s += in.get().getLastPrivateMessages(1000*60).asScala.mkString("\n")
      out.get.sendChatMessage(s)
    }
  }


  def onMessageEdit(oldMessage: DiscordChatMessage, newMessage: DiscordChatMessage): Unit = {
    log(s"$name: Message #${oldMessage.getId} was edited from '$oldMessage' to '$newMessage'")
  }

  def onMessageDelete(message: DiscordChatMessage): Unit = {
    log(s"$name: Message #${message.getId} was deleted  (content: $message)")
  }

  override def loop(): Unit = {}

  override def shutdown(): Unit = log(s"Stopped $name")
}
