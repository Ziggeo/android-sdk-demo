package com.ziggeo.androidsdk.demo.model.interactor

import com.ziggeo.androidsdk.demo.model.data.feature.LogModel
import com.ziggeo.androidsdk.demo.ui.log.EventLogger
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alexander Bedulin on 03-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class LogsInteractor @Inject constructor(
    private val logger: EventLogger
) {

    fun getLogsList(): Observable<List<LogModel>> =
        Observable.fromCallable { logger.logsDump() }
}
