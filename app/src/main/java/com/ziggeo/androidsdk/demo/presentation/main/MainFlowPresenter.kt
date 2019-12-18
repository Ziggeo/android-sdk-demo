package com.ziggeo.androidsdk.demo.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.demo.model.interactor.SessionInteractor
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import com.ziggeo.androidsdk.demo.presentation.main.MainFlowView.MenuItem
import com.ziggeo.androidsdk.demo.presentation.main.MainFlowView.MenuItem.*
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class MainFlowPresenter @Inject constructor(
    private val sessionInteractor: SessionInteractor,
    private val prefs: Prefs
) : BasePresenter<MainFlowView>() {

    private var currentSelectedItem: MenuItem? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showAccName(prefs.appToken)
    }

    fun onMenuItemClick(item: MenuItem) {
        viewState.closeDrawer()
        if (item != currentSelectedItem) {
            when (item) {
                RECORDINGS -> viewState.openRecordingsScreen()
                SETTINGS -> viewState.openSettingsScreen()
                SDKS -> viewState.openSdksScreen()
                CLIENTS -> viewState.openClientsScreen()
                CONTACT_US -> viewState.openContactUsScreen()
                ABOUT -> viewState.openAboutScreen()
                VIDEO_EDITOR -> viewState.openVideoEditorScreen()
            }
        }
    }

    fun onLogoutClick() {
        viewState.closeDrawer()
        sessionInteractor.logout()
        viewState.openAuthScreen()
    }
}