package com.ziggeo.androidsdk.demo.ui.contactus

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.contactus.ContactUsPresenter
import com.ziggeo.androidsdk.demo.presentation.contactus.ContactUsView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment
import com.ziggeo.androidsdk.demo.util.openUrl
import com.ziggeo.androidsdk.demo.util.sendEmail
import kotlinx.android.synthetic.main.fragment_contact_us.*


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class ContactUsFragment : BaseToolbarFragment<ContactUsView, ContactUsPresenter>(), ContactUsView {
    override val layoutRes = R.layout.fragment_contact_us

    @InjectPresenter
    lateinit var presenter: ContactUsPresenter

    @ProvidePresenter
    override fun providePresenter(): ContactUsPresenter =
        scope.getInstance(ContactUsPresenter::class.java)

    override fun getHeaderTextRes() = R.string.contact_header

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_visit_support.setOnClickListener {
            presenter.onVisitSupportClicked()
        }
        btn_contact_us.setOnClickListener {
            presenter.onContactUsClicked()
        }
    }

    override fun openSupportPage() {
        val supportUrl = "https://support.ziggeo.com"
        context?.let {
            openUrl(it, supportUrl)
        }
    }

    override fun sendEmailToZiggeo() {
        val supportEmail = "https://support.ziggeo.com"
        context?.let {
            sendEmail(it, supportEmail)
        }
    }
}