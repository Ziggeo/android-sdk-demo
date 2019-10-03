package com.ziggeo.androidsdk.demo.presentation.recordings

import com.arellomobile.mvp.InjectViewState
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class RecordingsPresenter @Inject constructor() : BasePresenter<RecordingsView>() {

    var fabActionsExpanded = false

    fun onFabCameraClicked() {
        viewState.startCameraRecorder()
    }

    fun onFabScreenClicked() {
        viewState.startScreenRecorder()
    }

    fun onFabAudioClicked() {
        viewState.startAudioRecorder()
    }

    fun onFabImageClicked() {
        viewState.startImageCapture()
    }

    fun onFabActionsClicked() {
        if (fabActionsExpanded) {
            viewState.collapseFabActions()
        } else {
            viewState.expandFabActions()
        }
        fabActionsExpanded = !fabActionsExpanded
    }

}