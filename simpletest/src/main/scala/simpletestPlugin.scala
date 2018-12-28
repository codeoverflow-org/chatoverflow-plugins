import org.codeoverflow.chatoverflow.api.plugin.{PluginImpl, PluginManager}

class simpletestPlugin(manager: PluginManager) extends PluginImpl(manager) {

  private val twitchChatInputReq = require.input.twitchChat("reqTwitch", "A twitch channel", false)
  private val nameToSayHelloToReq = require.parameter.string("reqHello", "Your name", false)
  loopInterval = 1000

  override def setup(): Unit = {
    println("Started successfully!")
    log("Hello!")
    log("Whats up?")
    log("I am new!")

    twitchChatInputReq.get.registerMessageHandler(msg => println(msg))

    println(s"Hello ${nameToSayHelloToReq.get.get()}!")
  }

  override def loop(): Unit = {
    twitchChatInputReq.get.getLastMessages(loopInterval)
      .forEach(msg => log(s"${msg.getAuthor}: ${msg.getMessage}"))
  }

  override def shutdown(): Unit = {

  }
}