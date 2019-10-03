package com.ziggeo.androidsdk.demo.ui.recordings

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.recordings.RecordingsPresenter
import com.ziggeo.androidsdk.demo.presentation.recordings.RecordingsView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment
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

    private lateinit var fabOpen: Animation
    private lateinit var fabClose: Animation
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
        fab_camera.startAnimation(fabOpen)
        fab_screen.startAnimation(fabOpen)
        fab_audio.startAnimation(fabOpen)
        fab_image.startAnimation(fabOpen)
    }

    override fun collapseFabActions() {
        fab_selector.startAnimation(rotateBackward)
        fab_camera.startAnimation(fabClose)
        fab_screen.startAnimation(fabClose)
        fab_audio.startAnimation(fabClose)
        fab_image.startAnimation(fabClose)
    }

    private fun initFab() {
        fabOpen = AnimationUtils.loadAnimation(context, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(context, R.anim.fab_close)
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