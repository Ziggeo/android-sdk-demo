package com.ziggeo.androidsdk.demo.main

import com.agoda.kakao.drawer.KDrawerView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.ziggeo.androidsdk.demo.R


/**
 * Created by Alexander Bedulin on 02-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
open class DrawerScreen : Screen<DrawerScreen>() {

    val drawer = KDrawerView { withId(R.id.drawer) }

    val tvAppTokenTitle = KTextView { withId(R.id.tv_app_token_title) }
    val tvAppToken = KTextView { withId(R.id.tv_app_token) }
    val ivLogout = KImageView { withId(R.id.iv_logout) }
    val tvRecordings = KTextView { withId(R.id.mi_recordings) }
    val tvSettings = KTextView { withId(R.id.mi_settings) }
    val tvSdks = KTextView { withId(R.id.mi_sdks) }
    val tvClients = KTextView { withId(R.id.mi_clients) }
    val tvContact = KTextView { withId(R.id.mi_contact) }
    val tvAbout = KTextView { withId(R.id.mi_about) }

}