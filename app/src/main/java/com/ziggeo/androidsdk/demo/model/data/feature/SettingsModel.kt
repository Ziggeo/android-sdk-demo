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
    var isBlurMode: Boolean = false,
    var shouldShowFaceOutline: Boolean = false,
    var maxDuration: Long = 0L,
    var videoQuality: Int = 0,
    var isLiveStreaming: Boolean = false,
    var shouldAutoStartRecording: Boolean = false,
    var isImageOnlyMode: Boolean = false,
    var shouldSendImmediately: Boolean = false,
    var shouldDisableCameraSwitch: Boolean = false,
    var shouldEnableCoverShot: Boolean = false,
    var shouldConfirmStopRecording: Boolean = false,
    var videoBitrate: Int = 0,
    var audioBitrate: Int = 0,
    var audioSampleRate: Int = 0,
    var facing: Int = 0,

    var shouldShowSubtitles: Boolean = false,
    var isMuted: Boolean = false,
    var isCachingEnabled: Boolean = false,
    var shouldPreload: Boolean = false,
    var adsUri: String = "",

    var shouldAllowMultipleSelection: Boolean = false,
    var mediaType: Int = 0,
    var maxDurationSelector: Long = 0L,

    var shouldCloseAfterSuccessfulScan: Boolean = true,

    var shouldTurnOffUploader: Boolean = false,
    var shouldUseWifiOnly: Boolean = false,
    var shouldStartAsForeground: Boolean = false,
    var syncInterval: Long = 0L,

    var titleText: String = "",
    var mesText: String = "",
    var posBtnText: String = "",
    var negBtnText: String = "",

    ) : FeatureModel