import org.codeoverflow.chatoverflow.api.plugin.{Pluggable, Plugin, PluginManager}

class anothertestMainClass extends Pluggable {

  override def getName: String = "Another Test"

  override def getAuthor: String = "sebinside"

  override def getDescription: String = "Just another test."

  override def getMajorAPIVersion: Int = 1

  override def getMinorAPIVersion: Int = 0

  override def getPlugin(manager: PluginManager): Plugin = new Plugin {

    override def start(): Unit = {
      println("Started another test!")
    }

    override def getDependenciesOrWhatEver: Array[String] = Array()
  }
}
