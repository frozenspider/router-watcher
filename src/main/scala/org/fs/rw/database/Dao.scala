package org.fs.rw.database

import org.fs.rw.domain.Message

trait Dao {
  /**
   * Do the initial preparations, called once upon app startup.
   * Throwing exception from here will terminate the app.
   */
  def setup(): Unit

  /** Persist the given message */
  def saveMessage(message: Message): Unit

  /** Thin out records done earlier than `cutoffTimeMs` ago, leaving only one every `intendedGapMs` */
  def thinOut(cutoffTimeMs: Long, intendedGapMs: Long): Unit

  /** Terminate all open connections and stuff, safe to call multiple times */
  def tearDown(): Unit
}
