package com.ziggeo.androidsdk.demo.main

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.ziggeo.androidsdk.demo.ui.AppActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber

@RunWith(AndroidJUnit4ClassRunner::class)
class MainScreenTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(AppActivity::class.java)

    @Test
    fun dumbTest() {
        Timber.e("testScreenContent")
    }
}
