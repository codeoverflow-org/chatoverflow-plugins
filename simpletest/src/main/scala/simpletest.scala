import org.codeoverflow.chatoverflow.api.plugin.{Pluggable, Plugin, PluginManager}

class simpletest extends Pluggable {
  /**
    * Returns the name of the plugin.
    *
    * @return the display name of the plugin
    */
  override def getName: String = "simpletest"

  /**
    * Returns the author name of the plugin.
    *
    * @return the real name or a alias of the author
    */
  override def getAuthor: String = "sebinside"

  /**
    * Returns a description of the plugin.
    *
    * @return a simple description of the service
    */
  override def getDescription: String = "A first, simple plugin to test the basic environment."

  /**
    * Returns the newest major version of the api, where the plugin was successfully tested!
    *
    * @return a version number
    */
  override def getMajorAPIVersion: Int = 1

  /**
    * Returns the newest minor version of the api, where the plugin was successfully tested!
    *
    * @return a version number
    */
  override def getMinorAPIVersion: Int = 0

  /**
    * Returns the real chat overflow plugin. Should only be used after testing the api version number!
    *
    * @return the plugin implementation of the plugin project
    */
  override def getPlugin: Plugin = new simpletestPlugin

  /**
    * Sets the plugin manager for communication. Should only be used after testing the api version number!
    *
    * @param manager the manager implementation of the framework
    */
  override def setPluginManager(manager: PluginManager): Unit = simpletest.manager = Some(manager)
}

object simpletest {

  var manager: Option[PluginManager] = None

}