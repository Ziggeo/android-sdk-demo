package com.ziggeo.androidsdk.demo.presentation.recordings

import com.arellomobile.mvp.InjectViewState
import com.ziggeo.androidsdk.demo.Screens
import com.ziggeo.androidsdk.demo.model.system.flow.FlowRouter
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
@InjectViewState
class RecordingDetailsPresenter @Inject constructor(
    private var router: FlowRouter
) : BasePresenter<RecordingDetailsView>() {

    override fun onBackPressed() {
        super.onBackPressed()
        router.newRootFlow(Screens.MainFlow)
    }

}