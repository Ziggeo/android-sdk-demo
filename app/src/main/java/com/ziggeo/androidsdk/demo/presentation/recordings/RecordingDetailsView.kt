package com.ziggeo.androidsdk.demo.presentation.recordings

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.ziggeo.androidsdk.demo.presentation.global.BaseView
import com.ziggeo.androidsdk.net.models.videos.VideoModel


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface RecordingDetailsView : BaseView {

    fun showViewsInEditState()
    fun showViewsInViewState()

    fun showPreview(url: String)

    fun showConfirmDeleteDialog()
    fun hideConfirmDeleteDialog()

    fun showRecordingData(videoModel: VideoModel)

}