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

    fun expandFabActions()
    fun collapseFabActions()

    fun startCameraRecorder()
    fun startScreenRecorder()
    fun startAudioRecorder()
    fun startImageCapture()

    fun showRecordingsList(list: List<VideoModel>)
    fun showNoRecordingsMessage()

    fun showError()

    fun showLoading()
    fun hideLoading()
}