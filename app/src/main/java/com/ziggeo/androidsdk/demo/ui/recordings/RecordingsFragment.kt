package ui.recordings

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
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

    @ProvidePresenter
    fun providePresenter(): RecordingsPresenter =
        scope.getInstance(RecordingsPresenter::class.java)

    override fun getTitleRes() = R.string.title_recordings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFab()
    }

    private fun initFab() {
        val fabOpen = AnimationUtils.loadAnimation(context, R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(context, R.anim.fab_close)
        val rotateForward = AnimationUtils.loadAnimation(context, R.anim.rotate_forward)
        val rotateBackward = AnimationUtils.loadAnimation(context, R.anim.rotate_backward)

        var isExpanded = false
        fab_selector.setOnClickListener {
            if (isExpanded) {
                fab_selector.startAnimation(rotateBackward)
                fab_video.startAnimation(fabClose)
                fab_screen.startAnimation(fabClose)
                fab_audio.startAnimation(fabClose)
                fab_image.startAnimation(fabClose)
            } else {
                fab_selector.startAnimation(rotateForward)
                fab_video.startAnimation(fabOpen)
                fab_screen.startAnimation(fabOpen)
                fab_audio.startAnimation(fabOpen)
                fab_image.startAnimation(fabOpen)
            }
            isExpanded = !isExpanded
        }

    }

}