package com.zygnearchitecture.domain.log.base

public interface Logger {

    /**
     * Log an event
     * @param severity : severity level of the Log
     * @param logData : data for logging
     */
    public fun log(severity: Severity, logData: LogData)
}
