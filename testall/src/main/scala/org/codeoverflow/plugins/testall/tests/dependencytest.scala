package org.codeoverflow.plugins.testall.tests

import org.apache.commons.math3.analysis.function.Sqrt
import org.codeoverflow.plugins.testall.{test, testallPlugin}

/**
 * A quick test to ensure that plugins are able to have dependencies by using apache commons math3 to take the square root of a number.
 * This dependency has been added in the build.sbt and should be downloaded using Coursier inside the framework.
 */
class dependencytest(val plugin: testallPlugin) extends test(plugin) {
  override def name: String = "Dependency test"

  override def setup(): Unit = {
    try {
      val num = 4
      val numSquared = num * num
      assert(num == new Sqrt().value(numSquared))
      log("Dependencies are working as expected")
    } catch {
      case _: Throwable => log("Dependencies aren't working successfully")
    }
  }

  override def loop(): Unit = ()

  override def shutdown(): Unit = ()
}
