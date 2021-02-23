package com.ziggeo.androidsdk.demo.presentation.custom

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.analytics.FirebaseAnalytics
import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.demo.Screens
import com.ziggeo.androidsdk.demo.model.data.storage.KVStorage
import com.ziggeo.androidsdk.demo.model.data.storage.VIDEO_FILE_PATH
import com.ziggeo.androidsdk.demo.model.system.flow.FlowRouter
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import com.ziggeo.androidsdk.demo.ui.custom.CustomModeCameraView
import javax.inject.Inject

@InjectViewState
class CustomModeCameraPresenter @Inject constructor(
    val ziggeo: IZiggeo,
    var router: FlowRouter,
    var kvStorage: KVStorage,
    smn: SystemMessageNotifier,
    analytics: FirebaseAnalytics
) : BasePresenter<CustomModeCameraView>(smn, analytics) {

    init {
        viewState.loadConfigs(ziggeo)
    }

    fun onPlayClicked(filePath: String) {
        kvStorage.put(VIDEO_FILE_PATH, filePath)
        router.startFlow(Screens.CustomModeVideo)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        router.newRootFlow(Screens.MainFlow)
    }
}