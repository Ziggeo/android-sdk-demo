package com.ziggeo.androidsdk.demo.presentation.global

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessage
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
open class BasePresenter<V : MvpView>(
    protected var systemMessageNotifier: SystemMessageNotifier,
    protected var analytics: FirebaseAnalytics
) : MvpPresenter<V>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }

    open fun onBackPressed() {

    }

    protected fun commonOnError(throwable: Throwable) {
        analytics.logEvent("common_error") {
            param("error", throwable.toString())
        }
        systemMessageNotifier.send(SystemMessage(R.string.err_common))
        Timber.e(throwable)
    }
}