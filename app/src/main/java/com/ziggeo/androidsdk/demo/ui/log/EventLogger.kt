package com.ziggeo.androidsdk.demo.ui.log

import androidx.annotation.StringRes
import com.ziggeo.androidsdk.demo.model.data.feature.LogModel

/**
 * Created by Alexander Bedulin on 06-Jul-20.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class EventLogger(private val buffer: MutableList<LogModel> = ArrayList()) {

    fun logsDump(): List<LogModel> = buffer

    fun addEvent(@StringRes reasonText: Int, details: String? = null) {
        buffer.add(LogModel(reasonText, details))
    }
}