package com.ziggeo.androidsdk.demo.ui.recordings

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.recordings.RecordingDetailsPresenter
import com.ziggeo.androidsdk.demo.presentation.recordings.RecordingDetailsView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment
import com.ziggeo.androidsdk.demo.ui.global.MessageDialogFragment
import com.ziggeo.androidsdk.net.models.videos.VideoModel
import kotlinx.android.synthetic.main.fragment_recording_details.*


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
private const val CONFIRM_TAG = "CONFIRM_TAG"

class RecordingDetailsFragment : BaseToolbarFragment<RecordingDetailsView,
        RecordingDetailsPresenter>(),
    RecordingDetailsView,
    MessageDialogFragment.OnClickListener {

    override fun getTitleRes(): Int = R.string.title_details

    override val layoutRes = R.layout.fragment_recording_details

    @InjectPresenter
    lateinit var presenter: RecordingDetailsPresenter

    @ProvidePresenter
    override fun providePresenter(): RecordingDetailsPresenter =
        scope.getInstance(RecordingDetailsPresenter::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener {
            presenter.onBackPressed()
        }
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_edit -> presenter.onEditClicked()
                R.id.item_delete -> presenter.onDeleteClicked()
                R.id.item_save -> presenter.onSaveClicked(
                    et_token_or_key.text.toString(),
                    et_title.text.toString(),
                    et_description.text.toString()
                )
            }
            true
        }
        iv_ic_play.setOnClickListener {
            presenter.onPlayClicked()
        }
    }

    override fun showRecordingData(videoModel: VideoModel) {
        et_token_or_key.setText(videoModel.key ?: videoModel.token)
        et_title.setText(videoModel.title)
        et_description.setText(videoModel.description)
    }

    override fun showPreview(url: String) {
        Glide.with(iv_preview).load(url).into(iv_preview)
    }

    override fun showViewsInEditState() {
        toolbar.menu.clear()
        toolbar.inflateMenu(R.menu.details_menu_edit_mode)

        et_token_or_key.isEnabled = true
        et_title.isEnabled = true
        et_description.isEnabled = true

        context?.let {
            toolbar.navigationIcon = ContextCompat.getDrawable(it, R.drawable.ic_close_white_24dp)
            toolbar.setNavigationOnClickListener {
                presenter.onCloseClicked()
            }
        }
    }

    override fun showViewsInViewState() {
        toolbar.menu.clear()
        toolbar.inflateMenu(R.menu.details_menu_view_mode)

        et_token_or_key.isEnabled = false
        et_title.isEnabled = false
        et_description.isEnabled = false

        context?.let {
            toolbar.navigationIcon =
                ContextCompat.getDrawable(it, R.drawable.ic_arrow_back_white_24dp)
            toolbar.setNavigationOnClickListener {
                presenter.onBackPressed()
            }
        }
    }

    override fun dialogPositiveClicked(tag: String) {
        super.dialogPositiveClicked(tag)
        presenter.onConfirmYesClicked()
    }

    override fun dialogNegativeClicked(tag: String) {
        super.dialogNegativeClicked(tag)
        presenter.onConfirmNoClicked()
    }

    override fun showConfirmDeleteDialog() {
        childFragmentManager.let {
            val dialog = MessageDialogFragment.create(
                null,
                getString(R.string.common_confirm_message),
                getString(R.string.common_yes),
                getString(R.string.common_no)
            )
            dialog.show(it, CONFIRM_TAG)
        }
    }

    override fun hideConfirmDeleteDialog() {
        childFragmentManager.let {
            (it.findFragmentByTag(CONFIRM_TAG) as MessageDialogFragment).dismiss()
        }
    }
}