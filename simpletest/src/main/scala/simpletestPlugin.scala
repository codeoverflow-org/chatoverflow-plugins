import org.codeoverflow.chatoverflow.api.io.event.chat.twitch.TwitchChatMessageSendEvent
import org.codeoverflow.chatoverflow.api.plugin.{PluginImpl, PluginManager}

class simpletestPlugin(manager: PluginManager) extends PluginImpl(manager) {

  private val twitchChatInputReq = require.input.twitchChat("reqTwitch", "A twitch channel", false)
  private val nameToSayHelloToReq = require.parameter.string("reqHello", "Your name", false)
  private val twitchChannelReq = require.parameter.string("reqTwitchChannel", "The name of the channel that you want to connect to.", false)
  loopInterval = 1000

  override def setup(): Unit = {
    println("Started successfully!")
    log("Hello!")
    log("Whats up?")
    log("I am new!")
    twitchChatInputReq.get.setChannel(twitchChannelReq.get.get)
    twitchChatInputReq.get.registerChatMessageSendEventHandler(onMessage)

    println(s"Hello ${nameToSayHelloToReq.get.get()}!")
  }

  private def onMessage(event: TwitchChatMessageSendEvent): Unit = {
    println(event.getMessage)
  }

  override def loop(): Unit = {
    twitchChatInputReq.get.getLastMessages(loopInterval)
      .forEach(msg => log(s"${msg.getAuthor}: ${msg.getMessage}"))
  }

  override def shutdown(): Unit = {

  }
}