package com.zygnearchitecture.domain.log.base

/**
 * Data class which contain necessary data for logging.
 *
 * @property tag : tag for filtering the log.
 * @property message : the message for the log.
 */
public data class LogData(
        val tag: String,
        val message: String
)