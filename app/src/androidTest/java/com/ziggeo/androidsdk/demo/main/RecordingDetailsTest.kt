package com.ziggeo.androidsdk.demo.main

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.ziggeo.androidsdk.demo.BuildConfig
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.di.DI
import com.ziggeo.androidsdk.demo.di.module.AppModule
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.demo.ui.AppActivity
import com.ziggeo.androidsdk.demo.util.nthChildOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import toothpick.Toothpick


@RunWith(AndroidJUnit4ClassRunner::class)
class RecordingDetailsTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(AppActivity::class.java)

    @Before
    fun before() {
        // make sure prefs has store token before launch
        // this will allow to navigate to the main screen
        val application = ApplicationProvider.getApplicationContext<Application>()
        val scope = Toothpick.openScope(DI.APP_SCOPE)
        scope.installModules(AppModule(application))
        scope.getInstance(Prefs::class.java).appToken = BuildConfig.APP_TOKEN
    }

    @Test
    fun testToolbar() {
        // check toolbar title
        onView(
            nthChildOf(
                withId(R.id.toolbar),
                1
            )
        ).check(matches(withText(R.string.title_details)))
    }


}
