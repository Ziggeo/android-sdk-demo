package com.ziggeo.androidsdk.demo.presentation.availablesdks

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.analytics.FirebaseAnalytics
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.model.data.feature.FeatureModel
import com.ziggeo.androidsdk.demo.model.data.feature.SdkModel
import com.ziggeo.androidsdk.demo.model.interactor.AvailableSdksInteractor
import com.ziggeo.androidsdk.demo.model.system.flow.FlowRouter
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessage
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageType
import com.ziggeo.androidsdk.demo.presentation.global.BaseMainFlowPresenter
import io.reactivex.disposables.Disposable
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class AvailableSDKsPresenter @Inject constructor(
    private var availableSdksInteractor: AvailableSdksInteractor,
    router: FlowRouter,
    systemMessageNotifier: SystemMessageNotifier,
    analytics: FirebaseAnalytics
) : BaseMainFlowPresenter<AvailableSDKsView>(router, systemMessageNotifier, analytics) {
    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable = availableSdksInteractor.getSDKsList()
            .subscribe { data: List<FeatureModel> ->
                viewState.showSdks(data)
            }
    }

    override fun detachView(view: AvailableSDKsView?) {
        super.detachView(view)
        disposable?.dispose()
    }

    fun onClientItemClicked(model: SdkModel) {
        if (model.url.isEmpty()) {
            systemMessageNotifier.send(SystemMessage(R.string.coming_soon, SystemMessageType.TOAST))
        } else {
            viewState.openUrl(model.url)
        }
    }

}