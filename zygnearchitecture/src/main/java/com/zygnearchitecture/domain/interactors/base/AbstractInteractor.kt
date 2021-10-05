/**
 * Copyright (C) 2017 Bardur Thomsen Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    // flag indicating if this interactor is active, is doing work.
    var isActive = false
        private set

    // flag to indicate if the this interactor has finished.
    var isFinshed = false
        private set

    // this method will execute the main logic for the interactor.
    abstract fun run()

    override fun execute() {
        logger.log(Severity.INFO, LogData(LogConfig.SUPER_TAG, "${this.javaClass.simpleName} :: Executing"))
        isActive = true
        executor.execute(this)
    }

    fun onFinished() {
        isActive = false
        isFinshed = true
        logger.log(Severity.INFO, LogData(LogConfig.SUPER_TAG, "${this.javaClass.simpleName} :: Finished"))
    }
}