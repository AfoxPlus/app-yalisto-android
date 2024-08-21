package com.afoxplus.yalisto.delivery.global

import com.afoxplus.network.global.AppProperties
import com.afoxplus.yalisto.BuildConfig
import com.afoxplus.yalisto.domain.repositories.AuthRepository
import com.afoxplus.yalisto.domain.repositories.GlobalRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AppPropertiesMain @Inject constructor(
    private val globalRepository: GlobalRepository,
    private val authRepository: AuthRepository
) : AppProperties {
    override fun getCurrencyID(): String = globalRepository.getCurrencyID()

    override fun getDeviceData(): String {
        var deviceData = ""
        try {
            val manufacturer = android.os.Build.MANUFACTURER
            val model = android.os.Build.MODEL
            val myVersion = android.os.Build.VERSION.RELEASE
            val osVersion = android.os.Build.VERSION.SDK

            deviceData = String.format(
                "%s|%s|%s|%s", manufacturer, model,
                myVersion, osVersion
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return deviceData
    }

    override fun getUserUUID(): String {
        var uuid: String
        runBlocking {
            uuid = authRepository.getUserAuth().uuid
        }
        return uuid
    }

    override fun isAppDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}