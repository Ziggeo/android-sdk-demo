package com.ziggeo.androidsdk.demo.main

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.ziggeo.androidsdk.demo.R


/**
 * Created by Alexander Bedulin on 02-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
open class RecordingDetailsScreen : Screen<RecordingDetailsScreen>() {
    val tvToolbarTitle = KView { withId(R.id.toolbar) }

    val btnBack = KButton { withId(R.id.toolbar) }
    val btnEdit = KButton { withId(R.id.toolbar) }
    val btnDelete = KButton { withId(R.id.toolbar) }
    val btnClose = KButton { withId(R.id.toolbar) }
    val btnSave = KButton { withId(R.id.toolbar) }

    val ivPreview = KView { withId(R.id.toolbar) }
    val tvTokenOrKey = KView { withId(R.id.toolbar) }
    val tvTitle = KView { withId(R.id.toolbar) }
    val tvDescription = KView { withId(R.id.toolbar) }

}
