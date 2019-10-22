package com.ziggeo.androidsdk.demo.ui.recordings

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
class RecordingsFragment : BaseToolbarFragment<RecordingsView, RecordingsPresenter>(),
    RecordingsView {
    override val layoutRes = R.layout.fragment_recordings

    @InjectPresenter
    lateinit var presenter: RecordingsPresenter

    private lateinit var rotateForward: Animation
    private lateinit var rotateBackward: Animation

    @ProvidePresenter
    override fun providePresenter(): RecordingsPresenter =
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
        adapter.onItemClickListener = object : RecordingsAdapter.ItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                presenter.onItemClicked(list[position])
            }
        }

        rv_recordings.layoutManager = LinearLayoutManager(context)
        rv_recordings.adapter = adapter
        rv_recordings.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fab_selector.isShown) {
                    presenter.onScrollDown()
                } else if (dy < 0 && !fab_selector.isShown) {
                    presenter.onScrollUp()
                }
            }
        })

        pull_to_refresh.setOnRefreshListener {
            presenter.onPullToRefresh()
        }
    }

    override fun showActionFabs() {
        fab_camera.show()
        fab_screen.show()
        fab_audio.show()
        fab_image.show()
    }

    override fun hideActionFabs() {
        fab_camera.hide()
        fab_screen.hide()
        fab_audio.hide()
        fab_image.hide()
    }

    override fun hideSelectorFab() {
        fab_selector.hide()
    }

    override fun showSelectorFab() {
        fab_selector.show()
    }

    override fun showNoRecordingsMessage() {
        tv_empty_list.visibility = View.VISIBLE
        rv_recordings.visibility = View.INVISIBLE
    }

    override fun showLoading() {
        tv_empty_list.visibility = View.INVISIBLE
        pull_to_refresh.isRefreshing = true
    }

    override fun hideLoading() {
        pull_to_refresh.isRefreshing = false
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

    override fun startShowAnimationMainFab() {
        fab_selector.startAnimation(rotateForward)
    }

    override fun startHideAnimationMainFab() {
        fab_selector.startAnimation(rotateBackward)
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