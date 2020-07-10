package com.ziggeo.androidsdk.demo.model.data.feature

import androidx.annotation.StringRes

class LogModel(
    @StringRes val name: Int,
    val details: String?,
    val timestamp: Long = System.currentTimeMillis()
) : FeatureModel