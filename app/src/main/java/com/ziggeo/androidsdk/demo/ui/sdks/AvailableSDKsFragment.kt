package com.ziggeo.androidsdk.demo.ui.sdks

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.availablesdks.AvailableSDKsPresenter
import com.ziggeo.androidsdk.demo.presentation.availablesdks.AvailableSDKsView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class AvailableSDKsFragment : BaseToolbarFragment(), AvailableSDKsView {
    override val layoutRes = R.layout.fragment_available_sdks

    @InjectPresenter
    lateinit var presenter: AvailableSDKsPresenter

    @ProvidePresenter
    fun providePresenter(): AvailableSDKsPresenter =
        scope.getInstance(AvailableSDKsPresenter::class.java)

    override fun getTitleRes() = R.string.title_sdks

}