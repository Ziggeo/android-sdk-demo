package com.ziggeo.androidsdk.demo.model.data.feature

/**
 * Created by Alexander Bedulin on 24-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
data class SettingsModel(
    var startDelay: Int = 0,
    var isCustomVideo: Boolean = false,
    var isCustomCamera: Boolean = false,
    var isBlurMode: Boolean = false
) : FeatureModel