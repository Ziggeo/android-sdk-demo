package com.ziggeo.androidsdk.demo.main

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
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.demo.ui.AppActivity
import com.ziggeo.androidsdk.demo.util.nthChildOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import toothpick.Toothpick


@RunWith(AndroidJUnit4ClassRunner::class)
class RecordingsTest : BaseTest() {

    @Rule
    @JvmField
    val rule = ActivityTestRule(AppActivity::class.java)

    @Before
    fun before() {
        // make sure prefs has store token before launch
        // this will allow to navigate to the main screen
        val scope = Toothpick.openScope(DI.APP_SCOPE)
        prefs = scope.getInstance(Prefs::class.java)
        prefs.appToken = BuildConfig.APP_TOKEN
    }

    @Test
    fun dumbTest() {

    }

    @Test
    fun testToolbar() {
        // check toolbar title
        onView(
            nthChildOf(
                withId(R.id.toolbar),
                1
            )
        ).check(matches(withText(R.string.title_recordings)))
    }

    @Test
    fun testActionsButton() {
        onScreen<RecordingsScreen> {
            // hidden by default
            btnAudionRecorder.isNotDisplayed()
            btnCameraRecorder.isNotDisplayed()
            btnScreenRecorder.isNotDisplayed()
            btnImageCapturer.isNotDisplayed()

            btnShowActions.click()

            // shown after click
            btnAudionRecorder.isDisplayed()
            btnCameraRecorder.isDisplayed()
            btnScreenRecorder.isDisplayed()
            btnImageCapturer.isDisplayed()

            btnShowActions.click()

            // hidden again
            btnAudionRecorder.isNotDisplayed()
            btnCameraRecorder.isNotDisplayed()
            btnScreenRecorder.isNotDisplayed()
            btnImageCapturer.isNotDisplayed()
        }
    }

    @Test
    fun testListLoading() {
        onScreen<RecordingsScreen> {
            pullToRefreshLayout {
                isDisplayed()
            }
        }
    }

    @Test
    fun testHidingBtnsOnListScroll() {
//        onScreen<RecordingsScreen> {
//            btnShowActions.isDisplayed()
//            rvRecordings.scrollToEnd()
//            btnShowActions.isNotDisplayed()
//        }
    }

    @Test
    fun testList() {
//        onScreen<RecordingsScreen> {
//            rvRecordings {
//                firstChild<RecordingsScreen.NestedItem> {
//                    isDisplayed()
//                    ivIcon.hasDrawable(R.drawable.ic_videocam_white_24dp)
//                    tvVideoToken.hasAnyText()
//                    tvStatus.hasAnyText()
//                    tvDate.hasAnyText()
//                }
//            }
//        }
    }

}
