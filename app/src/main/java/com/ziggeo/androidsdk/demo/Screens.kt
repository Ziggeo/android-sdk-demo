package com.ziggeo.androidsdk.demo

import com.ziggeo.androidsdk.demo.ui.about.AboutFragment
import com.ziggeo.androidsdk.demo.ui.auth.AuthFlowFragment
import com.ziggeo.androidsdk.demo.ui.auth.AuthFragment
import com.ziggeo.androidsdk.demo.ui.contactus.ContactUsFragment
import com.ziggeo.androidsdk.demo.ui.drawer.DrawerFlowFragment
import com.ziggeo.androidsdk.demo.ui.recordings.RecordingDetailsFragment
import com.ziggeo.androidsdk.demo.ui.recordings.RecordingsFragment
import com.ziggeo.androidsdk.demo.ui.sdks.AvailableSDKsFragment
import com.ziggeo.androidsdk.demo.ui.settings.SettingsFragment
import com.ziggeo.androidsdk.demo.ui.topcustomers.TopCustomersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
object Screens {

    // Flows
    object AuthFlow : SupportAppScreen() {
        override fun getFragment() = AuthFlowFragment()
    }

    object DrawerFlow : SupportAppScreen() {
        override fun getFragment() = DrawerFlowFragment()
    }

    // Screens
    object Auth : SupportAppScreen() {
        override fun getFragment() = AuthFragment()
    }

    object Recordings : SupportAppScreen() {
        override fun getFragment() = RecordingsFragment()
    }

    object RecordingDetails : SupportAppScreen() {
        override fun getFragment() = RecordingDetailsFragment()
    }

    object AvailableSdks : SupportAppScreen() {
        override fun getFragment() = AvailableSDKsFragment()
    }

    object TopCustomers : SupportAppScreen() {
        override fun getFragment() = TopCustomersFragment()
    }

    object ContactUs : SupportAppScreen() {
        override fun getFragment() = ContactUsFragment()
    }

    object Settings : SupportAppScreen() {
        override fun getFragment() = SettingsFragment()
    }

    object About : SupportAppScreen() {
        override fun getFragment() = AboutFragment()
    }
}