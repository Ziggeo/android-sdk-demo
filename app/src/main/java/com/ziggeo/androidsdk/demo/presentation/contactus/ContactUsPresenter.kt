package com.ziggeo.androidsdk.demo.presentation.contactus

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.analytics.FirebaseAnalytics
import com.ziggeo.androidsdk.demo.model.system.flow.FlowRouter
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.presentation.global.BaseMainFlowPresenter
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import com.ziggeo.androidsdk.demo.util.EmailSender
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class ContactUsPresenter @Inject constructor(
    router: FlowRouter,
    systemMessageNotifier: SystemMessageNotifier,
    analytics: FirebaseAnalytics,
    private val emailSender: EmailSender
) : BaseMainFlowPresenter<ContactUsView>(router, systemMessageNotifier, analytics) {

    fun onVisitSupportClicked() {
        viewState.openSupportPage()
    }

    fun onContactUsClicked() {
        emailSender.sendEmailToSupport()
    }
}