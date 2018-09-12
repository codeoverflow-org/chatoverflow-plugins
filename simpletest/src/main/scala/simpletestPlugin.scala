import org.codeoverflow.chatoverflow.api.plugin.{PluginImpl, PluginManager}

class simpletestPlugin(manager: PluginManager) extends PluginImpl {

  private val twitchChatInputReq = require.input.twitchChat("reqTwitch", "A twitch channel", false)
  private val nameToSayHelloToReq = require.parameter.string("reqHello", "Your name", false)
  loopInterval = 1000

  override def setup(): Unit = {
    println("Started successfully!")
    println(s"Dummy message is:${manager.getDummyMessage}")

    twitchChatInputReq.get.registerMessageHandler(msg => println(msg))

    println(s"Hello ${nameToSayHelloToReq.get}!")
  }

  override def loop(): Unit = {
    println("Loop!")
  }

  override def shutdown(): Unit = {

  }
}