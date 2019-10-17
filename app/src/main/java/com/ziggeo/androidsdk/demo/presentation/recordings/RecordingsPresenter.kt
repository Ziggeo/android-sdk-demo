package com.ziggeo.androidsdk.demo.presentation.recordings

import com.arellomobile.mvp.InjectViewState
import com.ziggeo.androidsdk.demo.model.interactor.RecordingsInteractor
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class RecordingsPresenter @Inject constructor(
    private val recordingsInteractor: RecordingsInteractor,
    private var router: Router
) : BasePresenter<RecordingsView>() {

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

    override fun onBackPressed() {
        super.onBackPressed()
        router.exit()
    }

    fun onPullToRefresh(){
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

    private fun updateRecordingsList() {
        disposable = recordingsInteractor.getRecordingsList()
            .doOnSubscribe { viewState.showLoading() }
            .doFinally { viewState.hideLoading() }
            .subscribe({ data ->
                if (data.isEmpty()) {
                    viewState.showNoRecordingsMessage()
                } else {
                    viewState.showRecordingsList(data)
                }
            }, { error ->
                viewState.showNoRecordingsMessage()
                viewState.showError()
                Timber.e(error)
            })
    }
}