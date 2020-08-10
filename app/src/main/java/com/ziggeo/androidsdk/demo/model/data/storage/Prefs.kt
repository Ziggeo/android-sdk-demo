package com.ziggeo.androidsdk.demo.model.data.storage

import android.content.Context
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class Prefs @Inject constructor(
    private val context: Context
) {

    private fun getSharedPreferences(prefsName: String) =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    private val AUTH_DATA = "auth_data"

    private val KEY_APP_TOKEN = "KEY_APP_TOKEN"

    // settings
    private val KEY_START_DELAY = "KEY_START_DELAY"

    private val authPrefs by lazy { getSharedPreferences(AUTH_DATA) }

    var appToken: String?
        get() = authPrefs.getString(KEY_APP_TOKEN, null)
        set(value) {
            authPrefs.edit().putString(KEY_APP_TOKEN, value).apply()
        }

    var startDelay: Int
        get() = authPrefs.getInt(KEY_START_DELAY, 0)
        set(value) {
            authPrefs.edit().putInt(KEY_START_DELAY, value).apply()
        }
}