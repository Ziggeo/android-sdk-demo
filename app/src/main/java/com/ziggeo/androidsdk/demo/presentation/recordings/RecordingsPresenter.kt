package com.ziggeo.androidsdk.demo.presentation.recordings

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.Screens
import com.ziggeo.androidsdk.demo.model.data.storage.KVStorage
import com.ziggeo.androidsdk.demo.model.data.storage.VIDEO_TOKEN
import com.ziggeo.androidsdk.demo.model.interactor.RecordingsInteractor
import com.ziggeo.androidsdk.demo.model.system.flow.FlowRouter
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessage
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.presentation.global.BaseMainFlowPresenter
import com.ziggeo.androidsdk.net.exceptions.ResponseException
import com.ziggeo.androidsdk.net.models.ContentModel
import com.ziggeo.androidsdk.net.models.audios.Audio
import com.ziggeo.androidsdk.net.models.videos.VideoModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class RecordingsPresenter @Inject constructor(
    private val recordingsInteractor: RecordingsInteractor,
    private var router: FlowRouter,
    private var kvStorage: KVStorage,
    systemMessageNotifier: SystemMessageNotifier,
    analytics: FirebaseAnalytics
) : BaseMainFlowPresenter<RecordingsView>(router, systemMessageNotifier, analytics) {

    private var fabActionsExpanded = false
    private var disposable: Disposable? = null

    override fun attachView(view: RecordingsView?) {
        super.attachView(view)
        updateRecordingsList()
    }

    override fun detachView(view: RecordingsView?) {
        super.detachView(view)
        disposable?.dispose()
    }

    fun onPullToRefresh() {
        updateRecordingsList()
    }

    fun onFabCameraClicked() {
        viewState.startCameraRecorder()
    }

    fun onFabScreenClicked() {
        viewState.startScreenRecorder()
    }

    fun onFabAudioClicked() {
        viewState.startAudioRecorder()
    }

    fun onFabImageClicked() {
        viewState.startImageCapture()
    }

    fun onFabFileClicked() {
        viewState.startFileSelector()
    }

    fun onFabActionsClicked() {
        if (fabActionsExpanded) {
            viewState.startHideAnimationMainFab()
            viewState.hideActionFabs()
        } else {
            viewState.startShowAnimationMainFab()
            viewState.showActionFabs()
        }
        fabActionsExpanded = !fabActionsExpanded
    }

    fun onScrollUp() {
        viewState.showSelectorFab()
    }

    fun onScrollDown() {
        if (fabActionsExpanded) {
            viewState.startHideAnimationMainFab()
            fabActionsExpanded = false
        }
        viewState.hideActionFabs()
        viewState.hideSelectorFab()
    }

    fun onItemClicked(model: ContentModel) {
        if (model is VideoModel) {
            kvStorage.put(VIDEO_TOKEN, model.token)
            router.startFlow(Screens.RecordingDetailsFlow)
        }
        if (model is Audio) {
            //todo add detail audio screen
        }
    }

    private fun updateRecordingsList() {
        disposable = recordingsInteractor.getRecordingsList()
            .doOnSubscribe { viewState.showLoading(true) }
            .doFinally { viewState.showLoading(false) }
            .subscribe({ data ->
                if (data.isEmpty()) {
                    analytics.logEvent("loaded_recordings_success") {
                        param("count", data.size.toString())
                    }
                    viewState.showNoRecordingsMessage()
                } else {
                    viewState.showRecordingsList(data)
                }
            }, {
                if (it is ResponseException && indexOperationNotAllowed(it.statusCode)) {
                    systemMessageNotifier.send(SystemMessage(R.string.err_check_indexing))
                    analytics.logEvent("loaded_recordings_error") {
                        param("reason", "indexOperationNotAllowed")
                    }
                } else {
                    commonOnError(it)
                    viewState.showNoRecordingsMessage()
                }
            })
    }

    /**
     * Operation can be forbidden in account settings
     */
    private fun indexOperationNotAllowed(code: Int): Boolean {
        val unauthorized = 401
        return code == unauthorized
    }
}