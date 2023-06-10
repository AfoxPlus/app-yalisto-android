package com.afoxplus.yalisto.di

import com.afoxplus.network.global.AppProperties
import com.afoxplus.yalisto.delivery.global.AppPropertiesMain
import com.afoxplus.yalisto.repositories.GlobalRepository
import com.afoxplus.yalisto.repositories.GlobalRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface YaListoModule {

    @Binds
    fun bindAppProperties(properties: AppPropertiesMain): AppProperties

    @Binds
    fun bindGlobalRepository(globalRepository: GlobalRepositorySource): GlobalRepository
}