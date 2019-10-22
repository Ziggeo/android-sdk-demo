package com.ziggeo.androidsdk.demo.ui

import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.di.DI
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageType
import com.ziggeo.androidsdk.demo.presentation.AppLauncher
import com.ziggeo.androidsdk.demo.ui.global.BaseFragment
import com.ziggeo.androidsdk.demo.ui.global.MessageDialogFragment
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class AppActivity : MvpAppCompatActivity() {
    @Inject
    lateinit var appLauncher: AppLauncher

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    @Inject
    lateinit var systemMessageNotifier: SystemMessageNotifier

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator =
        SupportAppNavigator(this, supportFragmentManager, R.id.container)

    private var notifierDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, Toothpick.openScope(DI.APP_SCOPE))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)

        if (savedInstanceState == null) {
            appLauncher.coldStart()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        subscribeOnSystemMessages()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        unsubscribeOnSystemMessages()
        super.onPause()
    }

    private fun showAlertMessage(message: String) {
        MessageDialogFragment.create(
            message = message
        ).show(supportFragmentManager, null)
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    private fun subscribeOnSystemMessages() {
        notifierDisposable = systemMessageNotifier.notifier
            .subscribe { msg ->
                when (msg.type) {
                    SystemMessageType.COMMON_ERROR -> showAlertMessage(getString(R.string.common_error))
                    SystemMessageType.ALERT -> showAlertMessage(msg.text)
                    SystemMessageType.TOAST -> showToastMessage(msg.text)
                }
            }
    }

    private fun unsubscribeOnSystemMessages() {
        notifierDisposable?.dispose()
    }
}