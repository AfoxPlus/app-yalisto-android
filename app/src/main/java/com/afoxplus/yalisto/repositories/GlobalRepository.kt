package com.afoxplus.yalisto.repositories

import java.util.UUID
import javax.inject.Inject

interface GlobalRepository {
    fun getUserUUID(): String
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

    companion object {
        private const val USER_UUID = "user_uuid"
    }
}