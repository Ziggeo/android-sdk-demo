package com.ziggeo.androidsdk.demo.ui.contactus

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.presentation.contactus.ContactUsPresenter
import com.ziggeo.androidsdk.demo.presentation.contactus.ContactUsView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment
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

    override fun getTitleRes() = R.string.title_contact

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_start_now.setOnClickListener {
            presenter.onStartNowClicked()
        }
    }

}