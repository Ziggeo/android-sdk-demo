package com.ziggeo.androidsdk.demo.ui.custom

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.demo.presentation.global.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface CustomModeCameraView : BaseView {

    fun loadConfigs(ziggeo: IZiggeo)
}