package com.example.numberslight.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class ConnectivityUtils
@Inject constructor(private val context: Context) {

    fun isOnline(): Boolean {
        val cm =
            context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

}