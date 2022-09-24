package com.zygnearchitecture.domain.executor.base

import com.zygnearchitecture.domain.interactors.base.AbstractInteractor

/**
 * Interface for the main Executor
 *
 * This interface should be implemented such that the method
 * execute() starts the interactor in a background thread.
 */
interface Executor {
    fun execute(interactor: AbstractInteractor)
}