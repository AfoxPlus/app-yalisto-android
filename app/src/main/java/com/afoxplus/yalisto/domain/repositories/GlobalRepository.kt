package com.afoxplus.yalisto.domain.repositories

interface GlobalRepository {
    fun getCurrencyID(): String
    suspend fun isShowedNotificationPermission(): Boolean
    suspend fun showNotificationPermission()
}