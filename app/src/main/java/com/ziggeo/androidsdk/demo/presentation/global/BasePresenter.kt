package com.ziggeo.androidsdk.demo.presentation.global

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessage
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageType
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
open class BasePresenter<V : MvpView>(
    private var systemMessageNotifier: SystemMessageNotifier
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
        systemMessageNotifier.send(SystemMessage(R.string.err_common))
        Timber.e(throwable)
    }
}