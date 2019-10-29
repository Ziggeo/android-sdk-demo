package com.ziggeo.androidsdk.demo.presentation.availablesdks

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.ziggeo.androidsdk.demo.model.data.feature.FeatureModel

/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface AvailableSDKsView : MvpView {
    fun showSdks(clientsList: List<FeatureModel>)

    fun openUrl(url: String)
}