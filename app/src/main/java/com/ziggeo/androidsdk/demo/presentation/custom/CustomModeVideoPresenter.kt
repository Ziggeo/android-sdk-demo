package com.ziggeo.androidsdk.demo.presentation.custom

import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.google.firebase.analytics.FirebaseAnalytics
import com.ziggeo.androidsdk.demo.Screens
import com.ziggeo.androidsdk.demo.model.data.storage.KVStorage
import com.ziggeo.androidsdk.demo.model.data.storage.VIDEO_FILE_PATH
import com.ziggeo.androidsdk.demo.model.data.storage.VIDEO_TOKEN
import com.ziggeo.androidsdk.demo.model.system.flow.FlowRouter
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import com.ziggeo.androidsdk.demo.ui.custom.CustomModeVideoView
import javax.inject.Inject

@InjectViewState
class CustomModeVideoPresenter @Inject constructor(
    var router: FlowRouter,
    kvStorage: KVStorage,
    smn: SystemMessageNotifier,
    analytics: FirebaseAnalytics
) : BasePresenter<CustomModeVideoView>(smn, analytics) {

    init {
        if (kvStorage.get(VIDEO_TOKEN) != null) {
            val videoToken = kvStorage.get(VIDEO_TOKEN) as String
            viewState.startVideo(videoToken)
            kvStorage.clear()
        }

        if (kvStorage.get(VIDEO_FILE_PATH) != null) {
            val videoPath = kvStorage.get(VIDEO_FILE_PATH) as String
            viewState.startVideoFile(Uri.parse(videoPath))
            kvStorage.clear()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        router.newRootFlow(Screens.MainFlow)
    }
}