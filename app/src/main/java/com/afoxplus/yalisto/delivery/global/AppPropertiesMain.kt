package com.afoxplus.yalisto.delivery.global

import com.afoxplus.network.global.AppProperties
import com.afoxplus.yalisto.BuildConfig
import javax.inject.Inject

class AppPropertiesMain @Inject constructor() : AppProperties {
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

    override fun isAppDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}