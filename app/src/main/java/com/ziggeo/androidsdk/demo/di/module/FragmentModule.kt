package com.ziggeo.androidsdk.demo.di.module

import android.annotation.SuppressLint
import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.Ziggeo
import com.ziggeo.androidsdk.callbacks.FileSelectorCallback
import com.ziggeo.androidsdk.callbacks.PlayerCallback
import com.ziggeo.androidsdk.callbacks.RecorderCallback
import com.ziggeo.androidsdk.callbacks.UploadingCallback
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.demo.ui.log.EventLogger
import com.ziggeo.androidsdk.net.services.streams.IStreamsServiceRx
import com.ziggeo.androidsdk.net.services.videos.IVideosServiceRx
import com.ziggeo.androidsdk.recorder.MicSoundLevel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import toothpick.config.Module
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class FragmentModule(context: Context, prefs: Prefs, logger: EventLogger) : Module() {

    init {
        val token = prefs.appToken!!
        val ziggeo = Ziggeo(token, context)
        bind(IZiggeo::class.java).toInstance(ziggeo)
        bind(IVideosServiceRx::class.java).toInstance(ziggeo.apiRx().videos())
        bind(IStreamsServiceRx::class.java).toInstance(ziggeo.apiRx().streams())
        bindPrefs(ziggeo, prefs)

        val analytics = FirebaseAnalytics.getInstance(context)
        analytics.setUserId(token)
        bind(FirebaseAnalytics::class.java).toInstance(analytics)

        FirebaseCrashlytics.getInstance().setCustomKey("app_token", token)

        initRecorderCallback(ziggeo, logger, context)
        initPlayerCallback(ziggeo, logger, context)
        initUploaderCallback(ziggeo, logger, context)
        initFileSelectorCallback(ziggeo, logger, context)
    }

    fun bindPrefs(ziggeo: Ziggeo, prefs: Prefs) {
        ziggeo.recorderConfig.startDelay = prefs.startDelay
    }

    private fun initRecorderCallback(ziggeo: IZiggeo, logger: EventLogger, context: Context) {
        ziggeo.recorderConfig.callback = object : RecorderCallback() {
            override fun loaded() {
                super.loaded()
                logger.addEvent(context.getString(R.string.ev_rec_loaded))
            }

            override fun manuallySubmitted() {
                super.manuallySubmitted()
                logger.addEvent(context.getString(R.string.ev_rec_manuallySubmitted))
            }

            override fun recordingStarted() {
                super.recordingStarted()
                logger.addEvent(context.getString(R.string.ev_rec_recordingStarted))
            }

            override fun recordingStopped(path: String) {
                super.recordingStopped(path)
                logger.addEvent(context.getString(R.string.ev_rec_recordingStopped), path)
            }

            override fun countdown(timeLeft: Int) {
                super.countdown(timeLeft)
                logger.addEvent(context.getString(R.string.ev_rec_countdown), timeLeft.toString())
            }

            override fun recordingProgress(time: Long) {
                super.recordingProgress(time)
                logger.addEvent(
                    context.getString(
                        R.string.ev_rec_recordingProgress
                    ),
                    time.toString()
                )
            }

            override fun readyToRecord() {
                super.readyToRecord()
                logger.addEvent(context.getString(R.string.ev_rec_readyToRecord))
            }

            override fun accessForbidden(permissions: MutableList<String>) {
                super.accessForbidden(permissions)
                logger.addEvent(
                    context.getString(
                        R.string.ev_rec_accessForbidden
                    ),
                    permissions.toString()
                )
            }

            override fun accessGranted() {
                super.accessGranted()
                logger.addEvent(context.getString(R.string.ev_rec_accessGranted))
            }

            override fun noCamera() {
                super.noCamera()
                logger.addEvent(context.getString(R.string.ev_rec_noCamera))
            }

            override fun noMicrophone() {
                super.noMicrophone()
                logger.addEvent(context.getString(R.string.ev_rec_noMicrophone))
            }

            override fun hasCamera() {
                super.hasCamera()
                logger.addEvent(context.getString(R.string.ev_rec_hasCamera))
            }

            override fun hasMicrophone() {
                super.hasMicrophone()
                logger.addEvent(context.getString(R.string.ev_rec_hasMicrophone))
            }

            override fun microphoneHealth(micStatus: MicSoundLevel) {
                super.microphoneHealth(micStatus)
                logger.addEvent(context.getString(R.string.ev_rec_microphoneHealth), micStatus.name)
            }

            override fun canceledByUser() {
                super.canceledByUser()
                logger.addEvent(context.getString(R.string.ev_rec_canceledByUser))
            }

            override fun error(throwable: Throwable) {
                super.error(throwable)
                logger.addEvent(context.getString(R.string.ev_rec_error), throwable.toString())
            }

            override fun streamingStarted() {
                super.streamingStarted()
                logger.addEvent(context.getString(R.string.ev_rec_streamingStarted))
            }

            override fun streamingStopped() {
                super.streamingStopped()
                logger.addEvent(context.getString(R.string.ev_rec_streamingStopped))
            }
        }
    }

    private fun initPlayerCallback(ziggeo: IZiggeo, logger: EventLogger, context: Context) {
        ziggeo.playerConfig.callback = object : PlayerCallback() {
            override fun loaded() {
                super.loaded()
                logger.addEvent(context.getString(R.string.ev_pl_loaded))
            }

            override fun accessForbidden(permissions: MutableList<String>) {
                super.accessForbidden(permissions)
                logger.addEvent(
                    context.getString(
                        R.string.ev_pl_accessForbidden
                    ),
                    permissions.toString()
                )
            }

            override fun accessGranted() {
                super.accessGranted()
                logger.addEvent(context.getString(R.string.ev_pl_accessGranted))
            }

            override fun canceledByUser() {
                super.canceledByUser()
                logger.addEvent(context.getString(R.string.ev_pl_canceledByUser))
            }

            override fun error(throwable: Throwable) {
                super.error(throwable)
                logger.addEvent(context.getString(R.string.ev_pl_error), throwable.toString())
            }

            override fun playing() {
                super.playing()
                logger.addEvent(context.getString(R.string.ev_pl_playing))
            }

            override fun paused() {
                super.paused()
                logger.addEvent(context.getString(R.string.ev_pl_paused))
            }

            override fun ended() {
                super.ended()
                logger.addEvent(context.getString(R.string.ev_pl_ended))
            }

            override fun seek(millis: Long) {
                super.seek(millis)
                logger.addEvent(context.getString(R.string.ev_pl_seek), millis.toString())
            }

            override fun readyToPlay() {
                super.readyToPlay()
                logger.addEvent(context.getString(R.string.ev_pl_readyToPlay))
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun initUploaderCallback(ziggeo: IZiggeo, logger: EventLogger, context: Context) {
        val subj = PublishSubject.create<Progress>()
        subj.debounce(100, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                logger.addEvent(
                    context.getString(
                        R.string.ev_upl_uploadProgress
                    ),
                    "${it.token} ${it.uploaded}/${it.total}"
                )
            }

        ziggeo.uploadingConfig.callback = object : UploadingCallback() {
            override fun uploaded(path: String, token: String) {
                super.uploaded(path, token)
                logger.addEvent(
                    context.getString(R.string.ev_upl_uploaded),
                    "$token $path"
                )
            }

            override fun uploadingStarted(path: String) {
                super.uploadingStarted(path)
                logger.addEvent(context.getString(R.string.ev_upl_uploadingStarted), path)
            }

            override fun uploadProgress(
                videoToken: String,
                file: File,
                uploaded: Long,
                total: Long
            ) {
                super.uploadProgress(videoToken, file, uploaded, total)
                subj.onNext(Progress(videoToken, uploaded, total))
            }

            override fun processing(token: String) {
                super.processing(token)
                logger.addEvent(context.getString(R.string.ev_upl_processing), token)
            }

            override fun processed(token: String) {
                super.processed(token)
                logger.addEvent(context.getString(R.string.ev_upl_processed), token)
            }

            override fun verified(token: String) {
                super.verified(token)
                logger.addEvent(context.getString(R.string.ev_upl_verified), token)
            }

            override fun error(throwable: Throwable) {
                super.error(throwable)
                logger.addEvent(context.getString(R.string.ev_upl_error), throwable.toString())
            }
        }
    }

    private fun initFileSelectorCallback(ziggeo: IZiggeo, logger: EventLogger, context: Context) {
        ziggeo.fileSelectorConfig.callback = object : FileSelectorCallback() {
            override fun uploadSelected(paths: MutableList<String>) {
                super.uploadSelected(paths)
                logger.addEvent(context.getString(R.string.ev_fs_uploadSelected), paths.toString())
            }

            override fun loaded() {
                super.loaded()
                logger.addEvent(context.getString(R.string.ev_fs_loaded))
            }

            override fun canceledByUser() {
                super.canceledByUser()
                logger.addEvent(context.getString(R.string.ev_fs_canceledByUser))
            }

            override fun accessForbidden(permissions: MutableList<String>) {
                super.accessForbidden(permissions)
                logger.addEvent(
                    context.getString(
                        R.string.ev_fs_accessForbidden
                    ),
                    permissions.toString()
                )
            }

            override fun accessGranted() {
                super.accessGranted()
                logger.addEvent(context.getString(R.string.ev_fs_accessGranted))
            }

            override fun error(throwable: Throwable) {
                super.error(throwable)
                logger.addEvent(context.getString(R.string.ev_fs_error), throwable.toString())
            }
        }

    }

    data class Progress(val token: String, val uploaded: Long, val total: Long)
}