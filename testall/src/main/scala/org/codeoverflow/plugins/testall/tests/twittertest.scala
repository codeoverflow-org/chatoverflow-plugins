package org.codeoverflow.plugins.testall.tests

import javax.imageio.ImageIO
import org.codeoverflow.chatoverflow.api.io.output.twitter.TwitterTweetOutput
import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirement
import org.codeoverflow.plugins.testall.{test, testallPlugin}

class twittertest(val plugin: testallPlugin,
                  val out: Requirement[TwitterTweetOutput]) extends test(plugin, out) {

  override def name: String = "Twitter Tweet Output test"

  val image = ImageIO.read(getClass.getResourceAsStream("/logo.png"))

  override def setup(): Unit = {
    out.get.sendTweet("Hi! Chatoverflow speaking here!")
    out.get.sendImageTweet("Hi! Chatoverflow sending image here!", image)
  }

  override def loop(): Unit = {
  }

  override def shutdown(): Unit = log("Stopped")
}