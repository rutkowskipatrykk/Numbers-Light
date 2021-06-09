package com.example.numberslight.di.module

import android.app.Application
import android.content.Context
import com.example.numberslight.utils.ConnectivityUtils
import com.example.numberslight.utils.ScreenUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun bindConnectivityUtils(context: Context) = ConnectivityUtils(context)

    @Provides
    @Singleton
    fun bindScreenUtils(context: Context) = ScreenUtils(context)

    @Provides
    @Singleton
    fun provideContext(application: Application) = application.applicationContext

}