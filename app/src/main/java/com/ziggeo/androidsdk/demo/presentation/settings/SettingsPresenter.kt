package com.ziggeo.androidsdk.demo.presentation.settings

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
class SettingsPresenter @Inject constructor(
    systemMessageNotifier: SystemMessageNotifier,
    analytics: FirebaseAnalytics
) : BasePresenter<SettingsView>(systemMessageNotifier, analytics)