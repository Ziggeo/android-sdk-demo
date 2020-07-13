package com.ziggeo.androidsdk.demo.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.ziggeo.androidsdk.demo.R
import java.io.File


/**
 * Created by Alexander Bedulin on 10-Jul-20.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class EmailSender(private val context: Context) {
    fun sendEmailToSupport(
        subject: String? = null,
        file: File? = null
    ) {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("support@ziggeo.com"))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        file?.let {
            val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(
                    context,
                    context.packageName + ".provider",
                    it
                )
            } else {
                Uri.fromFile(it)
            }
            emailIntent.putExtra(Intent.EXTRA_STREAM, uri)
        }
        ContextCompat.startActivity(
            context,
            Intent.createChooser(emailIntent, context.getString(R.string.email_chooser_title))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
            null
        )
    }
}