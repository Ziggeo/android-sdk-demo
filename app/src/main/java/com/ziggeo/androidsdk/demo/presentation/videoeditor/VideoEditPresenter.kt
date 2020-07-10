package com.ziggeo.androidsdk.demo.presentation.videoeditor

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.analytics.FirebaseAnalytics
import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import com.ziggeo.androidsdk.videoeditor.VideoEditorCallback
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 17-Dec-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class VideoEditPresenter @Inject constructor(
    val ziggeo: IZiggeo,
    systemMessageNotifier: SystemMessageNotifier,
    analytics: FirebaseAnalytics
) : BasePresenter<VideoEditView>(systemMessageNotifier, analytics) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.requestReadPermission()
    }

    fun onSelectFileClicked() {
        viewState.showFileSelector()
    }

    fun onFileSelected(path: String) {
        ziggeo.videoEditorCallback = object : VideoEditorCallback() {
            override fun onVideoCut(path: String) {
                super.onVideoCut(path)
                viewState.showVideoSavedToNotification(path)
            }

            override fun error(throwable: Throwable) {
                super.error(throwable)
                Timber.e(throwable)
            }
        }
        ziggeo.startVideoEditor(path)
    }
}