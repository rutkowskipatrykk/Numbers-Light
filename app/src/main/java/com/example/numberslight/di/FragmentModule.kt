package com.example.numberslight.di

import com.example.numberslight.view.fragment.DetailsFragment
import com.example.numberslight.view.fragment.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment
    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): DetailsFragment
}