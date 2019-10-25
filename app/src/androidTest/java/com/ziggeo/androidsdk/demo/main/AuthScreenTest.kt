package com.ziggeo.androidsdk.demo.main

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.NoActivityResumedException
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.di.DI
import com.ziggeo.androidsdk.demo.di.module.AppModule
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.demo.ui.AppActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import toothpick.Toothpick


@RunWith(AndroidJUnit4ClassRunner::class)
class AuthScreenTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(AppActivity::class.java)

    @Before
    fun before() {
        // clear prefs before launch. This will allow to stay on AuthScreen
        val application = ApplicationProvider.getApplicationContext<Application>()
        val scope = Toothpick.openScope(DI.APP_SCOPE)
        scope.installModules(AppModule(application))
        scope.getInstance(Prefs::class.java).appToken = null
    }

    @Test
    fun testScreenContent() {
        onScreen<AuthScreen> {
            // is logo visible and has correct image
            ivLogo.isDisplayed()
            ivLogo.hasDrawable(R.drawable.ic_ziggeo_logo)

            // is message visible and has correct text
            tvMessage.isDisplayed()
            tvMessage.hasText(R.string.auth_message)

            // is button visible, has correct text and enabled
            btnScanQr.isDisplayed()
            btnScanQr.isEnabled()
            btnScanQr.isClickable()
            btnScanQr.hasText(R.string.btn_scan_qr_text)
        }
    }

    @Test(expected = NoActivityResumedException::class)
    fun testBackPress() {
        onScreen<AuthScreen> {
            pressBack()
        }
    }

}
