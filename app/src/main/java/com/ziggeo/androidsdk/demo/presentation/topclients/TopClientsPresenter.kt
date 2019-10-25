package com.ziggeo.androidsdk.demo.presentation.topclients

import com.arellomobile.mvp.InjectViewState
import com.ziggeo.androidsdk.demo.model.ClientModel
import com.ziggeo.androidsdk.demo.model.interactor.TopClientsInteractor
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import io.reactivex.disposables.Disposable
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class TopClientsPresenter @Inject constructor(
    var topClientsInteractor: TopClientsInteractor
) : BasePresenter<TopClientsView>() {
    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable = topClientsInteractor.getClientsList()
            .subscribe { data: List<ClientModel> ->
                viewState.showClients(data)
            }
    }

    override fun detachView(view: TopClientsView?) {
        super.detachView(view)
        disposable?.dispose()
    }

    fun onClientItemClicked(model: ClientModel) {
        viewState.openUrl(model.url)
    }
}