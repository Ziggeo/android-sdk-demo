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
import com.ziggeo.androidsdk.widgets.cameraview.BaseCameraView
import kotlinx.android.synthetic.main.fragment_recorder_settings.*


/**
 * Created by Alexander Bedulin on 9-Aug-22.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class RecorderSettingsFragment : BaseToolbarFragment<SettingsView, SettingsPresenter>(),
    SettingsView {
    override val layoutRes = R.layout.fragment_recorder_settings

    private val quality: MutableList<SettingsItem> = ArrayList()
    private val facing: MutableList<SettingsItem> = ArrayList()

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    override fun providePresenter(): SettingsPresenter =
        scope.getInstance(SettingsPresenter::class.java)

    override fun getHeaderTextRes() = R.string.recorder_config_title

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quality.add(
            SettingsItem(
                requireActivity().getString(R.string.video_quality_high),
                BaseCameraView.QUALITY_HIGH
            )
        )

        quality.add(
            SettingsItem(
                requireActivity().getString(R.string.video_quality_medium),
                BaseCameraView.QUALITY_MEDIUM
            )
        )
        quality.add(
            SettingsItem(
                requireActivity().getString(R.string.video_quality_low),
                BaseCameraView.QUALITY_LOW
            )
        )

        val spinnerQualityAdapter = ArrayAdapter(
            requireActivity(),
            R.layout.item_settings,
            quality.map { item -> item.name }
        )
        spinnerQualityAdapter.setDropDownViewResource(R.layout.item_settings)
        spinner_video_quality.adapter = spinnerQualityAdapter

        spinner_video_quality.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                i: Int,
                l: Long
            ) {
                presenter.onVideoQualityChanged(i)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }
        spinner_video_quality.setSelection(presenter.getVideoQuality())

        facing.add(
            SettingsItem(
                requireActivity().getString(R.string.facing_back),
                BaseCameraView.FACING_BACK
            )
        )
        facing.add(
            SettingsItem(
                requireActivity().getString(R.string.facing_front),
                BaseCameraView.FACING_FRONT
            )
        )

        val spinnerFacingAdapter = ArrayAdapter(
            requireActivity(),
            R.layout.item_settings,
            facing.map { item -> item.name }
        )

        spinnerFacingAdapter.setDropDownViewResource(R.layout.item_settings)
        spinner_facing.adapter = spinnerFacingAdapter

        spinner_facing.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                i: Int,
                l: Long
            ) {
                presenter.onFacingChanged(i)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }
        spinner_facing.setSelection(presenter.getFacing())

        et_start_delay.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var startDelayValue = 0
                if (!s.isNullOrEmpty()) {
                    startDelayValue = s.toString().toInt()
                }
                presenter.onStartDelayChanged(startDelayValue)
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

        et_max_duration.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var maxDurationValue = 0
                if (!s.isNullOrEmpty()) {
                    maxDurationValue = s.toString().toInt()
                }
                presenter.onMaxDurationChanged(maxDurationValue.toLong())
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

        et_video_bitrate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var videoBitrateValue = 0
                if (!s.isNullOrEmpty()) {
                    videoBitrateValue = s.toString().toInt()
                }
                presenter.onVideoBitrateChanged(videoBitrateValue)
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

        et_audio_bitrate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var audioBitrateValue = 0
                if (!s.isNullOrEmpty()) {
                    audioBitrateValue = s.toString().toInt()
                }
                presenter.onAudioBitrateChanged(audioBitrateValue)
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

        et_audio_sample_rate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var audioSampleRate = 0
                if (!s.isNullOrEmpty()) {
                    audioSampleRate = s.toString().toInt()
                }
                presenter.onAudioSampleRateChanged(audioSampleRate)
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

        et_title_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var titleTextValue = ""
                if (!s.isNullOrEmpty()) {
                    titleTextValue = s.toString()
                }
                presenter.onTitleTextChanged(titleTextValue)
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

        et_mes_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var mesTextValue = ""
                if (!s.isNullOrEmpty()) {
                    mesTextValue = s.toString()
                }
                presenter.onMesTextChanged(mesTextValue)
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

        et_pos_btn_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var posBtnTextValue = ""
                if (!s.isNullOrEmpty()) {
                    posBtnTextValue = s.toString()
                }
                presenter.onPosBtnTextChanged(posBtnTextValue)
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

        et_neg_btn_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var negBtnTextValue = ""
                if (!s.isNullOrEmpty()) {
                    negBtnTextValue = s.toString()
                }
                presenter.onNegBtnTextChanged(negBtnTextValue)
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

        et_start_delay.setText(presenter.getStartDelay().toString())
        et_max_duration.setText(presenter.getMaxDuration().toString())


        sc_blur_mode.setName(requireActivity().resources.getString(R.string.use_lur_mode))
        sc_blur_mode.setValue(presenter.getBlurMode())
        sc_show_face_outline.setName(requireActivity().resources.getString(R.string.show_face_outline))
        sc_show_face_outline.setValue(presenter.getShouldShowFaceOutline())
        sc_live_streaming.setName(requireActivity().resources.getString(R.string.is_live_streaming))
        sc_live_streaming.setValue(presenter.getIsLiveStreaming())
        sc_autostart_recording.setName(requireActivity().resources.getString(R.string.autostart_recording))
        sc_autostart_recording.setValue(presenter.getShouldAutoStartRecording())
        sc_image_only_mode.setName(requireActivity().resources.getString(R.string.image_only_Mode))
        sc_image_only_mode.setValue(presenter.getIsImageOnlyMode())
        sc_send_immediately.setName(requireActivity().resources.getString(R.string.send_immediately))
        sc_send_immediately.setValue(presenter.getShouldSendImmediately())
        sc_disable_camera_switch.setName(requireActivity().resources.getString(R.string.disable_camera_switch))
        sc_disable_camera_switch.setValue(presenter.getShouldDisableCameraSwitch())
        sc_enable_cover_shot.setName(requireActivity().resources.getString(R.string.enable_cover_shot))
        sc_enable_cover_shot.setValue(presenter.getShouldEnableCoverShot())
        sc_confirm_stop_recording.setName(requireActivity().resources.getString(R.string.confirm_stop_recording))
        sc_confirm_stop_recording.setValue(presenter.getShouldConfirmStopRecording())


        et_video_bitrate.setText(presenter.getVideoBitrate().toString())
        et_audio_bitrate.setText(presenter.getAudioBitrate().toString())
        et_audio_sample_rate.setText(presenter.getAudioSampleRate().toString())
        et_title_text.setText(presenter.getTitleText())
        et_mes_text.setText(presenter.getMesText())
        et_pos_btn_text.setText(presenter.getPosBtnText())
        et_neg_btn_text.setText(presenter.getNegBtnText())

        sc_blur_mode.setOnChangeListener { _, isChecked ->
            presenter.onBlurModeChanged(
                isChecked
            )
        }
        sc_show_face_outline.setOnChangeListener { _, isChecked ->
            presenter.onShouldShowFaceOutlineChanged(
                isChecked
            )
        }

        sc_live_streaming.setOnChangeListener { _, isChecked ->
            presenter.onIsLiveStreamingChanged(
                isChecked
            )
        }

        sc_autostart_recording.setOnChangeListener { _, isChecked ->
            presenter.onShouldAutoStartRecordingChanged(
                isChecked
            )
        }

        sc_image_only_mode.setOnChangeListener { _, isChecked ->
            presenter.onIsImageOnlyModeChanged(
                isChecked
            )
        }

        sc_send_immediately.setOnChangeListener { _, isChecked ->
            presenter.onShouldSendImmediatelyChanged(
                isChecked
            )
        }

        sc_disable_camera_switch.setOnChangeListener { _, isChecked ->
            presenter.onShouldDisableCameraSwitchChanged(
                isChecked
            )
        }

        sc_enable_cover_shot.setOnChangeListener { _, isChecked ->
            presenter.onShouldEnableCoverShotChanged(
                isChecked
            )
        }

        sc_confirm_stop_recording.setOnChangeListener { _, isChecked ->
            presenter.onShouldConfirmStopRecordingChanged(
                isChecked
            )
        }

        btn_save_recorder.setOnClickListener {
            presenter.onSaveClickedRecorder()
        }
    }

    override fun showSavedNotification() {
        Snackbar.make(root, R.string.successfully_saved_message, Snackbar.LENGTH_SHORT).show()
    }
}