package com.afoxplus.yalisto.di

import com.afoxplus.navigation.entry.NavigationEntry
import com.afoxplus.yalisto.delivery.splash.delivery.navigation.SplashNavigationEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface YaListoNavigationModule {

    @IntoSet
    @Binds
    fun bindSplashNavigationEntry(entry: SplashNavigationEntry): NavigationEntry
}