package com.ziggeo.androidsdk.demo.ui.topcustomers

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.topcustomers.TopCustomersPresenter
import com.ziggeo.androidsdk.demo.presentation.topcustomers.TopCustomersView
import com.ziggeo.androidsdk.demo.ui.global.BaseFragment


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class TopCustomersFragment : BaseFragment(), TopCustomersView {
    override val layoutRes = R.layout.fragment_top_customers

    @InjectPresenter
    lateinit var presenter: TopCustomersPresenter

    @ProvidePresenter
    fun providePresenter(): TopCustomersPresenter =
        scope.getInstance(TopCustomersPresenter::class.java)
}