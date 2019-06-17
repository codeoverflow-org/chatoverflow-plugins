import org.codeoverflow.chatoverflow.api.plugin.{Pluggable, Plugin, PluginManager}
import org.codeoverflow.plugins.testall.testallPlugin

class testall extends Pluggable {

  override def getName: String = "testall"

  override def getAuthor: String = "joblo2213"

  override def getDescription: String = "A plugin to simplify testing of all services"

  override def getMajorAPIVersion: Int = 1

  override def getMinorAPIVersion: Int = 0

  override def createNewPluginInstance(manager: PluginManager): Plugin = new testallPlugin(manager)
}
