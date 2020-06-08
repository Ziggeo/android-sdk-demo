package com.ziggeo.androidsdk.demo.main

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.ziggeo.androidsdk.demo.R


/**
 * Created by Alexander Bedulin on 02-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
open class CameraRecorderScreen : Screen<CameraRecorderScreen>() {
    val btnStartStop = KButton { withId(com.ziggeo.androidsdk.R.id.v_start_stop) }
    val btnSwitchCamera = KButton { withId(R.id.v_switch_camera) }
    val btnPlay = KButton { withId(R.id.v_play) }
    val btnSendAndClose = KButton { withId(R.id.v_send_and_close) }
}
