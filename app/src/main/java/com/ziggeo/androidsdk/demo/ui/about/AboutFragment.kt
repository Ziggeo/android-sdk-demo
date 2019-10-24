package com.ziggeo.androidsdk.demo.ui.about

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.about.AboutPresenter
import com.ziggeo.androidsdk.demo.presentation.about.AboutView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment
import kotlinx.android.synthetic.main.fragment_about.*


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class AboutFragment : BaseToolbarFragment<AboutView, AboutPresenter>(), AboutView {
    override val layoutRes = R.layout.fragment_about

    @InjectPresenter
    lateinit var presenter: AboutPresenter

    @ProvidePresenter
    override fun providePresenter(): AboutPresenter =
        scope.getInstance(AboutPresenter::class.java)

    override fun getTitleRes() = R.string.title_about

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_text.text = fromHtml(getString(R.string.about_text).replace("\n","<br>"))
        tv_text.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun fromHtml(html: String?): Spanned {
//        return when {
//            html == null -> SpannableString("")
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> Html.fromHtml(
//                html,
//                Html.FROM_HTML_MODE_LEGACY
//            )
//            else ->
           return     Html.fromHtml(html)
//        }
    }
}