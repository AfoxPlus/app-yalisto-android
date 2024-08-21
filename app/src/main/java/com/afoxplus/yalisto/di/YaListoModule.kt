package com.afoxplus.yalisto.di

import com.afoxplus.network.global.AppProperties
import com.afoxplus.yalisto.data.repositories.AuthDataRepository
import com.afoxplus.yalisto.data.repositories.GlobalDataRepository
import com.afoxplus.yalisto.delivery.global.AppPropertiesMain
import com.afoxplus.yalisto.domain.repositories.AuthRepository
import com.afoxplus.yalisto.domain.repositories.GlobalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface YaListoModule {
    @Binds
    fun bindGlobalRepository(globalRepository: GlobalDataRepository): GlobalRepository

    @Binds
    fun bindAuthDataRepository(authRepository: AuthDataRepository): AuthRepository

    @Binds
    fun bindAppProperties(properties: AppPropertiesMain): AppProperties
}