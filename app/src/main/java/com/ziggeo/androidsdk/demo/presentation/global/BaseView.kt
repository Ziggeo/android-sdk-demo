package com.ziggeo.androidsdk.demo.presentation.global

import com.arellomobile.mvp.MvpView


/**
 * Created by Alexander Bedulin on 18-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
interface BaseView : MvpView {
    fun showProgressDialog(boolean: Boolean)
}