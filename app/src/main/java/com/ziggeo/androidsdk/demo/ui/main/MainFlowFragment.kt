package com.ziggeo.androidsdk.demo.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.Screens
import com.ziggeo.androidsdk.demo.presentation.main.MainFlowPresenter
import com.ziggeo.androidsdk.demo.presentation.main.MainFlowView
import com.ziggeo.androidsdk.demo.presentation.main.MainFlowView.MenuItem.*
import com.ziggeo.androidsdk.demo.ui.global.BaseFlowFragment
import com.ziggeo.androidsdk.demo.ui.global.BaseFragment
import com.ziggeo.androidsdk.demo.ui.global.MessageDialogFragment
import kotlinx.android.synthetic.main.fragment_drawer_flow.*
import kotlinx.android.synthetic.main.fragment_nav_drawer.*


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class MainFlowFragment : BaseFlowFragment(), MainFlowView,
    MessageDialogFragment.OnClickListener {

    override val layoutRes = R.layout.fragment_drawer_flow

    @InjectPresenter
    lateinit var presenter: MainFlowPresenter

    @ProvidePresenter
    fun providePresenter(): MainFlowPresenter =
        scope.getInstance(MainFlowPresenter::class.java)

    private val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.main_container) as? BaseFragment

    private val itemClickListener = { view: View ->
        val menuItem = view.tag as MainFlowView.MenuItem
        presenter.onMenuItemClick(menuItem)
        selectMenuItem(menuItem)
    }

    override fun getContainerId() = R.id.main_container

    override fun getLaunchScreen() = Screens.Recordings

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolbar()
        initDrawerMenu()
    }

    private fun openNavDrawer(open: Boolean) {
        if (open) drawer.openDrawer(GravityCompat.START)
        else drawer.closeDrawer(GravityCompat.START)
    }

    override fun showAccName(appToken: String?) {
        tv_app_token.text = appToken
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            openNavDrawer(false)
        } else {
            currentFragment?.onBackPressed() ?: router.exit()
        }
    }

    override fun openDrawer() {
        openNavDrawer(true)
    }

    override fun closeDrawer() {
        openNavDrawer(false)
    }

    override fun openAuthScreen() {
        router.newRootFlow(Screens.AuthFlow)
    }

    override fun openRecordingsScreen() {
        router.newRootScreen(Screens.Recordings)
    }

    override fun openSettingsScreen() {
        router.newRootScreen(Screens.Settings)
    }

    override fun openSdksScreen() {
        router.newRootScreen(Screens.AvailableSdks)
    }

    override fun openClientsScreen() {
        router.newRootScreen(Screens.TopClients)
    }

    override fun openContactUsScreen() {
        router.newRootScreen(Screens.ContactUs)
    }

    override fun openAboutScreen() {
        router.newRootScreen(Screens.About)
    }

    override fun openVideoEditorScreen() {
        router.newRootScreen(Screens.VideoEditor)
    }

    override fun selectMenuItem(item: MainFlowView.MenuItem) {
        (0 until drawer_menu_container.childCount)
            .map { drawer_menu_container.getChildAt(it) }
            .forEach { menuItem -> menuItem.tag?.let { menuItem.isSelected = it == item } }
    }

    override fun dialogPositiveClicked(tag: String) {
        when (tag) {
            CONFIRM_LOGOUT_TAG -> presenter.onLogoutClick()
        }
    }

    private fun initToolbar() {
        with(toolbar) {
            setNavigationOnClickListener { openNavDrawer(true) }
        }
    }

    private fun initDrawerMenu() {
        iv_logout.setOnClickListener {
            MessageDialogFragment.create(
                message = getString(R.string.logout_message),
                positive = getString(R.string.common_yes),
                negative = getString(R.string.common_no),
                tag = CONFIRM_LOGOUT_TAG
            ).show(childFragmentManager, CONFIRM_LOGOUT_TAG)
        }

        mi_recordings.tag = RECORDINGS
        mi_settings.tag = SETTINGS
        mi_video_editor.tag = VIDEO_EDITOR
        mi_sdks.tag = SDKS
        mi_clients.tag = CLIENTS
        mi_contact.tag = CONTACT_US
        mi_about.tag = ABOUT

        mi_recordings.setOnClickListener(itemClickListener)
        mi_video_editor.setOnClickListener(itemClickListener)
        mi_settings.setOnClickListener(itemClickListener)
        mi_sdks.setOnClickListener(itemClickListener)
        mi_clients.setOnClickListener(itemClickListener)
        mi_contact.setOnClickListener(itemClickListener)
        mi_about.setOnClickListener(itemClickListener)

        selectMenuItem(RECORDINGS)
    }

    private companion object {
        private const val CONFIRM_LOGOUT_TAG = "confirm_logout_tag"
    }
}