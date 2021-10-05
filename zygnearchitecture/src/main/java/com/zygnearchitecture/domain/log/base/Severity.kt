package com.zygnearchitecture.domain.log.base

/**
 * Enum class which the priority of the log output
 *
 * @property FATAL : Log internal error which prevents the system from working correctly.
 * @property ERROR : Log for internal or external errors which impacts the result of a process.
 * @property WARNING : Log for internal or external unexpected behaviours.
 * @property INFO : Log for status updates.
 * @property DEBUG : Log for development purposes only.
 */
public enum class Severity {
    DEBUG,
    INFO,
    WARNING,
    ERROR,
    FATAL
}
