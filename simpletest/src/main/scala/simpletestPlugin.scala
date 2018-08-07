import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirements
import org.codeoverflow.chatoverflow.api.plugin.{Plugin, PluginManager}

class simpletestPlugin(manager: PluginManager) extends Plugin {

  private val require = new Requirements
  //private val twitchChatInput = require.input.twitchChat("reqTwitch", "A twitch channel", false)
  private val nameToSayHelloTo = require.parameter.string("reqHello", "Your name", false)

  override def start(): Unit = {
    println("Started successfully!")
    println(s"Dummy message is:${manager.getDummyMessage}")

    //twitchChatInput.getValue.registerMessageHandler(msg => println(msg))

    println(s"Hello ${nameToSayHelloTo.getValue}!")

    while (true) {
      Thread.sleep(10)
    }
  }

  override def getRequirements: Requirements = require
}