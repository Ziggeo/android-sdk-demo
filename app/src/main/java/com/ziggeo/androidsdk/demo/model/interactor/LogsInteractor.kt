package com.ziggeo.androidsdk.demo.model.interactor

import com.ziggeo.androidsdk.demo.ui.log.EventLogger
import com.ziggeo.androidsdk.log.LogModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Alexander Bedulin on 03-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class LogsInteractor @Inject constructor(
    private val logger: EventLogger
) {
    fun getLogsList(): Single<List<LogModel>> =
        Single.fromCallable { logger.logsDump() }
}
