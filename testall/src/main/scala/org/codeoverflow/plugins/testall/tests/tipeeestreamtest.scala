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
    event.get.registerSubscriptionEventHandler(e =>
      log(s"${e.getInfo.getSubscriber.getDisplayName} just ${
        if (e.getInfo.isGifted) s"got gifted a ${e.getInfo.getTier.toString} subscription by ${e.getInfo.getDonor.getDisplayName}."
        else s"subscribed with ${e.getInfo.getTier.toString}."
      } Streak: ${e.getInfo.getResub}")
    )
    event.get.registerCheerEventHandler(e => log(s"${e.getInfo.getCheerer.getDisplayName} just cheered ${e.getInfo.getAmount} bits: ${e.getInfo.getMessage}."))
  }

  override def loop(): Unit = {}

  override def shutdown(): Unit = {}
}
