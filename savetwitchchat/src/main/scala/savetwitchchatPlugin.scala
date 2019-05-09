import org.codeoverflow.chatoverflow.api.io.input.chat.TwitchChatMessage
import org.codeoverflow.chatoverflow.api.plugin.{PluginImpl, PluginManager}

class savetwitchchatPlugin(manager: PluginManager) extends PluginImpl(manager) {

  private val twitchChatInputReq = require.input.twitchChat("reqTwitch", "A twitch channel", false)
  private val fileNameReq = require.parameter.string("reqFileName", "File name", true)
  private val twitchChatFileOutputReq = require.output.twitchChatFileOutput("reqFile", "A file name", false)

  // TODO set zero ?
  loopInterval = 10000

  private val twitchChatMessages: java.util.List[TwitchChatMessage] = new java.util.ArrayList[TwitchChatMessage]()

  override def setup(): Unit = {
    twitchChatInputReq.get.registerMessageHandler(msg => {
      println(msg) // TODO remove
      twitchChatMessages.add(msg)
    })
  }

  override def loop(): Unit = {
  }

  override def shutdown(): Unit = {
    var fileName: java.lang.String = "default"
    if (fileNameReq.isSet) fileName = fileNameReq.get().get()
    twitchChatFileOutputReq.get.save(fileName, twitchChatMessages)
  }
}