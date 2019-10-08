package com.ziggeo.androidsdk.demo.ui.auth

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.di.DI
import com.ziggeo.androidsdk.demo.presentation.auth.AuthPresenter
import com.ziggeo.androidsdk.demo.presentation.auth.AuthView
import com.ziggeo.androidsdk.demo.ui.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_auth.*


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class AuthFragment : BaseFragment(), AuthView {
    override val layoutRes = R.layout.fragment_auth

    override val parentScopeName = DI.APP_SCOPE

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @ProvidePresenter
    fun providePresenter(): AuthPresenter =
        scope.getInstance(AuthPresenter::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_scan_qr.setOnClickListener {
            presenter.onScanQrClicked()
        }
    }

}