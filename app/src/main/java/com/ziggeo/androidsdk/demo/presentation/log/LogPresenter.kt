package com.ziggeo.androidsdk.demo.presentation.log

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.analytics.FirebaseAnalytics
import com.ziggeo.androidsdk.demo.model.interactor.LogsInteractor
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class LogPresenter @Inject constructor(
    systemMessageNotifier: SystemMessageNotifier,
    private val logsInteractor: LogsInteractor,
    analytics: FirebaseAnalytics
) : BasePresenter<LogView>(systemMessageNotifier, analytics) {

    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable = logsInteractor.getLogsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading(true) }
            .doFinally { viewState.showLoading(false) }
            .subscribe({ data ->
                if (data.isEmpty()) {
                    viewState.showNoLogsMessage()
                } else {
                    viewState.showLogs(data)
                }
            }, {
                commonOnError(it)
                viewState.showNoLogsMessage()
            })
    }

    override fun detachView(view: LogView?) {
        super.detachView(view)
        disposable?.dispose()
    }
}