package com.ziggeo.androidsdk.demo.ui.global

import com.arellomobile.mvp.MvpView
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter


/**
 * Created by Alexander Bedulin on 17-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
abstract class BaseScreenFragment<V : MvpView, P : BasePresenter<V>> : BaseFragment() {
    abstract fun providePresenter(): P

    override fun onBackPressed() {
        super.onBackPressed()
        providePresenter().onBackPressed()
    }
}