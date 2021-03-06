package com.ziggeo.androidsdk.demo.presentation.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainFlowView : MvpView {
    enum class MenuItem {
        RECORDINGS,
        VIDEO_EDITOR,
        SETTINGS,
        SDKS,
        CLIENTS,
        CONTACT_US,
        ABOUT,
        LOG,
    }

    fun selectMenuItem(item: MenuItem)

    fun showAccName(appToken: String?)

    fun openDrawer()
    fun closeDrawer()

    fun openAuthScreen()
    fun openRecordingsScreen()
    fun openSettingsScreen()
    fun openLogScreen()
    fun openSdksScreen()
    fun openClientsScreen()
    fun openContactUsScreen()
    fun openAboutScreen()
    fun openVideoEditorScreen()
}