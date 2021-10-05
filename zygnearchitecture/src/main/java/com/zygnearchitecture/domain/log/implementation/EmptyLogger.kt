package com.zygnearchitecture.domain.log.implementation

import com.zygnearchitecture.domain.log.base.LogData
import com.zygnearchitecture.domain.log.base.Logger
import com.zygnearchitecture.domain.log.base.Severity

class EmptyLogger : Logger {

    override fun log(severity: Severity, logData: LogData) {}
}