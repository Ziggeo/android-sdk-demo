package com.ziggeo.androidsdk.demo.main

import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.ziggeo.androidsdk.demo.R


/**
 * Created by Alexander Bedulin on 02-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
open class AuthScreen : Screen<AuthScreen>() {
    val ivLogo = KImageView { withId(R.id.iv_logo) }
    val tvMessage = KTextView { withId(R.id.tv_auth_message) }
    val btnScanQr = KButton { withId(R.id.btn_scan_qr) }
}
