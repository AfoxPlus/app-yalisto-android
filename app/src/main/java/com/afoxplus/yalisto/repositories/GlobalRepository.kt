package com.afoxplus.yalisto.repositories

import java.util.UUID
import javax.inject.Inject

interface GlobalRepository {
    fun getUserUUID(): String
    fun getCurrencyID(): String
}

class GlobalRepositorySource @Inject constructor(private val preference: GlobalPreference) :
    GlobalRepository {

    override fun getUserUUID(): String {
        var localUUID = preference.readObjectFromFile<String?>(USER_UUID)
        if (localUUID == null) {
            localUUID = UUID.randomUUID().toString()
            preference.writeObjectToFile(localUUID, USER_UUID)
        }
        return localUUID
    }

    override fun getCurrencyID(): String {
        return "61a18be00b6de1476436de41"
    }

    companion object {
        private const val USER_UUID = "user_uuid"
    }
}