import org.codeoverflow.chatoverflow.api.plugin.{Plugin, PluginManager}

class simpletestPlugin extends Plugin {

  private var manager: PluginManager = _

  override def start(): Unit = {
    println("Started successfully!")
    println(s"Dummy message is: '${
      if (simpletest.manager.isDefined)
        simpletest.manager.get.getDummyMessage
      else
        "Not defined!"
    }'")
  }

}
