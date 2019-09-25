package com.ziggeo.androidsdk.demo.di.module

import com.ziggeo.androidsdk.demo.model.system.flow.FlowRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class FlowNavigationModule(globalRouter: Router) : Module() {
    init {
        val cicerone = Cicerone.create(
            FlowRouter(
                globalRouter
            )
        )
        bind(FlowRouter::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}