package org.codeoverflow.plugins.testall

import org.codeoverflow.chatoverflow.api.plugin.{PluginImpl, PluginManager}
import org.codeoverflow.plugins.testall.tests.{discordtest, filetest, rcontest, serialtest, tipeeestreamtest, twitchtest}


class testallPlugin(manager: PluginManager) extends PluginImpl(manager) {

  private val tests: Seq[test] = Seq(
    new discordtest(this,
      require.input.discordChat("discordIn", "Discord input", true),
      require.output.discordChat("discordOut", "Discord output", true),
      require.parameter.string("discordChannel", "The id of the channel to which the bot should connect", true)
    ),
    new filetest(this,
      require.input.file("fileIn", "File input", true),
      require.output.file("fileOut", "File output", true)
    ),
    new serialtest(this,
      require.input.serial("serialIn", "Serial Port input", true),
      require.output.serial("serialOut", "Serial Port output", true)
    ),
    new twitchtest(this,
      require.input.twitchChat("twitchIn", "Twitch chat input #skate702", true),
      require.output.twitchChat("twitchOut", "Twitch chat output #skate702", true)
    ),
    new tipeeestreamtest(this,
      require.input.tipeeeStream("tipeeestreamEvents", "TipeeeStream event input", true)
    ),
    new rcontest(this,
      require.input.rcon("rconIn",  "Remote control (RCON) socket of a gameserver used as input", true),
      require.output.rcon("rconOut",  "Remote control (RCON) socket of a gameserver used as output", true)
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
