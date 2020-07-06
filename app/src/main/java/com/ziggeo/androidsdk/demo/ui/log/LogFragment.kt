package com.ziggeo.androidsdk.demo.ui.log

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.log.LogPresenter
import com.ziggeo.androidsdk.demo.presentation.log.LogView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class LogFragment : BaseToolbarFragment<LogView, LogPresenter>(), LogView {
    override val layoutRes = R.layout.fragment_log

    @InjectPresenter
    lateinit var presenter: LogPresenter

    @ProvidePresenter
    override fun providePresenter(): LogPresenter =
        scope.getInstance(LogPresenter::class.java)

    override fun getHeaderTextRes() = R.string.log_header

}