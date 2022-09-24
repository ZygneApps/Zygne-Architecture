package com.zygnearchitecture.domain.interactors.base

/**
 * Interface for interactor.
 *
 * Interactors should implement this interface, in order for the executor
 * to run their main code on a background thread
 */
interface Interactor {
    fun execute()
}