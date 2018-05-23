import org.codeoverflow.chatoverflow.api.io.input.chat.TwitchChatInput
import org.codeoverflow.chatoverflow.api.io.input.event.EventInput
import org.codeoverflow.chatoverflow.api.plugin.configuration.{Configuration, ParameterRequirement, SourceRequirement}
import org.codeoverflow.chatoverflow.api.plugin.{Plugin, PluginManager}

class simpletestPlugin(manager: PluginManager) extends Plugin {

  val config = new Configuration
  config.addInputRequirement("Twitch Channel", new SourceRequirement[TwitchChatInput])
  config.addInputRequirement("Some other stuff", new SourceRequirement[EventInput])
  //config.addOutputRequirement("Any Channel", new SourceRequirement[ChatOutput])
  config.addParameterRequirement("Some name", new ParameterRequirement[String])

  override def start(): Unit = {
    println("Started successfully!")
    println(s"Dummy message is:${manager.getDummyMessage}")

    val chatInput = config.getInputs.get("Twitch Channel").getSource.asInstanceOf[TwitchChatInput]
    chatInput.registerMessageHandler(msg => println(msg))

    println(s"Param: ${config.getParameters.get("Some name").getParameter}")

    while (true) {
      Thread.sleep(10)
    }
  }

  override def getRequirements: Configuration = config
}