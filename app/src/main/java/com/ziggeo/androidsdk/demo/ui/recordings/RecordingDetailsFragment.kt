package com.ziggeo.androidsdk.demo.ui.recordings

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.recordings.RecordingDetailsPresenter
import com.ziggeo.androidsdk.demo.presentation.recordings.RecordingDetailsView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment
import com.ziggeo.androidsdk.demo.ui.global.MessageDialogFragment
import com.ziggeo.androidsdk.net.models.ContentModel
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

    override fun getHeaderTextRes(): Int = R.string.details_header

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
        fab_play.setOnClickListener {
            presenter.onPlayClicked()
        }
    }

    override fun showRecordingData(contentModel: ContentModel) {
        et_token_or_key.setText(contentModel.key ?: contentModel.token)
        et_title.setText(contentModel.title)
        et_description.setText(contentModel.description)
    }

    override fun showPreview(url: String, isVideo: Boolean) {
        if (url.isEmpty()) {
            iv_preview.setImageResource(R.drawable.ic_microphone)
            iv_preview.setOnClickListener {
                presenter.onPlayClicked()
            }
        } else {
            Glide.with(iv_preview)
                .load(url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (isVideo) {
                            fab_play.show()
                        } else {
                            iv_preview.setOnClickListener {
                                presenter.onPlayClicked()
                            }
                        }
                        return false
                    }

                })
                .into(iv_preview)
        }
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