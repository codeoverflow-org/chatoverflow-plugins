import org.codeoverflow.chatoverflow.api.io.input.chat.TwitchChatInput
import org.codeoverflow.chatoverflow.api.plugin.configuration.{ParameterRequirement, Requirements, SourceRequirement}
import org.codeoverflow.chatoverflow.api.plugin.{Plugin, PluginManager}

class simpletestPlugin(manager: PluginManager) extends Plugin {

  // TODO: Better?
  // val x: Requirement[TwitchChatInput] = config.requireTwitchChatInput("inputReq", "Twitch Channel")

  val config = new Requirements

  // VERSION A (Objektorientiert. Vorteil: Typsicher. Nachteil: Für jeden Input/Output eigene Methode in API)
  private val twitchChat = config.requireTwitchChatInput("inputReq", "A Twitch channel")
  twitchChat.getSource.getLastMessages(1000)

  // VERSION B (Durch Reflection aufgelöst, Vorteil: Leicht erweiterbar, Nachteil: Nicht typsicher)
  //private val twitchChat2 = config.require(Class[TwitchChatInput], "inputReq2", "A twitch channel")
  //twitchChat2.getSource.asInstanceOf[TwitchChatInput].getLastMessages(1000)


  config.addInputRequirement("Twitch Channel", new SourceRequirement[TwitchChatInput]("input"))
  config.addParameterRequirement("Some name", new ParameterRequirement[String]("helloReq"))

  override def start(): Unit = {
    println("Started successfully!")
    println(s"Dummy message is:${manager.getDummyMessage}")

    val chatInput = config.getInputRequirement("Twitch Channel").getSource.asInstanceOf[TwitchChatInput]
    chatInput.registerMessageHandler(msg => println(msg))

    println(s"Param: ${config.getParameterRequirement("Some name").getParameter}")

    while (true) {
      Thread.sleep(10)
    }
  }

  override def getRequirements: Requirements = config
}