package com.ziggeo.androidsdk.demo.presentation.recordings

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.demo.Screens
import com.ziggeo.androidsdk.demo.model.data.storage.KVStorage
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.demo.model.data.storage.VIDEO_TOKEN
import com.ziggeo.androidsdk.demo.model.interactor.RecordingsInteractor
import com.ziggeo.androidsdk.demo.model.system.flow.FlowRouter
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import com.ziggeo.androidsdk.net.models.videos.VideoModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class RecordingDetailsPresenter @Inject constructor(
    private var recordingsInteractor: RecordingsInteractor,
    private var router: FlowRouter,
    private var kvStorage: KVStorage,
    private var ziggeo: IZiggeo,
    private val prefs: Prefs,
    smn: SystemMessageNotifier,
    analytics: FirebaseAnalytics
) : BasePresenter<RecordingDetailsView>(smn, analytics) {

    private lateinit var model: VideoModel
    private lateinit var videoToken: String
    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        videoToken = kvStorage.get(VIDEO_TOKEN) as String
        disposable = recordingsInteractor.getInfo(videoToken)
            .flatMap {
                this.model = it
                recordingsInteractor.getImageUrl(videoToken)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {  viewState.showRecordingData(model) }
            .doOnSubscribe {
                viewState.showLoading(true)
            }.doFinally {
                viewState.showLoading(false)
            }.subscribe { url, throwable ->
                url?.let {
                    viewState.showPreview(it)
                }
                throwable?.let {
                    commonOnError(it)
                }
            }
        viewState.showViewsInViewState()
    }

    fun onPlayClicked() {
        analytics.logEvent("play_clicked") {
            param("video_token", videoToken)
        }
        if (prefs.isCustomVideo) {
            kvStorage.put(VIDEO_TOKEN, model.token)
            router.startFlow(Screens.CustomModeVideo)
        } else {
            ziggeo.startPlayer(videoToken)
        }
    }

    fun onConfirmNoClicked() {
        viewState.hideConfirmDeleteDialog()
    }

    fun onConfirmYesClicked() {
        viewState.hideConfirmDeleteDialog()
        analytics.logEvent("delete_video") {
            param("video_token", videoToken)
        }
        disposable = recordingsInteractor.destroy(videoToken)
            .doOnError { commonOnError(it) }
            .doOnSubscribe {
                viewState.showLoading(true)
            }.doFinally {
                viewState.showLoading(false)
            }.subscribe {
                router.finishFlow()
            }
    }

    fun onDeleteClicked() {
        viewState.showConfirmDeleteDialog()
    }

    fun onSaveClicked(tokenOrKey: String, title: String, description: String) {
        analytics.logEvent("save_video_details") {
            param("video_token", videoToken)
        }
        if (model.token != tokenOrKey) {
            model.key = tokenOrKey
        }
        model.title = title
        model.description = description

        disposable = recordingsInteractor.updateInfo(model)
            .doOnError { commonOnError(it) }
            .doOnSubscribe {
                viewState.showLoading(true)
            }.doFinally {
                viewState.showLoading(false)
            }.subscribe { model ->
                this.model = model
                viewState.showViewsInViewState()
            }
    }

    fun onEditClicked() {
        analytics.logEvent("edit_video_details") {
            param("video_token", videoToken)
        }
        viewState.showViewsInEditState()
    }

    fun onCloseClicked() {
        viewState.showRecordingData(model)
        viewState.showViewsInViewState()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        router.newRootFlow(Screens.MainFlow)
    }
}