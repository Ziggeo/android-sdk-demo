package com.ziggeo.androidsdk.demo.model.interactor

import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import javax.inject.Inject

/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class LaunchInteractor @Inject constructor(
    private val prefs: Prefs
) {
    val hasAccount: Boolean
        get() = prefs.appToken != null
}