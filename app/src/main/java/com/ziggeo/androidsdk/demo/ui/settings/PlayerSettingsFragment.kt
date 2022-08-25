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
import kotlinx.android.synthetic.main.fragment_player_settings.*


/**
 * Created by Alexander Bedulin on 9-Aug-22.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class PlayerSettingsFragment : BaseToolbarFragment<SettingsView, SettingsPresenter>(),
    SettingsView {
    override val layoutRes = R.layout.fragment_player_settings

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    override fun providePresenter(): SettingsPresenter =
        scope.getInstance(SettingsPresenter::class.java)

    override fun getHeaderTextRes() = R.string.player_config_title

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_ads_uri.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var adsUriValue = ""
                if (!s.isNullOrEmpty()) {
                    adsUriValue = s.toString()
                }
                presenter.onAdsUriChanged(adsUriValue)
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

        sc_show_subtitles.setName(requireActivity().resources.getString(R.string.show_subtitles))
        sc_show_subtitles.setValue(presenter.getshouldShowSubtitles())
        sc_is_muted.setName(requireActivity().resources.getString(R.string.is_muted))
        sc_is_muted.setValue(presenter.getIsMuted())
        sc_is_caching_enabled.setName(requireActivity().resources.getString(R.string.is_caching_enabled))
        sc_is_caching_enabled.setValue(presenter.getIsCachingEnabled())
        sc_should_preload.setName(requireActivity().resources.getString(R.string.should_preload))
        sc_should_preload.setValue(presenter.getShouldPreload())
        et_ads_uri.setText(presenter.getAdsUri())

        sc_show_subtitles.setOnChangeListener { _, isChecked ->
            presenter.onShouldShowSubtitlesChanged(
                isChecked
            )
        }

        sc_is_muted.setOnChangeListener { _, isChecked ->
            presenter.onIsMutedChanged(
                isChecked
            )
        }

        sc_is_caching_enabled.setOnChangeListener { _, isChecked ->
            presenter.onIsCachingEnabledChanged(
                isChecked
            )
        }

        sc_should_preload.setOnChangeListener { _, isChecked ->
            presenter.onShouldPreloadChanged(
                isChecked
            )
        }

        btn_save_player.setOnClickListener {
            presenter.onSaveClickedPlayer()
        }
    }

    override fun showSavedNotification() {
        Snackbar.make(root, R.string.successfully_saved_message, Snackbar.LENGTH_SHORT).show()
    }
}