package com.ziggeo.androidsdk.demo.presentation.settings

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.analytics.FirebaseAnalytics
import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.demo.model.data.feature.SettingsModel
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.demo.model.system.flow.FlowRouter
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.presentation.global.BaseMainFlowPresenter
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class SettingsPresenter @Inject constructor(
    router: FlowRouter,
    systemMessageNotifier: SystemMessageNotifier,
    analytics: FirebaseAnalytics,
    val prefs: Prefs,
    val ziggeo: IZiggeo
) : BaseMainFlowPresenter<SettingsView>(router, systemMessageNotifier, analytics) {

    private val settingsModel = SettingsModel()

    fun onCustomVideoChanged(isCustomVideo: Boolean) {
        settingsModel.isCustomVideo = isCustomVideo
    }

    fun onCustomCameraChanged(isCustomCamera: Boolean) {
        settingsModel.isCustomCamera = isCustomCamera
    }

    fun getCustomVideoMode() = prefs.isCustomVideo

    fun getCustomCameraMode() = prefs.isCustomCamera

    // Recorder config
    fun onStartDelayChanged(time: Int) {
        settingsModel.startDelay = time
    }

    fun onBlurModeChanged(isBlurMode: Boolean) {
        settingsModel.isBlurMode = isBlurMode
    }

    fun onShouldShowFaceOutlineChanged(shouldShowFaceOutline: Boolean) {
        settingsModel.shouldShowFaceOutline = shouldShowFaceOutline
    }

    fun onMaxDurationChanged(time: Long) {
        settingsModel.maxDuration = time
    }

    fun onVideoQualityChanged(quality: Int) {
        settingsModel.videoQuality = quality
    }

    fun onIsLiveStreamingChanged(isLiveStreaming: Boolean) {
        settingsModel.isLiveStreaming = isLiveStreaming
    }

    fun onShouldAutoStartRecordingChanged(shouldAutoStartRecording: Boolean) {
        settingsModel.shouldAutoStartRecording = shouldAutoStartRecording
    }

    fun onIsImageOnlyModeChanged(isImageOnlyMode: Boolean) {
        settingsModel.isImageOnlyMode = isImageOnlyMode
    }

    fun onShouldSendImmediatelyChanged(shouldSendImmediately: Boolean) {
        settingsModel.shouldSendImmediately = shouldSendImmediately
    }

    fun onShouldDisableCameraSwitchChanged(shouldDisableCameraSwitch: Boolean) {
        settingsModel.shouldDisableCameraSwitch = shouldDisableCameraSwitch
    }

    fun onShouldEnableCoverShotChanged(shouldEnableCoverShot: Boolean) {
        settingsModel.shouldEnableCoverShot = shouldEnableCoverShot
    }

    fun onShouldConfirmStopRecordingChanged(shouldConfirmStopRecording: Boolean) {
        settingsModel.shouldConfirmStopRecording = shouldConfirmStopRecording
    }

    fun onVideoBitrateChanged(time: Int) {
        settingsModel.videoBitrate = time
    }

    fun onAudioBitrateChanged(time: Int) {
        settingsModel.audioBitrate = time
    }

    fun onAudioSampleRateChanged(time: Int) {
        settingsModel.audioSampleRate = time
    }

    fun onFacingChanged(value: Int) {
        settingsModel.facing = value
    }

    fun getStartDelay() = prefs.startDelay

    fun getBlurMode() = prefs.isBlurMode

    fun getShouldShowFaceOutline() = prefs.shouldShowFaceOutline

    fun getMaxDuration() = prefs.maxDuration

    fun getVideoQuality() = prefs.videoQuality

    fun getIsLiveStreaming() = prefs.isLiveStreaming

    fun getShouldAutoStartRecording() = prefs.shouldAutoStartRecording

    fun getIsImageOnlyMode() = prefs.isImageOnlyMode

    fun getShouldSendImmediately() = prefs.shouldSendImmediately

    fun getShouldDisableCameraSwitch() = prefs.shouldDisableCameraSwitch

    fun getShouldEnableCoverShot() = prefs.shouldEnableCoverShot

    fun getShouldConfirmStopRecording() = prefs.shouldConfirmStopRecording

    fun getVideoBitrate() = prefs.videoBitrate

    fun getAudioBitrate() = prefs.audioBitrate

    fun getAudioSampleRate() = prefs.audioSampleRate

    fun getFacing() = prefs.facing

    // Player config
    fun onShouldShowSubtitlesChanged(shouldShowSubtitles: Boolean) {
        settingsModel.shouldShowSubtitles = shouldShowSubtitles
    }

    fun onIsMutedChanged(isMuted: Boolean) {
        settingsModel.isMuted = isMuted
    }

    fun onIsCachingEnabledChanged(isCachingEnabled: Boolean) {
        settingsModel.isCachingEnabled = isCachingEnabled
    }

    fun onShouldPreloadChanged(shouldPreload: Boolean) {
        settingsModel.shouldPreload = shouldPreload
    }

    fun onAdsUriChanged(uri: String) {
        settingsModel.adsUri = uri
    }

    fun getshouldShowSubtitles() = prefs.shouldShowSubtitles

    fun getIsMuted() = prefs.isMuted

    fun getIsCachingEnabled() = prefs.isCachingEnabled

    fun getShouldPreload() = prefs.shouldPreload

    fun getAdsUri() = prefs.adsUri

    //File selector player
    fun onShouldAllowMultipleSelectionChanged(shouldAllowMultipleSelection: Boolean) {
        settingsModel.shouldAllowMultipleSelection = shouldAllowMultipleSelection
    }

    fun onMediaTypeChanged(value: Int) {
        settingsModel.mediaType = value
    }

    fun onMaxDurationSelectorChanged(time: Long) {
        settingsModel.maxDurationSelector = time
    }

    fun getShouldAllowMultipleSelection() = prefs.shouldAllowMultipleSelection

    fun getMediaType() = prefs.mediaType

    fun getMaxDurationSelector() = prefs.maxDurationSelector

    //Qr scanner config
    fun onShouldCloseAfterSuccessfulScanChanged(shouldCloseAfterSuccessfulScan: Boolean) {
        settingsModel.shouldCloseAfterSuccessfulScan = shouldCloseAfterSuccessfulScan
    }

    fun getshouldCloseAfterSuccessfulScan() = prefs.shouldCloseAfterSuccessfulScan

    //    Uploading config
    fun onShouldTurnOffUploaderChanged(shouldTurnOffUploader: Boolean) {
        settingsModel.shouldTurnOffUploader = shouldTurnOffUploader
    }

    fun onShouldUseWifiOnlyChanged(shouldUseWifiOnly: Boolean) {
        settingsModel.shouldUseWifiOnly = shouldUseWifiOnly
    }

    fun onShouldStartAsForegroundChanged(shouldStartAsForeground: Boolean) {
        settingsModel.shouldStartAsForeground = shouldStartAsForeground
    }

    fun onSyncIntervalChanged(time: Long) {
        settingsModel.syncInterval = time
    }

    fun getShouldTurnOffUploader() = prefs.shouldTurnOffUploader

    fun getShouldUseWifiOnly() = prefs.shouldUseWifiOnly

    fun getShouldStartAsForeground() = prefs.shouldStartAsForeground

    fun getSyncInterval() = prefs.syncInterval

    //    StopRecordingConfirmationDialogConfig
    fun onTitleTextChanged(text: String) {
        settingsModel.titleText = text
    }

    fun onMesTextChanged(text: String) {
        settingsModel.mesText = text
    }

    fun onPosBtnTextChanged(text: String) {
        settingsModel.posBtnText = text
    }

    fun onNegBtnTextChanged(text: String) {
        settingsModel.negBtnText = text
    }

    fun getTitleText() = prefs.titleText

    fun getMesText() = prefs.mesText

    fun getPosBtnText() = prefs.posBtnText

    fun getNegBtnText() = prefs.negBtnText

    fun onSaveClicked() {
        prefs.startDelay = settingsModel.startDelay

        prefs.isCustomVideo = settingsModel.isCustomVideo
        prefs.isCustomCamera = settingsModel.isCustomCamera
        prefs.shouldAllowMultipleSelection = settingsModel.shouldAllowMultipleSelection
        prefs.mediaType = settingsModel.mediaType
        prefs.maxDurationSelector = settingsModel.maxDurationSelector
        prefs.shouldCloseAfterSuccessfulScan = settingsModel.shouldCloseAfterSuccessfulScan
        prefs.shouldTurnOffUploader = settingsModel.shouldTurnOffUploader
        prefs.shouldUseWifiOnly = settingsModel.shouldUseWifiOnly
        prefs.shouldStartAsForeground = settingsModel.shouldStartAsForeground
        prefs.syncInterval = settingsModel.syncInterval

        viewState.showSavedNotification()
    }

    fun onSaveClickedPlayer() {
        prefs.shouldShowSubtitles = settingsModel.shouldShowSubtitles
        prefs.isMuted = settingsModel.isMuted
        prefs.isCachingEnabled = settingsModel.isCachingEnabled
        prefs.shouldPreload = settingsModel.shouldPreload
        prefs.adsUri = settingsModel.adsUri

        viewState.showSavedNotification()
    }

    fun onSaveClickedRecorder() {
        prefs.startDelay = settingsModel.startDelay
        prefs.isBlurMode = settingsModel.isBlurMode
        prefs.shouldShowFaceOutline = settingsModel.shouldShowFaceOutline
        prefs.maxDuration = settingsModel.maxDuration
        prefs.videoQuality = settingsModel.videoQuality
        prefs.isLiveStreaming = settingsModel.isLiveStreaming
        prefs.shouldAutoStartRecording = settingsModel.shouldAutoStartRecording
        prefs.isImageOnlyMode = settingsModel.isImageOnlyMode
        prefs.shouldSendImmediately = settingsModel.shouldSendImmediately
        prefs.shouldDisableCameraSwitch = settingsModel.shouldDisableCameraSwitch
        prefs.shouldEnableCoverShot = settingsModel.shouldEnableCoverShot
        prefs.shouldConfirmStopRecording = settingsModel.shouldConfirmStopRecording
        prefs.videoBitrate = settingsModel.videoBitrate
        prefs.audioBitrate = settingsModel.audioBitrate
        prefs.audioSampleRate = settingsModel.audioSampleRate
        prefs.facing = settingsModel.facing

        prefs.titleText = settingsModel.titleText
        prefs.mesText = settingsModel.mesText
        prefs.posBtnText = settingsModel.posBtnText
        prefs.negBtnText = settingsModel.negBtnText

        viewState.showSavedNotification()
    }


}