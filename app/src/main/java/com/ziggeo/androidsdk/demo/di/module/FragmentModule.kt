package com.ziggeo.androidsdk.demo.di.module

import android.content.Context
import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.Ziggeo
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.net.services.streams.IStreamsServiceRx
import com.ziggeo.androidsdk.net.services.videos.IVideosServiceRx
import toothpick.config.Module


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class FragmentModule(context: Context, prefs: Prefs) : Module() {

    init {
        val ziggeo = Ziggeo(prefs.appToken!!, context)
        bind(IZiggeo::class.java).toInstance(ziggeo)
        bind(IVideosServiceRx::class.java).toInstance(ziggeo.apiRx().videos())
        bind(IStreamsServiceRx::class.java).toInstance(ziggeo.apiRx().streams())
    }
}