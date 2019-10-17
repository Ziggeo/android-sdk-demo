package com.ziggeo.androidsdk.demo.ui.about

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.about.AboutPresenter
import com.ziggeo.androidsdk.demo.presentation.about.AboutView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class AboutFragment : BaseToolbarFragment<AboutView, AboutPresenter>(), AboutView {
    override val layoutRes = R.layout.fragment_about

    @InjectPresenter
    lateinit var presenter: AboutPresenter

    @ProvidePresenter
    override fun providePresenter(): AboutPresenter =
        scope.getInstance(AboutPresenter::class.java)

    override fun getTitleRes() = R.string.title_about

}