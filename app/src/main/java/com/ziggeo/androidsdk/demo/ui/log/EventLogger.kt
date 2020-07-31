package com.ziggeo.androidsdk.demo.ui.log

import com.ziggeo.androidsdk.log.LogModel

/**
 * Created by Alexander Bedulin on 06-Jul-20.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class EventLogger(private val buffer: MutableList<LogModel> = ArrayList()) {

    fun logsDump(): List<LogModel> = buffer

    fun addEvent(reasonText: String, details: String? = null) {
        buffer.add(LogModel(reasonText, details))
    }
}