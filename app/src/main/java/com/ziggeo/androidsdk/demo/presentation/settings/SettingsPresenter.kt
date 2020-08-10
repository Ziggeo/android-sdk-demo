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

    fun onStartDelayChanged(time: Int) {
        settingsModel.startDelay = time
    }

    fun onSaveClicked() {
        prefs.startDelay = settingsModel.startDelay
        viewState.showSavedNotification()
    }

}