package com.zygnearchitecture.domain.log.implementation

import android.util.Log
import com.zygnearchitecture.domain.log.base.LogData
import com.zygnearchitecture.domain.log.base.Logger
import com.zygnearchitecture.domain.log.base.Severity

class DebugLogger : Logger {

    override fun log(severity: Severity, logData: LogData) {

        if (severity.ordinal >= Severity.DEBUG.ordinal) {
            Log.d(logData.tag, logData.message)
        }
    }

}