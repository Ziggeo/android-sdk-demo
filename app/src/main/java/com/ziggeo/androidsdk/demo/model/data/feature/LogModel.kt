package com.ziggeo.androidsdk.demo.model.data.feature

data class LogModel(
    val name: String,
    val details: String?,
    val timestamp: Long = System.currentTimeMillis()
) : FeatureModel