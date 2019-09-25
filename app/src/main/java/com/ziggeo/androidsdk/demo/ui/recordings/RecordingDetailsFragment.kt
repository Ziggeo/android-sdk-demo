package com.ziggeo.androidsdk.demo.ui.recordings

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.recordings.RecordingDetailsPresenter
import com.ziggeo.androidsdk.demo.presentation.recordings.RecordingDetailsView
import com.ziggeo.androidsdk.demo.ui.global.BaseFragment


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class RecordingDetailsFragment : BaseFragment(), RecordingDetailsView {
    override val layoutRes = R.layout.fragment_recording_details

    @InjectPresenter
    lateinit var presenter: RecordingDetailsPresenter

    @ProvidePresenter
    fun providePresenter(): RecordingDetailsPresenter =
        scope.getInstance(RecordingDetailsPresenter::class.java)
}