package com.afoxplus.yalisto.data.repositories

import com.afoxplus.yalisto.data.sources.local.preferences.UserAuthPreferenceDataSource
import com.afoxplus.yalisto.domain.repositories.GlobalRepository
import javax.inject.Inject

class GlobalDataRepository @Inject constructor(private val userAuthDataSource: UserAuthPreferenceDataSource) :
    GlobalRepository {
    //Get data from service
    override fun getCurrencyID(): String = "61a18be00b6de1476436de41"
    override suspend fun isShowedNotificationPermission(): Boolean {
        return userAuthDataSource.isShowedNotificationPermission()
    }

    override suspend fun showNotificationPermission() {
        this.userAuthDataSource.showNotificationPermission()
    }
}