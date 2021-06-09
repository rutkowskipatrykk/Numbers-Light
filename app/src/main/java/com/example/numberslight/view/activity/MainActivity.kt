package com.example.numberslight.view.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.numberslight.R
import com.example.numberslight.databinding.ActivityMainBinding
import com.example.numberslight.utils.ConnectivityUtils
import com.example.numberslight.utils.ScreenUtils
import com.example.numberslight.view.fragment.DetailsFragment
import com.example.numberslight.view.fragment.ListFragment
import com.example.numberslight.view.listener.ConnectionBackListener
import com.example.numberslight.view.viewmodel.MainActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectivityUtils: ConnectivityUtils

    @Inject
    lateinit var screenUtils: ScreenUtils

    lateinit var binding: ActivityMainBinding
    private var lastStatusAvailability: Boolean = true
    private val viewModel by viewModels<MainActivityViewModel>()
    private val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    private val br: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            context?.let {
                val currentInternetStatus = connectivityUtils.isOnline()
                if (currentInternetStatus && !lastStatusAvailability) {
                    supportFragmentManager.fragments.forEach {
                        if (it is ConnectionBackListener) {
                            it.onConnectionBack()
                        }
                    }
                }
                viewModel.setErrorStatus(connectivityUtils.isOnline())
                lastStatusAvailability = currentInternetStatus
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        registerReceiver(br, filter)
        setBinding()

        if (savedInstanceState == null && !screenUtils.isTabletLandscapeOrientation()) {
            replaceFragment(ListFragment())
        } else if (savedInstanceState != null && screenUtils.isTabletPortraitOrientation()) {
            replaceFragment(ListFragment(), true)
        }

        viewModel.selectedItem.observe(this, {
            it.getContentIfNotHandled()?.let {
                if (!screenUtils.isTabletLandscapeOrientation()) {
                    replaceFragment(DetailsFragment())
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0 ||
            screenUtils.isTabletLandscapeOrientation() ||
            !connectivityUtils.isOnline()
        ) {
            finish()
        }
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun replaceFragment(fragment: Fragment, clearBackStack: Boolean = false) {
        if (clearBackStack) {
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                supportFragmentManager.popBackStack()
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(
                fragment.javaClass.toString()
            ).commit()
    }

}
