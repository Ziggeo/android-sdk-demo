package com.ziggeo.androidsdk.demo.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.ziggeo.androidsdk.demo.R
import timber.log.Timber


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

fun sendEmail(context: Context, to: String, subject: String? = null, body: String? = null) {
    val i = Intent(Intent.ACTION_SENDTO)
    i.data = Uri.parse("mailto:$to");
    i.putExtra(Intent.EXTRA_SUBJECT, subject)
    i.putExtra(Intent.EXTRA_TEXT, body)
    try {
        startActivity(
            context,
            Intent.createChooser(i, context.getString(R.string.email_chooser_title)),
            null
        )
    } catch (ex: ActivityNotFoundException) {
        Timber.e(ex)
    }
}