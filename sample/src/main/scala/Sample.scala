import org.codeoverflow.chatoverflow.api.plugin.{Pluggable, Plugin, PluginManager}

class Sample extends Pluggable {
  /**
    * Returns the name of the plugin.
    *
    * @return the display name of the plugin
    */
  override def getName: String = "sample"

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
  override def getDescription: String = "Sample plugin for testing purposes."

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
    * If the plugin is not up-to-date, it might not be loaded due to possible reflection errors. Please
    * do ONLY use the Plugin class to define your own chat overflow plugin logic!
    * Create a new instance HERE every time!
    *
    * @param manager the manager implementation of the framework
    * @return the plugin implementation of the plugin project, ready to get started!
    */
  override def createNewPluginInstance(manager: PluginManager): Plugin = new SamplePlugin(manager)
}
