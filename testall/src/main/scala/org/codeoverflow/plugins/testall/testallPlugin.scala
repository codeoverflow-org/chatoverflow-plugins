package org.codeoverflow.plugins.testall

import org.codeoverflow.chatoverflow.api.plugin.{PluginImpl, PluginManager}
import org.codeoverflow.plugins.testall.tests.{discordtest, filetest}


class testallPlugin(manager: PluginManager) extends PluginImpl(manager) {

  private val tests: Seq[test] = Seq(
    new discordtest(this,
      require.input.discordChat("reqDiscordIn", "Discord input", true),
      require.output.discordChat("reqDiscordOut", "Discord output", true),
      require.parameter.string("reqDiscordChannel", "The id of the channel to which the bot should connect", true)
    ),
    new filetest(this,
      require.input.file("fileIn", "File input", true),
      require.output.file("fileOut", "File output", true)
    )
    //Add more tests here!
  )

  private var running = Seq[test]()

  override def setup(): Unit = {
    running = tests.filter(test => {
      log(s"${test.name}: ${if (test.runTest) "testing" else "skipping (not all requirements are set)"}")
      test.runTest
    })
    running.foreach(_.setup())
  }

  override def loop(): Unit = running.foreach(_.loop())

  override def shutdown(): Unit = running.foreach(_.shutdown())
}
