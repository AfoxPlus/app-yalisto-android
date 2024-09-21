package com.afoxplus.yalisto.di

import com.afoxplus.yalisto.domain.repositories.AuthRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface YaListoEntryPoints {
    fun getAuthRepository(): AuthRepository
}