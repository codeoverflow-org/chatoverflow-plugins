import org.codeoverflow.chatoverflow.api.plugin.{Pluggable, Plugin, PluginManager}

class simpletest extends Pluggable {

  override def getName: String = "simpletest"

  override def getAuthor: String = "sebinside"

  override def getDescription: String = "A first, simple plugin to test the basic environment."

  override def getMajorAPIVersion: Int = 1

  override def getMinorAPIVersion: Int = 0

  override def createNewPluginInstance(manager: PluginManager): Plugin = new simpletestPlugin(manager)
}