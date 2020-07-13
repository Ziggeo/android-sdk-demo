package com.ziggeo.androidsdk.demo.presentation.global

import com.arellomobile.mvp.MvpView
import com.google.firebase.analytics.FirebaseAnalytics
import com.ziggeo.androidsdk.demo.model.system.flow.FlowRouter
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
open class BaseMainFlowPresenter<V : MvpView>(
    private var router: FlowRouter,
    systemMessageNotifier: SystemMessageNotifier,
    analytics: FirebaseAnalytics
) : BasePresenter<V>(systemMessageNotifier, analytics) {

    override fun onBackPressed() {
        super.onBackPressed()
        router.exit()
    }

}