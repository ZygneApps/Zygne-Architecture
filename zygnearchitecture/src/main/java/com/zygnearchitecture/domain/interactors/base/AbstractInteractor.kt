package com.zygnearchitecture.domain.interactors.base

import com.zygnearchitecture.domain.executor.base.Executor
import com.zygnearchitecture.domain.executor.base.MainThread
import com.zygnearchitecture.domain.log.base.LogConfig
import com.zygnearchitecture.domain.log.base.LogData
import com.zygnearchitecture.domain.log.base.Logger
import com.zygnearchitecture.domain.log.base.Severity
import com.zygnearchitecture.domain.log.factory.LoggerFactory

/**
 * Base class for all interactors.
 *
 * All interactors should extend this class, and implement their main logic
 * in the method run().
 *
 * @property executor :  executor for executing the main logic on a background thread.
 * @property mainThread :  main thread for posting results from worker thread to the main thread.
 */
abstract class AbstractInteractor(
        private val executor: Executor,
        protected val mainThread: MainThread,
        protected val logger: Logger = LoggerFactory.getDefaultLogger()) : Interactor {

    /**
     * flag indicating if this interactor is active, is doing work.
     */
    var isActive = false
        private set

    /**
     * flag to indicate if the this interactor has finished.
     */
    var isFinshed = false
        private set

    /**
     * Method to execute the main logic of the interactor in a background thread
     */
    abstract fun run()

    override fun execute() {
        logger.log(Severity.INFO, LogData(LogConfig.SUPER_TAG, "${this.javaClass.simpleName} :: Prepare to execute"))
        isActive = true
        executor.execute(this)
    }

    fun onFinished() {
        isActive = false
        isFinshed = true
        logger.log(Severity.INFO, LogData(LogConfig.SUPER_TAG, "${this.javaClass.simpleName} :: Finished"))
    }
}