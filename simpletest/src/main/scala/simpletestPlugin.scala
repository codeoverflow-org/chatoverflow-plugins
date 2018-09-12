import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirements
import org.codeoverflow.chatoverflow.api.plugin.{Plugin, PluginManager}

class simpletestPlugin(manager: PluginManager) extends Plugin {

  private val require = new Requirements
  private val twitchChatInput = require.input.twitchChat("reqTwitch", "A twitch channel", false)
  private val nameToSayHelloTo = require.parameter.string("reqHello", "Your name", false)
  private val twitchChatOutput = require.output.twitchChat("reqTwitchO", "A twitch channel", false)

  override def start(): Unit = {
    println("Started successfully!")
    println(s"Dummy not loaded msg is:${manager.getDummyMessage}")

    twitchChatInput.getValue.registerMessageHandler(msg => println(msg.toHTMLString))

    println(s"Hello ${nameToSayHelloTo.getValue}!")

    Thread.sleep(5000)


    twitchChatOutput.getValue.sendChatMessage("I am a simple bot message!")

    while (true) {
      Thread.sleep(10)
    }
  }

  override def getRequirements: Requirements = require
}