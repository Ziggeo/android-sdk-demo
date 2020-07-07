package com.ziggeo.androidsdk.demo.presentation.log

import com.arellomobile.mvp.InjectViewState
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
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
    systemMessageNotifier: SystemMessageNotifier
) : BasePresenter<LogView>(systemMessageNotifier) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLogs(loadLogs())
    }

    private fun loadLogs(): String? {
        try {
            val process = Runtime.getRuntime().exec("logcat -d")
            val bufferedReader = BufferedReader(
                InputStreamReader(process.inputStream)
            )
            val log = StringBuilder()
            var line: String? = ""
            while (bufferedReader.readLine().also { line = it } != null) {
                log.append(line)
            }
            return log.toString()
        } catch (e: IOException) {
            Timber.e(e)
        }
        return null
    }
}