import org.codeoverflow.chatoverflow.api.io.dto.chat.discord.DiscordChatMessage
import org.codeoverflow.chatoverflow.api.plugin.{PluginImpl, PluginManager}
import scala.collection.JavaConverters._

class discordTestPlugin(manager: PluginManager) extends PluginImpl(manager) {

  private val discordInputReq = require.input.discordChat("reqDiscordIn", "Discord input", false)
  private val discordOutputReq = require.output.discordChat("reqDiscordOut", "Discord output", false)

  override def setup(): Unit = {
    println(s"Input connected to channel ${discordInputReq.get().getChannelId}")
    println(s"Output connected to channel ${discordOutputReq.get().getChannelId}")
    discordInputReq.get.registerMessageHandler(onMessage)
    discordInputReq.get.registerMessageEditHandler(onMessageEdit)
    discordInputReq.get.registerMessageDeleteHandler(onMessageDelete)
    discordOutputReq.get.sendChatMessage("Hey I'm working! \uD83C\uDF89")
    println("Startet succesfully")
  }

  override def loop(): Unit = {}


  override def shutdown(): Unit = println("Plugin stopped")

  def onMessage(message: DiscordChatMessage): Unit = {
    if (message.getMessage == "/messages") {
      var s = "**Recent messages:**\n"
      s += discordInputReq.get.getLastMessages(1000*60).asScala.filter(_.getAuthor.getId != discordTestPlugin.BOT_ID).mkString("\n")
      s += "\n\n**Recent private messages:**\n"
      s += discordInputReq.get().getLastPrivateMessages(1000*60).asScala.mkString("\n")
      discordOutputReq.get. sendChatMessage(s)
    }
  }

  def onMessageEdit(oldMessage: DiscordChatMessage, newMessage: DiscordChatMessage): Unit = {
    println(s"Message #${oldMessage.getId} was edited from '$oldMessage' to '$newMessage'")
  }

  def onMessageDelete(message: DiscordChatMessage): Unit = {
    println(s"Message #${message.getId} was deleted  (content: $message)")
  }
}
object discordTestPlugin {
  val BOT_ID = "572870096356376576"
}
