package com.zygnearchitecture.presentation.presenters.base

import com.zygnearchitecture.domain.executor.base.Executor
import com.zygnearchitecture.domain.executor.base.MainThread

/**
 * Base class all presenters
 *
 * All presenters should extend this class, and pass the member
 * parameters to the interactors which should run.
 *
 * @property executor : implementation of Executor
 * @property mainThread : implementation of MainThread
 */
abstract class AbstractPresenter(protected val executor: Executor,
                                 protected val mainThread: MainThread) {

}