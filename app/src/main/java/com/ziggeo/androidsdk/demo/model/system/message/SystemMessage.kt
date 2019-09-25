package com.ziggeo.androidsdk.demo.model.system.message


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
data class SystemMessage(
    val text: String,
    val type: SystemMessageType = SystemMessageType.ALERT
)