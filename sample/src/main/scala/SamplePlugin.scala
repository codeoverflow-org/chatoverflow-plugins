import org.codeoverflow.chatoverflow.api.plugin.{PluginImpl, PluginManager}

class SamplePlugin(manager: PluginManager) extends PluginImpl(manager) {

  // Sample input test requirement
  private val sampleReq = require.input.sample("sampleReq")

  /**
    * The setup method is executed one, when the plugin is started. Do NOT define your requirements in here!
    */
  override def setup(): Unit = {
    log("Initialized sample plugin!")
  }

  /**
    * The loop method is executed in loop with a specified interval until the shutdown method is called.
    * The loop method is NOT executed if a negative loop interval is set.
    */
  override def loop(): Unit = {
    log("Loop!")
  }

  /**
    * The shutdown method should contain logic to close everything.
    */
  override def shutdown(): Unit = {
    log("Shutting down sample plugin!")
  }
}
