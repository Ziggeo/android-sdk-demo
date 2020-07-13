package com.ziggeo.androidsdk.demo.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

/**
 * Created by Alexander Bedulin on 08-Jun-20.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
fun openUrl(context: Context, url: String) {
    val uri: Uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(context, intent, null)
}