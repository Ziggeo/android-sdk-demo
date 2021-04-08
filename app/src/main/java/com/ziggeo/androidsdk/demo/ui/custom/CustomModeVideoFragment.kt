package com.ziggeo.androidsdk.demo.ui.custom

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.di.DI
import com.ziggeo.androidsdk.demo.presentation.custom.CustomModeVideoPresenter
import com.ziggeo.androidsdk.demo.ui.global.BaseScreenFragment
import kotlinx.android.synthetic.main.fragment_custom_video_player.*

class CustomModeVideoFragment :
    BaseScreenFragment<CustomModeVideoView,
            CustomModeVideoPresenter>(), CustomModeVideoView {

    override val parentScopeName = DI.APP_SCOPE

    override val layoutRes: Int
        get() = R.layout.fragment_custom_video_player

    @InjectPresenter
    lateinit var presenter: CustomModeVideoPresenter

    @ProvidePresenter
    override fun providePresenter(): CustomModeVideoPresenter =
        scope.getInstance(CustomModeVideoPresenter::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        z_video_view.loadConfigs()
        z_video_view.initViews()

        if (z_video_view.callback != null) {
            z_video_view.callback.loaded()
        }
    }

    override fun onPause() {
        super.onPause()
        z_video_view.onPause()
    }

    override fun onResume() {
        super.onResume()
        z_video_view.onResume()
    }

    override fun startVideo(token: String) {
        z_video_view.videoTokens = listOf(token)
        z_video_view.prepareQueueAndStartPlaying()
    }

    override fun startVideoFile(path: Uri) {
        z_video_view.videoUris = listOf(path)
        z_video_view.prepareQueueAndStartPlaying()
    }

}