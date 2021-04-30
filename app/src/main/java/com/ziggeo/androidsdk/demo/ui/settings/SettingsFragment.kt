package com.ziggeo.androidsdk.demo.ui.settings

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.settings.SettingsPresenter
import com.ziggeo.androidsdk.demo.presentation.settings.SettingsView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment
import kotlinx.android.synthetic.main.fragment_settings.*


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class SettingsFragment : BaseToolbarFragment<SettingsView, SettingsPresenter>(), SettingsView {
    override val layoutRes = R.layout.fragment_settings

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    override fun providePresenter(): SettingsPresenter =
        scope.getInstance(SettingsPresenter::class.java)

    override fun getHeaderTextRes() = R.string.settings_header

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        et_start_delay.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var startDelayValue = 0
                if (!s.isNullOrEmpty()) {
                    startDelayValue = s.toString().toInt()
                }
                presenter.onStartDelayChanged(startDelayValue)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        sc_custom_video.isChecked = presenter.getCustomVideoMode()
        sc_custom_camera.isChecked = presenter.getCustomCameraMode()

        sc_custom_video.setOnCheckedChangeListener { _, isChecked ->
            presenter.onCustomVideoChanged(
                isChecked
            )
        }

        sc_custom_camera.setOnCheckedChangeListener { _, isChecked ->
            presenter.onCustomCameraChanged(
                isChecked
            )
        }

        btn_save.setOnClickListener {
            presenter.onSaveClicked()
        }
    }

    override fun showSavedNotification() {
        Snackbar.make(root, R.string.successfully_saved_message, Snackbar.LENGTH_SHORT).show()
    }
}