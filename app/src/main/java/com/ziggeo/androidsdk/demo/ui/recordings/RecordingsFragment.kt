package com.ziggeo.androidsdk.demo.ui.recordings

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.recordings.RecordingsPresenter
import com.ziggeo.androidsdk.demo.presentation.recordings.RecordingsView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment
import com.ziggeo.androidsdk.net.models.videos.VideoModel
import kotlinx.android.synthetic.main.fragment_recordings.*


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class RecordingsFragment : BaseToolbarFragment(), RecordingsView {
    override val layoutRes = R.layout.fragment_recordings

    @InjectPresenter
    lateinit var presenter: RecordingsPresenter

    private lateinit var rotateForward: Animation
    private lateinit var rotateBackward: Animation

    @ProvidePresenter
    fun providePresenter(): RecordingsPresenter =
        scope.getInstance(RecordingsPresenter::class.java)

    override fun getTitleRes() = R.string.title_recordings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFab()
    }

    override fun showRecordingsList(list: List<VideoModel>) {
        tv_empty_list.visibility = View.INVISIBLE
        rv_recordings.visibility = View.VISIBLE

        val adapter = RecordingsAdapter(list)
        rv_recordings.layoutManager = LinearLayoutManager(context)
        rv_recordings.adapter = adapter
    }

    override fun showNoRecordingsMessage() {
        tv_empty_list.visibility = View.VISIBLE
        rv_recordings.visibility = View.INVISIBLE
    }

    override fun showLoading() {
        tv_empty_list.visibility = View.INVISIBLE
        pb_progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_progress.visibility = View.INVISIBLE
    }

    override fun showError() {
        Toast.makeText(context, R.string.common_error, Toast.LENGTH_SHORT).show()
    }

    override fun startCameraRecorder() {
        ziggeo.startCameraRecorder()
    }

    override fun startScreenRecorder() {
        ziggeo.startScreenRecorder(null)
    }

    override fun startAudioRecorder() {
        Toast.makeText(context, R.string.will_be_ready_soon, Toast.LENGTH_SHORT).show()
    }

    override fun startImageCapture() {
        Toast.makeText(context, R.string.will_be_ready_soon, Toast.LENGTH_SHORT).show()
    }

    override fun expandFabActions() {
        fab_selector.startAnimation(rotateForward)
        fab_camera.show()
        fab_screen.show()
        fab_audio.show()
        fab_image.show()
    }

    override fun collapseFabActions() {
        fab_selector.startAnimation(rotateBackward)
        fab_camera.hide()
        fab_screen.hide()
        fab_audio.hide()
        fab_image.hide()
    }

    private fun initFab() {
        rotateForward = AnimationUtils.loadAnimation(context, R.anim.rotate_forward)
        rotateBackward = AnimationUtils.loadAnimation(context, R.anim.rotate_backward)

        fab_selector.setOnClickListener {
            presenter.onFabActionsClicked()
        }
        fab_camera.setOnClickListener {
            presenter.onFabCameraClicked()
        }
        fab_screen.setOnClickListener {
            presenter.onFabScreenClicked()
        }
        fab_audio.setOnClickListener {
            presenter.onFabAudioClicked()
        }
        fab_image.setOnClickListener {
            presenter.onFabImageClicked()
        }
    }
}