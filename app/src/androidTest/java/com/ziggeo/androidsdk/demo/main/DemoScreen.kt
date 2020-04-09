package com.ziggeo.androidsdk.demo.main

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.ziggeo.demo.R


/**
 * Created by Alexander Bedulin on 02-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
open class DemoScreen : Screen<DemoScreen>() {
    val btnStartRecorder = KButton { withId(R.id.btn_start_recorder) }
}
