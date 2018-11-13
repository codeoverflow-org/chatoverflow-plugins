import org.codeoverflow.chatoverflow.api.plugin.{PluginImpl, PluginManager}

class simpletestPlugin(manager: PluginManager) extends PluginImpl(manager) {

  //private val twitchChatInputReq = require.input.twitchChat("reqTwitch", "A twitch channel", false)
  private val twitchAPIInputReq = require.input.twitchStats("reqTwitchAPI", "A twitch channel", false)
  //private val nameToSayHelloToReq = require.parameter.string("reqHello", "Your name", false)
  loopInterval = 1000

  override def setup(): Unit = {
    println("Started successfully!")
    log("Hello!")
    log("Whats up?")

    log(twitchAPIInputReq.get.getUser("skate702").toString)

    //twitchChatInputReq.get.registerMessageHandler(msg => println(msg))

    //println(s"Hello ${nameToSayHelloToReq.get}!")
  }

  override def loop(): Unit = {
    println("Loop!")
  }

  override def shutdown(): Unit = {

  }
}