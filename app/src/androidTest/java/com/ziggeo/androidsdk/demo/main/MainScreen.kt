package com.ziggeo.androidsdk.demo.main

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.ziggeo.androidsdk.demo.R


/**
 * Created by Alexander Bedulin on 02-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
open class MainScreen : Screen<MainScreen>() {
    val toolbar = KView { withId(R.id.toolbar) }

    val mainContainer = KView { withId(R.id.main_container) }

    val tvAppToken = KView { withId(R.id.tv_app_token) }

    val miRecording = KView { withId(R.id.mi_recordings) }
    val miSettings = KView { withId(R.id.mi_settings) }
    val miSdks = KView { withId(R.id.mi_sdks) }
    val miClients = KView { withId(R.id.mi_clients) }
    val miContact = KView { withId(R.id.mi_contact) }
    val miAbout = KView { withId(R.id.mi_about) }
    val ivLogout = KView { withId(R.id.iv_logout) }
}
