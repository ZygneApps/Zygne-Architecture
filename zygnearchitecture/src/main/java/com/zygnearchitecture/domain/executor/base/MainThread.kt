package com.zygnearchitecture.domain.executor.base

/**
 * Interface for the main thread.
 *
 * This interface should be implemented such that the method
 * post(Runnable) posts a new runnable to be run on the main thread.
 */
interface MainThread {
    fun post(runnable: Runnable)
}