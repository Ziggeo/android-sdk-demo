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
    private val KEY_CUSTOM_VIDEO = "KEY_CUSTOM_VIDEO"
    private val KEY_CUSTOM_CAMERA = "KEY_CUSTOM_CAMERA"
    private val KEY_BLUR_MODE = "KEY_BLUR_MODE"

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

    var isCustomVideo: Boolean
        get() = authPrefs.getBoolean(KEY_CUSTOM_VIDEO, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_CUSTOM_VIDEO, value).apply()
        }

    var isCustomCamera: Boolean
        get() = authPrefs.getBoolean(KEY_CUSTOM_CAMERA, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_CUSTOM_CAMERA, value).apply()
        }

    var isBlurMode: Boolean
        get() = authPrefs.getBoolean(KEY_BLUR_MODE, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_BLUR_MODE, value).apply()
        }
}