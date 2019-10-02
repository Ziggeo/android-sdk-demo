package com.ziggeo.androidsdk.demo.ui.global

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import com.ziggeo.androidsdk.demo.R

/**
 * Created by Alexander Bedulin on 30-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
abstract class BaseToolbarFragment : BaseFragment() {

    @StringRes
    abstract fun getTitleRes(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTitle()
    }

    private fun initTitle() = activity?.findViewById<Toolbar>(R.id.toolbar)?.setTitle(getTitleRes())
}