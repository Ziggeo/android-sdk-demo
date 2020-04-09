package com.ziggeo.androidsdk.demo.main

import android.app.Application
import android.os.SystemClock
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.ziggeo.androidsdk.demo.BuildConfig
import com.ziggeo.androidsdk.demo.di.DI
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.demo.ui.AppActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import toothpick.Toothpick


@RunWith(AndroidJUnit4ClassRunner::class)
class CameraRecorderTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(AppActivity::class.java)

    @get:Rule
    var permissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @Before
    fun before() {
        // make sure prefs has store token before launch
        // this will allow to navigate to the main screen
        val application = ApplicationProvider.getApplicationContext<Application>()
        val scope = Toothpick.openScope(DI.APP_SCOPE)
        scope.getInstance(Prefs::class.java).appToken = BuildConfig.APP_TOKEN
    }

    @Test
    fun testRecorder() {
        onScreen<RecordingsScreen> {
            btnShowActions.click()
            btnCameraRecorder.click()
        }
        SystemClock.sleep(1000)
        onScreen<CameraRecorderScreen> {
            btnStartStop.isVisible()
            btnStartStop.click()
            SystemClock.sleep(2000)
            btnSendAndClose.click()
        }
    }
}
