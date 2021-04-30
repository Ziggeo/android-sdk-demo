package com.ziggeo.androidsdk.demo.ui.custom

import android.net.Uri
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.ziggeo.androidsdk.demo.presentation.global.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface CustomModeVideoView : BaseView {

    fun startVideo(token: String)

    fun startVideoFile(path: Uri)

}