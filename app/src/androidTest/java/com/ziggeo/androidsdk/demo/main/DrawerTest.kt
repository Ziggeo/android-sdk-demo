package com.ziggeo.androidsdk.demo.main

import android.view.Gravity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.ui.AppActivity
import com.ziggeo.androidsdk.demo.util.nthChildOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class DrawerTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(AppActivity::class.java)

    @Before
    fun navToMainScreen() {
        // make sure prefs has store token before launch
    }

    @Test
    fun testDrawerOpen() {
        // closed by default
        onView(withId(R.id.drawer)).check(matches(isClosed(Gravity.LEFT)))

        // click on toolbar icon
        onView(
            nthChildOf(
                withId(R.id.toolbar),
                0
            )
        ).perform(click())

        // make sure drawer is opened after the click
        onView(withId(R.id.drawer)).check(matches(isOpen(Gravity.LEFT)))
    }

    @Test
    fun checkDrawerUi() {
        onScreen<DrawerScreen> {
            drawer.open(Gravity.LEFT)

            tvRecordings.hasText(R.string.item_recordings)
            tvRecordings.isSelected()
            tvRecordings.isDisplayed()

            tvSettings.hasText(R.string.item_settings)
            tvSettings.isNotSelected()
            tvSettings.isDisplayed()

            tvSdks.hasText(R.string.item_sdks)
            tvSdks.isNotSelected()
            tvSdks.isDisplayed()

            tvClients.hasText(R.string.item_clients)
            tvClients.isNotSelected()
            tvClients.isDisplayed()

            tvContact.hasText(R.string.item_contact)
            tvContact.isNotSelected()
            tvContact.isDisplayed()

            tvAbout.hasText(R.string.item_about)
            tvAbout.isNotSelected()
            tvAbout.isDisplayed()

            tvAppTokenTitle.hasText(R.string.title_app_token)
            tvAppToken.hasAnyText()

            ivLogout.isDisplayed()
        }
    }

}
