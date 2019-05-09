import org.codeoverflow.chatoverflow.api.plugin.{Pluggable, Plugin, PluginManager}

class savetwitchvideochat extends Pluggable {

  override def getName: String = "savetwitchvideochat"

  override def getAuthor: String = "andre"

  override def getDescription: String = "A plugin to save the whole live chat of a uploaded twitch video"

  override def getMajorAPIVersion: Int = 1

  override def getMinorAPIVersion: Int = 0

  override def createNewPluginInstance(manager: PluginManager): Plugin = new savetwitchvideochatPlugin(manager)
}