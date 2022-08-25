package com.ziggeo.androidsdk.demo.ui.settings

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.settings.SettingsPresenter
import com.ziggeo.androidsdk.demo.presentation.settings.SettingsView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment
import com.ziggeo.androidsdk.demo.ui.settings.widgets.SettingsItem
import com.ziggeo.androidsdk.utils.FileUtils
import kotlinx.android.synthetic.main.fragment_common_settings.*


/**
 * Created by Alexander Bedulin on 9-Aug-22.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class CommonSettingsFragment : BaseToolbarFragment<SettingsView, SettingsPresenter>(),
    SettingsView {
    override val layoutRes = R.layout.fragment_common_settings

    private val mediaType: MutableList<SettingsItem> = ArrayList()

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    override fun providePresenter(): SettingsPresenter =
        scope.getInstance(SettingsPresenter::class.java)

    override fun getHeaderTextRes() = R.string.settings_header

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaType.add(
            SettingsItem(
                requireActivity().getString(R.string.media_type_video),
                FileUtils.VIDEO
            )
        )

        mediaType.add(
            SettingsItem(
                requireActivity().getString(R.string.media_type_audio),
                FileUtils.AUDIO
            )
        )

        mediaType.add(
            SettingsItem(
                requireActivity().getString(R.string.media_type_image),
                FileUtils.IMAGE
            )
        )

        val spinnerMediaTypeAdapter = ArrayAdapter(
            requireActivity(),
            R.layout.item_settings,
            mediaType.map { item -> item.name }
        )

        spinnerMediaTypeAdapter.setDropDownViewResource(R.layout.item_settings)
        spinner_media_type.adapter = spinnerMediaTypeAdapter

        spinner_media_type.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                i: Int,
                l: Long
            ) {
                presenter.onMediaTypeChanged(i)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }
        spinner_media_type.setSelection(presenter.getMediaType())

        et_max_duration_selector.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var maxDurationSelectorValue = 0
                if (!s.isNullOrEmpty()) {
                    maxDurationSelectorValue = s.toString().toInt()
                }
                presenter.onMaxDurationSelectorChanged(maxDurationSelectorValue.toLong())
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        et_sync_interval.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var syncIntervalValue = 0
                if (!s.isNullOrEmpty()) {
                    syncIntervalValue = s.toString().toInt()
                }
                presenter.onSyncIntervalChanged(syncIntervalValue.toLong())
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        sc_custom_video.setName(requireActivity().resources.getString(R.string.use_custom_player))
        sc_custom_video.setValue(presenter.getCustomVideoMode())
        sc_custom_camera.setName(requireActivity().resources.getString(R.string.use_custom_camera))
        sc_custom_camera.setValue(presenter.getCustomCameraMode())
        sc_should_allow_multiple_selection.setName(requireActivity().resources.getString(R.string.should_allow_multiple_selection))
        sc_should_allow_multiple_selection.setValue(presenter.getShouldAllowMultipleSelection())
        et_max_duration_selector.setText(presenter.getMaxDurationSelector().toString())

        sc_should_close_after_successful_scan.setName(requireActivity().resources.getString(R.string.close_after_successful_scan))
        sc_should_close_after_successful_scan.setValue(presenter
            .getshouldCloseAfterSuccessfulScan())
        sc_turn_off_uploader.setName(requireActivity().resources.getString(R.string.use_custom_player))
        sc_turn_off_uploader.setValue(presenter.getShouldTurnOffUploader())
        sc_use_wifi_only.setName(requireActivity().resources.getString(R.string.use_wifi_only))
        sc_use_wifi_only.setValue(presenter.getShouldUseWifiOnly())
        sc_start_as_foreground.setName(requireActivity().resources.getString(R.string.start_as_foreground))
        sc_start_as_foreground.setValue(presenter.getShouldStartAsForeground())
        sc_custom_video.setName(requireActivity().resources.getString(R.string.use_custom_player))
        sc_custom_video.setValue(presenter.getCustomVideoMode())
        et_sync_interval.setText(presenter.getSyncInterval().toString())

        sc_custom_video.setOnChangeListener { _, isChecked ->
            presenter.onCustomVideoChanged(
                isChecked
            )
        }

        sc_custom_camera.setOnChangeListener { _, isChecked ->
            presenter.onCustomCameraChanged(
                isChecked
            )
        }

        sc_should_allow_multiple_selection.setOnChangeListener { _, isChecked ->
            presenter.onShouldAllowMultipleSelectionChanged(
                isChecked
            )
        }

        sc_should_close_after_successful_scan.setOnChangeListener { _, isChecked ->
            presenter.onShouldCloseAfterSuccessfulScanChanged(
                isChecked
            )
        }

        sc_turn_off_uploader.setOnChangeListener { _, isChecked ->
            presenter.onShouldTurnOffUploaderChanged(
                isChecked
            )
        }

        sc_use_wifi_only.setOnChangeListener { _, isChecked ->
            presenter.onShouldUseWifiOnlyChanged(
                isChecked
            )
        }

        sc_start_as_foreground.setOnChangeListener { _, isChecked ->
            presenter.onShouldStartAsForegroundChanged(
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

