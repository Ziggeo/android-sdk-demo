package com.ziggeo.androidsdk.demo.main

import androidx.test.espresso.NoActivityResumedException
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.ziggeo.androidsdk.demo.ui.AppActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class AuthScreenTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(AppActivity::class.java)

    @Test
    fun testScreenContent() {
        onScreen<AuthScreen> {
            // is logo visible and has correct image
            ivLogo.isDisplayed()
            ivLogo.hasDrawable(com.ziggeo.androidsdk.demo.R.drawable.ic_ziggeo_logo)

            // is message visible and has correct text
            tvMessage.isDisplayed()
            tvMessage.hasText(com.ziggeo.androidsdk.demo.R.string.auth_message)

            // is button visible, has correct text and enabled
            btnScanQr.isDisplayed()
            btnScanQr.isEnabled()
            btnScanQr.isClickable()
            btnScanQr.hasText(com.ziggeo.androidsdk.demo.R.string.btn_scan_qr_text)
        }
    }

    @Test(expected = NoActivityResumedException::class)
    fun testBackPress() {
        onScreen<AuthScreen> {
            pressBack()
        }
    }

}
