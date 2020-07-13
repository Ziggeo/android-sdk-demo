package com.ziggeo.androidsdk.demo.model.interactor

import android.annotation.SuppressLint
import android.content.Context
import com.ziggeo.androidsdk.demo.model.data.feature.LogModel
import com.ziggeo.androidsdk.demo.ui.log.EventLogger
import com.ziggeo.androidsdk.net.models.DeviceInfo
import io.reactivex.Single
import timber.log.Timber
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

/**
 * Created by Alexander Bedulin on 03-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class LogsInteractor @Inject constructor(
    private val logger: EventLogger,
    private val context: Context
) {

    fun getLogsList(): Single<List<LogModel>> =
        Single.fromCallable { logger.logsDump() }

    @SuppressLint("CheckResult")
    fun saveDumpToFile(): Single<File> {
        val dumpName = "ziggeoLogs_${System.currentTimeMillis()}.log"
        return getLogsList().flatMap { list: List<LogModel> ->
            val file = File(context.externalCacheDir, dumpName)
            val sb = StringBuilder(DeviceInfo().toString())
                .append("\n")
            for (model in list) {
                sb.append(model.toString())
                    .append("\n")
            }
            sb.append(getSystemLog())
            file.appendText(sb.toString())
            Single.fromCallable { file }
        }
    }

    private fun getSystemLog(): String {
        var log = ""
        try {
            val process = Runtime.getRuntime().exec("logcat -d")
            log = BufferedReader(
                InputStreamReader(process.inputStream)
            ).use(BufferedReader::readText)
        } catch (e: IOException) {
            Timber.e(e)
        }
        return log
    }
}
