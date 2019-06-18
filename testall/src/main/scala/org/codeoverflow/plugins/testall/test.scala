package org.codeoverflow.plugins.testall

import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirement

/**
  * Used for testing a connector.
  *
  * Pass all requirements required for this test and implement your tests in the `setup()`, `loop()` and `shutdown()` functions.
  * Also make sure to add the test in the `testallPlugin` class.
  *
  * @param plugin the testallPlugins instance (required for logging)
  * @param requirements the requirements that are used by this test
  */
abstract class test(private val plugin: testallPlugin, val requirements : Requirement[_]*) {

  /**
    * Used on setup to determine if the testallPlugin should run this test.
    *
    * By default it only checks if the supplied requirements are set.
    *
    * Override to change this behaviour
    *
    * @return true if the test should be run
    */
  def runTest: Boolean = requirements.forall(_.isSet)

  /**
    * The name of the test for displaying in log
    *
    * @return the name
    */
  def name: String

  /**
    * Perform the tests that should be executed on startup
    */
  def setup(): Unit

  /**
    * Perform the tests that should be executed in a loop
    */
  def loop(): Unit

  /**
    * Perform the tests that should be executed at shutdown and close everything.
    */
  def shutdown(): Unit

  /**
    * Prints a log message on the console and saves the message for later inspection.
    *
    * @param message the message to show
    */
  protected final def log(message: String): Unit = plugin.getManager.log(s"[$name] $message")

}
