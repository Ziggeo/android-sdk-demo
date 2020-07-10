package com.ziggeo.androidsdk.demo.ui.global

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import com.arellomobile.mvp.MvpView
import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.di.module.FragmentModule
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.demo.presentation.global.BasePresenter
import com.ziggeo.androidsdk.demo.ui.log.EventLogger
import toothpick.Scope
import javax.inject.Inject

/**
 * Created by Alexander Bedulin on 30-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
abstract class BaseToolbarFragment<V : MvpView, P : BasePresenter<V>> : BaseScreenFragment<V, P>() {

    @Inject
    lateinit var ziggeo: IZiggeo

    @StringRes
    abstract fun getHeaderTextRes(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTitle()
    }

    private fun initTitle() =
        activity?.findViewById<Toolbar>(R.id.toolbar)?.setTitle(getHeaderTextRes())

    override fun installModules(scope: Scope) {
        super.installModules(scope)
        scope.installModules(
            FragmentModule(
                scope.getInstance(Context::class.java),
                scope.getInstance(Prefs::class.java),
                scope.getInstance(EventLogger::class.java)
            )
        )
    }
}