package com.zygnearchitecture.domain.log.factory

import com.zygnearchitecture.domain.log.base.Logger
import com.zygnearchitecture.domain.log.implementation.DebugLogger

object LoggerFactory {

    private var defaultLogger: Logger = DebugLogger()

    fun setDefaultLogger(logger: Logger) {
        defaultLogger = logger
    }

    fun getDefaultLogger(): Logger {
        return defaultLogger
    }
}