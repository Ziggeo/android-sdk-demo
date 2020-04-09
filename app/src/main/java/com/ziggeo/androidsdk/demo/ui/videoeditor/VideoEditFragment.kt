package com.ziggeo.androidsdk.demo.ui.videoeditor

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.videoeditor.VideoEditPresenter
import com.ziggeo.androidsdk.demo.presentation.videoeditor.VideoEditView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment
import com.ziggeo.androidsdk.utils.FileUtils
import kotlinx.android.synthetic.main.fragment_video_edit.*
import timber.log.Timber


/**
 * Created by Alexander Bedulin on 17-Dec-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class VideoEditFragment : BaseToolbarFragment<VideoEditView, VideoEditPresenter>(),
    VideoEditView {

    @InjectPresenter
    lateinit var presenter: VideoEditPresenter

    override fun getTitleRes(): Int = R.string.title_video_editor
    override val layoutRes: Int = R.layout.fragment_video_edit

    @ProvidePresenter
    override fun providePresenter(): VideoEditPresenter =
        scope.getInstance(VideoEditPresenter::class.java)

    override fun requestReadPermission() {
        Dexter.withActivity(activity)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    btn_select_file.visibility = View.VISIBLE
                    btn_select_file.setOnClickListener { presenter.onSelectFileClicked() }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Toast.makeText(
                        context,
                        R.string.read_storage_permission_message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
            .onSameThread()
            .check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            val path = FileUtils.getPath(context, uri)
            if (path != null) {
                presenter.onFileSelected(path)
            } else {
                Timber.e("Can't get path from uri:%s", uri)
            }
        }
    }

    override fun showFileSelector() {
        val intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(
            Intent.createChooser(intent, getString(R.string.video_chooser_title)),
            0
        )
    }

    override fun showVideoSavedToNotification(path: String) {
        Toast.makeText(
            context, String.format(getString(R.string.cut_video_saved_to), path),
            Toast.LENGTH_SHORT
        ).show()
    }
}