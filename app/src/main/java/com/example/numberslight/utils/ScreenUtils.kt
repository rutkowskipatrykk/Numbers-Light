package com.example.numberslight.utils

import android.content.Context
import android.content.res.Configuration
import com.example.numberslight.R
import javax.inject.Inject

class ScreenUtils
@Inject constructor(private val context: Context) {

    fun isTablet() = context.resources.getBoolean(R.bool.isTablet)

    fun isTabletLandscapeOrientation() =
        context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE && isTablet()

    fun isTabletPortraitOrientation() =
        context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT && isTablet()

    fun getOrientation() = context.resources.configuration.orientation

}