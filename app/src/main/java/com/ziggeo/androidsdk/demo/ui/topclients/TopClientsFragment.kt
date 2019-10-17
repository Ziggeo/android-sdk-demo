package com.ziggeo.androidsdk.demo.ui.topclients

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.topclients.TopClientsPresenter
import com.ziggeo.androidsdk.demo.presentation.topclients.TopClientsView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class TopClientsFragment : BaseToolbarFragment<TopClientsView, TopClientsPresenter>(),
    TopClientsView {
    override val layoutRes = R.layout.fragment_top_clients

    @InjectPresenter
    lateinit var presenter: TopClientsPresenter

    @ProvidePresenter
    override fun providePresenter(): TopClientsPresenter =
        scope.getInstance(TopClientsPresenter::class.java)

    override fun getTitleRes() = R.string.title_clients

}