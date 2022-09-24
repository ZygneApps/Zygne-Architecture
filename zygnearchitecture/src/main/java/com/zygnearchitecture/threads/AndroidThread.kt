package com.zygnearchitecture.threads

import android.os.Handler
import android.os.Looper
import com.zygnearchitecture.domain.executor.base.MainThread

/**
 * Implementation of the MainThread interface.
 *
 * This class is used to send tasks from a background thread to the main thread
 */
object AndroidThread : MainThread {
    private val handler: Handler = Handler(Looper.getMainLooper())

    // post the runnable to the main thread
    override fun post(runnable: Runnable) {
        handler.post(runnable)
    }

}