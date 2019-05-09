import org.codeoverflow.chatoverflow.api.plugin.{Pluggable, Plugin, PluginManager}

class savetwitchchat extends Pluggable {

  override def getName: String = "savetwitchchat"

  override def getAuthor: String = "andre"

  override def getDescription: String = "A plugin to save live chat from twitch stream"

  override def getMajorAPIVersion: Int = 1

  override def getMinorAPIVersion: Int = 0

  override def createNewPluginInstance(manager: PluginManager): Plugin = new savetwitchchatPlugin(manager)
}