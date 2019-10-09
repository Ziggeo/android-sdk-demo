package com.ziggeo.androidsdk.demo.util

import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


/**
 * Created by Alexander Bedulin on 09-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("with $childPosition child view of type parentMatcher")
        }

        override fun matchesSafely(view: View?): Boolean {
            if (view?.parent !is ViewGroup) {
                return parentMatcher.matches(view?.parent)
            }

            val group = view.parent as ViewGroup
            return parentMatcher.matches(view.parent) && group.getChildAt(childPosition) == view
        }

    }
}