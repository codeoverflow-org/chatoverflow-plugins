package org.codeoverflow.plugins.testall.tests

import org.codeoverflow.chatoverflow.api.io.input.event.TipeeestreamEventInput
import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirement
import org.codeoverflow.plugins.testall.{test, testallPlugin}

class tipeeestreamtest(val plugin: testallPlugin,
                       val event: Requirement[TipeeestreamEventInput]) extends test(plugin, event) {

  override def name: String = "TipeeeStream test"

  override def setup(): Unit = {
    event.get.registerDonationEventHandler(e => log(s"${e.getInfo.getDonor.getDisplayName} donated ${e.getInfo.getFormattedAmount}"))
    event.get.registerFollowEventHandler(e => log(s"${e.getInfo.getFollower.getDisplayName} is now following you"))
    event.get.registerSubscriptionEventHandler(e => log(s"${e.getInfo.getSubscriber.getDisplayName} just subscribed. Streak: ${e.getInfo.getResub}"))
  }

  override def loop(): Unit = {}

  override def shutdown(): Unit = {}
}
