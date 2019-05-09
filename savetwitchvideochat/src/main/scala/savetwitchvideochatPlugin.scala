import org.codeoverflow.chatoverflow.api.io.input.chat.TwitchChatMessage
import org.codeoverflow.chatoverflow.api.plugin.{PluginImpl, PluginManager}

class savetwitchvideochatPlugin(manager: PluginManager) extends PluginImpl(manager) {

  private val twitchAPIReq = require.input.twitchStats("reqTwitchAPI", "Twitch API", false)
  private val videoIDReq = require.parameter.string("reqVideoID", "VideoID", false)
  private val fileNameReq = require.parameter.string("reqFileName", "File name", true)
  private val twitchChatFileOutputReq = require.output.twitchChatFileOutput("reqFile", "A file name", false)

  // TODO set zero ?
  loopInterval = 10000

  override def setup(): Unit = {
    var fileName: java.lang.String = "default"
    if (fileNameReq.isSet) fileName = fileNameReq.get().get()
    twitchChatFileOutputReq.get.save(fileName, twitchAPIReq.get().getVideoComments(videoIDReq.get().get()))
  }

  override def loop(): Unit = {
  }

  override def shutdown(): Unit = {
  }
}