package com.ziggeo.androidsdk.demo.util

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.widget.TextView
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Replace

/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
fun Navigator.setLaunchScreen(screen: SupportAppScreen) {
    applyCommands(
        arrayOf(
            BackTo(null),
            Replace(screen)
        )
    )
}

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"

fun String.Companion.fromHtml(html: String?): Spanned {
    return when {
        html == null -> SpannableString("")
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> Html.fromHtml(
            html,
            Html.FROM_HTML_MODE_LEGACY
        )
        else -> return Html.fromHtml(html)
    }
}