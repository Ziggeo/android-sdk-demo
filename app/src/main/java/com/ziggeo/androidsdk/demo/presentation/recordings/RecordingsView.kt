package com.ziggeo.androidsdk.demo.presentation.recordings

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.ziggeo.androidsdk.net.models.videos.VideoModel


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface RecordingsView : MvpView {

    fun startCameraRecorder()
    fun startScreenRecorder()
    fun startAudioRecorder()
    fun startImageCapture()
    fun startFileSelector()

    fun showRecordingsList(list: List<VideoModel>)
    fun showNoRecordingsMessage()

    fun showLoading()
    fun hideLoading()

    fun hideActionFabs()
    fun showActionFabs()
    fun hideSelectorFab()
    fun showSelectorFab()

    fun startShowAnimationMainFab()
    fun startHideAnimationMainFab()
}