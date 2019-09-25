package com.ziggeo.androidsdk.demo.ui.settings

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.settings.SettingsPresenter
import com.ziggeo.androidsdk.demo.presentation.settings.SettingsView
import com.ziggeo.androidsdk.demo.ui.global.BaseFragment


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class SettingsFragment : BaseFragment(), SettingsView {
    override val layoutRes = R.layout.fragment_settings

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    fun providePresenter(): SettingsPresenter =
        scope.getInstance(SettingsPresenter::class.java)
}