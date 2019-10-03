package com.ziggeo.androidsdk.demo.di.module

import android.content.Context
import com.ziggeo.androidsdk.Ziggeo
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import toothpick.config.Module


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class FragmentModule(context: Context, prefs: Prefs) : Module() {

    init {
        bind(Ziggeo::class.java).toInstance(Ziggeo(prefs.appToken!!, context))
    }
}