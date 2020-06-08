package com.ziggeo.androidsdk.demo.model.data.feature

import androidx.annotation.DrawableRes

/**
 * Created by Alexander Bedulin on 24-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
data class SdkModel(
    @DrawableRes val drawableRes: Int,
    val url: String,
    val name: String? = null
) : FeatureModel