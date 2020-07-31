package com.ziggeo.androidsdk.demo.presentation.log

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.ziggeo.androidsdk.demo.presentation.global.BaseView
import com.ziggeo.androidsdk.log.LogModel


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface LogView : BaseView {
    fun showLogs(logModels: List<LogModel>)
    fun showNoLogsMessage()
}