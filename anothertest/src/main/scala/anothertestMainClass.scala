import org.codeoverflow.chatoverflow.api.plugin.{Pluggable, Plugin, PluginImpl, PluginManager}

class anothertestMainClass extends Pluggable {

  override def getName: String = "Another Test"

  override def getAuthor: String = "sebinside"

  override def getDescription: String = "Just another test."

  override def getMajorAPIVersion: Int = 1

  override def getMinorAPIVersion: Int = 0

  override def createNewPluginInstance(manager: PluginManager): Plugin = new PluginImpl(manager) {
    override def setup(): Unit = println("Setup!")

    override def loop(): Unit = println("Loop!")

    override def shutdown(): Unit = println("Shutdown!")
  }
}
