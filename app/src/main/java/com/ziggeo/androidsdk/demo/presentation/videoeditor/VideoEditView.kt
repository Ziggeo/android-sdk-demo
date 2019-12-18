package com.ziggeo.androidsdk.demo.presentation.videoeditor

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by Alexander Bedulin on 17-Dec-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface VideoEditView : MvpView {

    fun requestReadPermission()

    fun showFileSelector()

    fun showVideoSavedToNotification(path: String)

}