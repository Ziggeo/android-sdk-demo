package com.ziggeo.androidsdk.demo.model.data.storage

import android.content.Context
import com.ziggeo.androidsdk.utils.FileUtils
import com.ziggeo.androidsdk.widgets.cameraview.BaseCameraView
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
    private val KEY_FACE_OUTLINE = "KEY_FACE_OUTLINE"
    private val KEY_MAX_DURATION = "KEY_MAX_DURATION"
    private val KEY_VIDEO_QUALITY = "KEY_VIDEO_QUALITY"
    private val KEY_LIVE_STREAMING = "KEY_LIVE_STREAMING"
    private val KEY_AUTOSTART_RECORDING = "KEY_AUTOSTART_RECORDING"
    private val KEY_IMAGE_ONLY_MODE = "KEY_IMAGE_ONLY_MODE"
    private val KEY_SEND_IMMEDIATELY = "KEY_SEND_IMMEDIATELY"
    private val KEY_DISABLE_CAMERA_SWITCH = "KEY_DISABLE_CAMERA_SWITCH"
    private val KEY_ENABLE_COVER_SHOT = "KEY_ENABLE_COVER_SHOT"
    private val KEY_CONFIRM_STOP_RECORDING = "KEY_CONFIRM_STOP_RECORDING"
    private val KEY_VIDEO_BITRATE = "KEY_VIDEO_BITRATE"
    private val KEY_AUDIO_BITRATE = "KEY_AUDIO_BITRATE"
    private val KEY_AUDIO_SAMPLE_RATE = "KEY_AUDIO_SAMPLE_RATE"
    private val KEY_FACING = "KEY_FACING"
    private val KEY_MUTED = "KEY_MUTED"
    private val KEY_SHOW_SUBTITLES = "KEY_SHOW_SUBTITLES"
    private val KEY_CACHING_ENABLED = "KEY_CACHING_ENABLED"
    private val KEY_PRELOAD = "KEY_PRELOAD"
    private val KEY_ALLOW_MULTIPLE_SELECTION = "KEY_ALLOW_MULTIPLE_SELECTION"
    private val KEY_MEDIA_TYPE = "KEY_MEDIA_TYPE"
    private val KEY_MAX_DURATION_SELECTOR = "KEY_MAX_DURATION_SELECTOR"
    private var KEY_CLOSE_AFTER_SUCCESSFUL_SCAN = "KEY_CLOSE_AFTER_SUCCESSFUL_SCAN"
    private val KEY_TURN_OFF_UPLOADER = "KEY_TURN_OFF_UPLOADER"
    private val KEY_USE_WIFI_ONLY = "KEY_USE_WIFI_ONLY"
    private val KEY_START_AS_FOREGROUND = "KEY_START_AS_FOREGROUND"
    private val KEY_SYNC_INTERVAL = "KEY_SYNC_INTERVAL"
    private val KEY_ADS_URI = "KEY_ADS_URI"
    private val KEY_TITLE_TEXT = "KEY_TITLE_TEXT"
    private val KEY_MES_TEXT = "KEY_MES_TEXT"
    private val KEY_POS_BTN_TEXT = "KEY_POS_BTN_TEXT"
    private val KEY_NEG_BTN_TEXT = "KEY_NEG_BTN_TEXT"

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

    var shouldShowFaceOutline: Boolean
        get() = authPrefs.getBoolean(KEY_FACE_OUTLINE, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_FACE_OUTLINE, value).apply()
        }

    var maxDuration: Long
        get() = authPrefs.getLong(KEY_MAX_DURATION, 0L)
        set(value) {
            authPrefs.edit().putLong(KEY_MAX_DURATION, value).apply()
        }

    var videoQuality: Int
        get() = authPrefs.getInt(KEY_VIDEO_QUALITY, BaseCameraView.QUALITY_HIGH)
        set(value) {
            authPrefs.edit().putInt(KEY_VIDEO_QUALITY, value).apply()
        }

    var isLiveStreaming: Boolean
        get() = authPrefs.getBoolean(KEY_LIVE_STREAMING, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_LIVE_STREAMING, value).apply()
        }

    var shouldAutoStartRecording: Boolean
        get() = authPrefs.getBoolean(KEY_AUTOSTART_RECORDING, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_AUTOSTART_RECORDING, value).apply()
        }

    var isImageOnlyMode: Boolean
        get() = authPrefs.getBoolean(KEY_IMAGE_ONLY_MODE, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_IMAGE_ONLY_MODE, value).apply()
        }

    var shouldSendImmediately: Boolean
        get() = authPrefs.getBoolean(KEY_SEND_IMMEDIATELY, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_SEND_IMMEDIATELY, value).apply()
        }

    var shouldDisableCameraSwitch: Boolean
        get() = authPrefs.getBoolean(KEY_DISABLE_CAMERA_SWITCH, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_DISABLE_CAMERA_SWITCH, value).apply()
        }

    var shouldEnableCoverShot: Boolean
        get() = authPrefs.getBoolean(KEY_ENABLE_COVER_SHOT, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_ENABLE_COVER_SHOT, value).apply()
        }

    var shouldConfirmStopRecording: Boolean
        get() = authPrefs.getBoolean(KEY_CONFIRM_STOP_RECORDING, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_CONFIRM_STOP_RECORDING, value).apply()
        }

    var videoBitrate: Int
        get() = authPrefs.getInt(KEY_VIDEO_BITRATE, 0)
        set(value) {
            authPrefs.edit().putInt(KEY_VIDEO_BITRATE, value).apply()
        }
    var audioBitrate: Int
        get() = authPrefs.getInt(KEY_AUDIO_BITRATE, 0)
        set(value) {
            authPrefs.edit().putInt(KEY_AUDIO_BITRATE, value).apply()
        }

    var audioSampleRate: Int
        get() = authPrefs.getInt(KEY_AUDIO_SAMPLE_RATE, 0)
        set(value) {
            authPrefs.edit().putInt(KEY_AUDIO_SAMPLE_RATE, value).apply()
        }

    var facing: Int
        get() = authPrefs.getInt(KEY_FACING, BaseCameraView.FACING_BACK)
        set(value) {
            authPrefs.edit().putInt(KEY_FACING, value).apply()
        }

    var shouldShowSubtitles: Boolean
        get() = authPrefs.getBoolean(KEY_SHOW_SUBTITLES, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_SHOW_SUBTITLES, value).apply()
        }

    var isMuted: Boolean
        get() = authPrefs.getBoolean(KEY_MUTED, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_MUTED, value).apply()
        }

    var isCachingEnabled: Boolean
        get() = authPrefs.getBoolean(KEY_CACHING_ENABLED, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_CACHING_ENABLED, value).apply()
        }

    var shouldPreload: Boolean
        get() = authPrefs.getBoolean(KEY_PRELOAD, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_PRELOAD, value).apply()
        }

    var shouldAllowMultipleSelection: Boolean
        get() = authPrefs.getBoolean(KEY_ALLOW_MULTIPLE_SELECTION, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_ALLOW_MULTIPLE_SELECTION, value).apply()
        }

    var mediaType: Int
        get() = authPrefs.getInt(KEY_MEDIA_TYPE, FileUtils.VIDEO)
        set(value) {
            authPrefs.edit().putInt(KEY_MEDIA_TYPE, value).apply()
        }

    var maxDurationSelector: Long
        get() = authPrefs.getLong(KEY_MAX_DURATION_SELECTOR, 0L)
        set(value) {
            authPrefs.edit().putLong(KEY_MAX_DURATION_SELECTOR, value).apply()
        }

    var shouldCloseAfterSuccessfulScan: Boolean
        get() = authPrefs.getBoolean(KEY_CLOSE_AFTER_SUCCESSFUL_SCAN, true)
        set(value) {
            authPrefs.edit().putBoolean(KEY_CLOSE_AFTER_SUCCESSFUL_SCAN, value).apply()
        }

    var shouldTurnOffUploader: Boolean
        get() = authPrefs.getBoolean(KEY_TURN_OFF_UPLOADER, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_TURN_OFF_UPLOADER, value).apply()
        }

    var shouldUseWifiOnly: Boolean
        get() = authPrefs.getBoolean(KEY_USE_WIFI_ONLY, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_USE_WIFI_ONLY, value).apply()
        }

    var shouldStartAsForeground: Boolean
        get() = authPrefs.getBoolean(KEY_START_AS_FOREGROUND, false)
        set(value) {
            authPrefs.edit().putBoolean(KEY_START_AS_FOREGROUND, value).apply()
        }

    var syncInterval: Long
        get() = authPrefs.getLong(KEY_SYNC_INTERVAL, 0L)
        set(value) {
            authPrefs.edit().putLong(KEY_SYNC_INTERVAL, value).apply()
        }

    var adsUri: String
        get() = authPrefs.getString(KEY_ADS_URI, "").toString()
        set(value) {
            authPrefs.edit().putString(KEY_ADS_URI, value).apply()
        }

    var titleText: String
        get() = authPrefs.getString(KEY_TITLE_TEXT, "").toString()
        set(value) {
            authPrefs.edit().putString(KEY_TITLE_TEXT, value).apply()
        }

    var mesText: String
        get() = authPrefs.getString(KEY_MES_TEXT, "").toString()
        set(value) {
            authPrefs.edit().putString(KEY_MES_TEXT, value).apply()
        }

    var posBtnText: String
        get() = authPrefs.getString(KEY_POS_BTN_TEXT, "").toString()
        set(value) {
            authPrefs.edit().putString(KEY_POS_BTN_TEXT, value).apply()
        }

    var negBtnText: String
        get() = authPrefs.getString(KEY_NEG_BTN_TEXT, "").toString()
        set(value) {
            authPrefs.edit().putString(KEY_NEG_BTN_TEXT, value).apply()
        }
}