package com.ziggeo.androidsdk.demo.presentation.videoeditor

import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.google.firebase.analytics.FirebaseAnalytics
import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.demo.model.system.flow.FlowRouter
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.presentation.global.BaseMainFlowPresenter
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
    analytics: FirebaseAnalytics,
    router: FlowRouter
) : BaseMainFlowPresenter<VideoEditView>(router, systemMessageNotifier, analytics) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.requestReadPermission()
    }

    fun onSelectFileClicked() {
        viewState.showFileSelector()
    }

    fun onFileSelected(uri: Uri) {
    }
}