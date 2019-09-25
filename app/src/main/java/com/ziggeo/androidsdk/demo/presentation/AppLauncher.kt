package com.ziggeo.androidsdk.demo.presentation

import com.ziggeo.androidsdk.demo.Screens
import com.ziggeo.androidsdk.demo.model.interactor.LaunchInteractor
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class AppLauncher @Inject constructor(
    private val launchInteractor: LaunchInteractor,
    private val router: Router
) {

    fun coldStart() {
        val rootScreen =
            if (launchInteractor.hasAccount) Screens.DrawerFlow
            else Screens.AuthFlow

        router.newRootScreen(rootScreen)
    }
}