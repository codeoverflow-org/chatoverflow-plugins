package org.codeoverflow.plugins.testall.tests

import java.awt.Color

import org.codeoverflow.chatoverflow.api.io.dto.chat.discord.DiscordEmbed
import org.codeoverflow.chatoverflow.api.io.event.chat.discord.{DiscordChatMessageDeleteEvent, DiscordChatMessageEditEvent, DiscordChatMessageReceiveEvent}
import org.codeoverflow.chatoverflow.api.io.input.chat.DiscordChatInput
import org.codeoverflow.chatoverflow.api.io.output.chat.DiscordChatOutput
import org.codeoverflow.chatoverflow.api.io.parameter.StringParameter
import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirement
import org.codeoverflow.plugins.testall.{test, testallPlugin}

import scala.jdk.CollectionConverters._

/**
  * Test for the discord service, logs edited and deleted messages, tests sending of messages, embeds and files,
  * allows listing the recent messages with /message command
  */
class discordtest(val plugin: testallPlugin,
                  val in: Requirement[DiscordChatInput],
                  val out: Requirement[DiscordChatOutput],
                  val channel: Requirement[StringParameter]) extends test(plugin, in, out, channel) {

  override def name: String = "Discord test"
  
  override def setup(): Unit = {
    in.get.setChannel(channel.get.get)
    out.get.setChannel(channel.get.get)
    println(s"Input connected to channel ${in.get().getChannelId}")
    println(s"Output connected to channel ${out.get().getChannelId}")
    in.get.registerEventHandler(onMessage, classOf[DiscordChatMessageReceiveEvent])
    in.get.registerChatMessageEditEventHandler(onMessageEdit)
    in.get.registerChatMessageDeleteEventHandler(onMessageDelete)
    out.get.sendChatMessage("Hey I'm working! \uD83C\uDF89")
    out.get().sendFile("../config/config.xml")
    out.get().sendFile("allowed_file.png")
    out.get().sendChatMessage(DiscordEmbed.Builder()
      .withColor(Color.RED)
      .withDescription("test")
      .withAuthor("skateShiny", null, "https://cdn.discordapp.com/emojis/496389587329875981.png?v=1")
      .build())
    log("Started successfully")
  }

  def onMessage(event: DiscordChatMessageReceiveEvent): Unit = {
    if (event.getMessage.getMessage == "/messages") {
      var s = "**Recent messages:**\n"
      s += in.get.getLastMessages(1000*60).asScala.mkString("\n")
      s += "\n\n**Recent private messages:**\n"
      s += in.get().getLastPrivateMessages(1000*60).asScala.mkString("\n")
      out.get.sendChatMessage(s)
    }
  }


  def onMessageEdit(event: DiscordChatMessageEditEvent): Unit = {
    log(s"Message #${event.getMessage.getId} was edited from '${event.getOldMessage.getMessage}' to '${event.getMessage.getMessage}'")
  }

  def onMessageDelete(event: DiscordChatMessageDeleteEvent): Unit = {
    log(s"Message #${event.getMessage.getId} was deleted  (content: ${event.getMessage.getMessage})")
  }

  override def loop(): Unit = {}

  override def shutdown(): Unit = log("Stopped")
}
