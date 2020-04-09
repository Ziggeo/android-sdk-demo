package com.ziggeo.androidsdk.demo.main

import android.os.SystemClock
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.ziggeo.androidsdk.demo.recorders.CameraFullscreenRecorderActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CameraRecorderTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(CameraFullscreenRecorderActivity::class.java)

    @get:Rule
    var permissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @Test
    fun testRecorder() {
        onScreen<DemoScreen> {
            btnStartRecorder.click()
        }
        SystemClock.sleep(1000)
        onScreen<CameraRecorderScreen> {
            btnStartStop.isVisible()
            btnStartStop.click()
            SystemClock.sleep(5000)
            btnSendAndClose.click()
        }
    }
}
