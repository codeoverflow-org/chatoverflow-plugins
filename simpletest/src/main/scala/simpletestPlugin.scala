import org.codeoverflow.chatoverflow.api.io.input.chat.TwitchChatInput
import org.codeoverflow.chatoverflow.api.plugin.configuration.{Configuration, ParameterRequirement, SourceRequirement}
import org.codeoverflow.chatoverflow.api.plugin.{Plugin, PluginManager}

class simpletestPlugin(manager: PluginManager) extends Plugin {

  val config = new Configuration
  config.addInputRequirement("Twitch Channel", new SourceRequirement[TwitchChatInput])
  //config.addOutputRequirement("Any Channel", new SourceRequirement[ChatOutput])
  config.addParameterRequirement("Some name", new ParameterRequirement[String])

  override def start(): Unit = {
    println("Started successfully!")
    println(s"Dummy message is:${manager.getDummyMessage}")

    val chatInput = config.getInputs.get("Twitch Channel").getSource.asInstanceOf[TwitchChatInput]
    // chatInput.gimmeSomeMessagasJo
  }

  override def getRequirements: Configuration = config
}