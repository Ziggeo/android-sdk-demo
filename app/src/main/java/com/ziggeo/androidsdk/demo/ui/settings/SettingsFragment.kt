package com.ziggeo.androidsdk.demo.ui.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.ui.global.BaseFragment

/**
 * Created by Alexander Bedulin on 9-Aug-22.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class SettingsFragment : BaseFragment() {

    override val layoutRes = R.layout.fragment_settings

    fun getHeaderTextRes() = R.string.settings_header

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<Toolbar>(R.id.toolbar)?.setTitle(getHeaderTextRes())

        val bottomNavigationView =
            requireActivity().findViewById<View>(R.id.nav_view) as BottomNavigationView

        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager

        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, CommonSettingsFragment())
            .commit()



        bottomNavigationView.setOnItemSelectedListener { item ->
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.common_item -> {
                    fragmentTransaction.replace(R.id.nav_host_fragment, CommonSettingsFragment())
                        .addToBackStack(null)
                        .commit()
                    return@setOnItemSelectedListener true
                }
                R.id.recorder_item -> {
                    fragmentTransaction.replace(R.id.nav_host_fragment, RecorderSettingsFragment())
                        .addToBackStack(null)
                        .commit()
                    return@setOnItemSelectedListener true
                }
                R.id.player_item -> {
                    fragmentTransaction.replace(R.id.nav_host_fragment, PlayerSettingsFragment())
                        .addToBackStack(null)
                        .commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}