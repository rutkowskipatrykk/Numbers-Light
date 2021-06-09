package com.example.numberslight.di

import com.example.numberslight.view.activity.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface MainActivitySubComponent: AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<MainActivity>

}
