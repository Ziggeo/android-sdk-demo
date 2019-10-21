package com.ziggeo.androidsdk.demo.di.module

import android.content.Context
import com.ziggeo.androidsdk.demo.model.data.storage.KVStorage
import com.ziggeo.androidsdk.demo.model.data.storage.KVStorageImpl
import com.ziggeo.androidsdk.demo.model.data.storage.Prefs
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class AppModule(context: Context) : Module() {

    init {
        // Global
        bind(Context::class.java).toInstance(context)
        bind(SystemMessageNotifier::class.java).toInstance(SystemMessageNotifier())
        bind(Prefs::class.java).toInstance(Prefs(context))
        bind(KVStorage::class.java).toInstance(KVStorageImpl())

        // Navigation
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}