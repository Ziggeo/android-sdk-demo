package com.ziggeo.androidsdk.demo.presentation.topclients

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.ziggeo.androidsdk.demo.model.data.feature.ClientModel


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface TopClientsView : MvpView {

    fun showClients(clientsList: List<ClientModel>)

    fun openUrl(url: String)
}