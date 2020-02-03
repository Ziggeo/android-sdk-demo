package com.ziggeo.androidsdk.demo.presentation.auth

import com.arellomobile.mvp.InjectViewState
import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.demo.Screens
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import com.ziggeo.androidsdk.qr.QrScannerCallback
import ru.terrakok.cicerone.Router
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class AuthPresenter @Inject constructor(
    private var ziggeo: IZiggeo,
    private var prefs: Prefs,
    private var router: Router
) : BasePresenter<AuthView>() {

    private val qrScannerCallback = object : QrScannerCallback() {
        override fun onQrDecoded(value: String) {
            super.onQrDecoded(value)
            if (value.isNotEmpty()) {
                prefs.appToken = value
                ziggeo.appToken = value
                navigateToMainFlow()
            }
        }
    }

    fun onScanQrClicked() {
        ziggeo.qrScannerConfig.callback = qrScannerCallback
        ziggeo.startQrScanner()
    }

    fun onEnterQrManuallyClicked() {
        viewState.showEnterQrField()
    }

    fun onUseScannerClicked() {
        viewState.showScannerButton()
    }

    fun onUseEnteredQrClicked(value: String) {
        if (value.isEmpty()) {
            viewState.showQrCannotBeEmptyError()
        } else {
            qrScannerCallback.onQrDecoded(value)
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