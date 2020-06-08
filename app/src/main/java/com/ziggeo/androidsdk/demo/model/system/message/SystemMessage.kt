package com.ziggeo.androidsdk.demo.model.system.message

import androidx.annotation.StringRes


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
data class SystemMessage(
    @StringRes
    val textRes: Int,
    val type: SystemMessageType = SystemMessageType.ALERT
)