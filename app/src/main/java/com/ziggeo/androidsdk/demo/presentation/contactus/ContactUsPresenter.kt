package com.ziggeo.androidsdk.demo.presentation.contactus

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.analytics.FirebaseAnalytics
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class ContactUsPresenter @Inject constructor(
    systemMessageNotifier: SystemMessageNotifier,
    analytics: FirebaseAnalytics
) : BasePresenter<ContactUsView>(systemMessageNotifier, analytics) {

    fun onVisitSupportClicked() {
        viewState.openSupportPage()
    }

    fun onContactUsClicked() {
        viewState.sendEmailToZiggeo()
    }
}