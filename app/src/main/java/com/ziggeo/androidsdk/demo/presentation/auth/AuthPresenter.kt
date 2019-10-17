package com.ziggeo.androidsdk.demo.presentation.auth

import com.arellomobile.mvp.InjectViewState
import com.ziggeo.androidsdk.demo.Screens
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class AuthPresenter @Inject constructor(
    private var prefs: Prefs,
    private var router: Router
) : BasePresenter<AuthView>() {

    fun onScanQrClicked() {
        val token = ""
        if (token.isNotEmpty()) {
            prefs.appToken = token
            navigateToMainFlow()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        router.exit()
    }
    private fun navigateToMainFlow() {
        router.newRootScreen(Screens.MainFlow)
    }
}