package org.codeoverflow.plugins.testall.tests

import org.codeoverflow.chatoverflow.api.io.input.event.StreamElementsEventInput
import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirement
import org.codeoverflow.plugins.testall.{test, testallPlugin}

class streamelementstest(val plugin: testallPlugin,
                         val event: Requirement[StreamElementsEventInput]) extends test(plugin, event) {

  override def name: String = "StreamElements test"

  override def setup(): Unit = {
    event.get.registerDonationEventHandler(e => log(s"${e.getInfo.getDonor.getDisplayName} donated ${e.getInfo.getFormattedAmount}"))
    event.get.registerFollowEventHandler(e => log(s"${e.getInfo.getFollower.getDisplayName} is now following you"))
    event.get.registerSubscriptionEventHandler(e => {
      if (e.getInfo.isGifted)
        log(s"${e.getInfo.getSubscriber.getDisplayName} just got gifted a subscription. Streak: ${e.getInfo.getResub}")
      else
        log(s"${e.getInfo.getSubscriber.getDisplayName} just subscribed. Streak: ${e.getInfo.getResub}")
    })
  }

  override def loop(): Unit = ()

  override def shutdown(): Unit = ()
}
