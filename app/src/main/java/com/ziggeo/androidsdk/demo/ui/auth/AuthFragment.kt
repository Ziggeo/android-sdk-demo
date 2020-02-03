package com.ziggeo.androidsdk.demo.ui.auth

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.di.DI
import com.ziggeo.androidsdk.demo.presentation.auth.AuthPresenter
import com.ziggeo.androidsdk.demo.presentation.auth.AuthView
import com.ziggeo.androidsdk.demo.ui.global.BaseScreenFragment
import kotlinx.android.synthetic.main.fragment_auth.*


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class AuthFragment : BaseScreenFragment<AuthView, AuthPresenter>(), AuthView {
    override val layoutRes = R.layout.fragment_auth

    override val parentScopeName = DI.APP_SCOPE

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_scan_qr.setOnClickListener {
            presenter.onScanQrClicked()
        }
        tv_enter_manually.setOnClickListener {
            presenter.onEnterQrManuallyClicked()
        }
        tv_use_scanner.setOnClickListener {
            presenter.onUseScannerClicked()
        }
        et_qr.setOnEditorActionListener { _, _, _ ->
            btn_use_entered_qr.performClick()
            true
        }
        btn_use_entered_qr.setOnClickListener {
            presenter.onUseEnteredQrClicked(et_qr.text.toString())
        }
    }

    override fun showQrCannotBeEmptyError() {
        et_qr.error = getString(R.string.err_not_empty)
    }

    @ProvidePresenter
    override fun providePresenter(): AuthPresenter = scope.getInstance(AuthPresenter::class.java)

    override fun showScannerButton() {
        btn_scan_qr.visibility = View.VISIBLE
        tv_enter_manually.visibility = View.VISIBLE

        til_qr.visibility = View.INVISIBLE
        btn_use_entered_qr.visibility = View.GONE
        tv_use_scanner.visibility = View.GONE
    }

    override fun showEnterQrField() {
        btn_scan_qr.visibility = View.GONE
        tv_enter_manually.visibility = View.GONE

        til_qr.visibility = View.VISIBLE
        btn_use_entered_qr.visibility = View.VISIBLE
        tv_use_scanner.visibility = View.VISIBLE
    }
}