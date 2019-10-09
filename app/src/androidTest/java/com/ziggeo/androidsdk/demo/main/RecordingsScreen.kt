package com.ziggeo.androidsdk.demo.main

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.ziggeo.androidsdk.demo.R
import org.hamcrest.Matcher


/**
 * Created by Alexander Bedulin on 02-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
open class RecordingsScreen : Screen<RecordingsScreen>() {
    val toolbar = KView { withId(R.id.toolbar) }
    val btnShowActions = KButton { withId(R.id.fab_selector) }
    val btnCameraRecorder = KButton { withId(R.id.fab_camera) }
    val btnScreenRecorder = KButton { withId(R.id.fab_screen) }
    val btnAudionRecorder = KButton { withId(R.id.fab_audio) }
    val btnImageCapturer = KButton { withId(R.id.fab_image) }

    val rvRecordings = KRecyclerView({ withId(R.id.rv_recordings) }, { itemType(::NestedItem) })

    class NestedItem(parent: Matcher<View>) : KRecyclerItem<NestedItem>(parent) {
        val ivIcon = KImageView(parent) { withId(R.id.iv_icon) }
        val tvVideoToken = KTextView(parent) { withId(R.id.tv_video_token) }
        val tvTags = KTextView(parent) { withId(R.id.tv_tags) }
        val tvStatus = KTextView(parent) { withId(R.id.tv_status) }
        val tvDate = KTextView(parent) { withId(R.id.tv_date) }
    }

}
