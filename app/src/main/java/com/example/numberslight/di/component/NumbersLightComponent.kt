package com.example.numberslight.di.component

import android.app.Application
import com.example.numberslight.NumbersLightApplication
import com.example.numberslight.di.ActivityModule
import com.example.numberslight.di.FragmentModule
import com.example.numberslight.di.module.AppModule
import com.example.numberslight.di.module.ServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ServiceModule::class,
        AppModule::class
    ]
)
interface NumbersLightComponent {

    fun inject(application: NumbersLightApplication)

    @Component.Builder
    interface Builder {

        fun build(): NumbersLightComponent

        @BindsInstance
        fun applicationBind(application: Application) : Builder

    }

}