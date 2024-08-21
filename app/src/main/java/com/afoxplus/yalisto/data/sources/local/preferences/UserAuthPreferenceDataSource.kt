package com.afoxplus.yalisto.data.sources.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.afoxplus.yalisto.domain.entities.UserAuth
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserAuthPreferenceDataSource @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        const val PREFERENCES_STRING_USER_UUID = "user_uuid"
        const val PREFERENCES_STRING_USER_FCM_TOKEN = "user_fcm_token"
    }

    private val userUUID = stringPreferencesKey(PREFERENCES_STRING_USER_UUID)
    private val userFCMToken = stringPreferencesKey(PREFERENCES_STRING_USER_FCM_TOKEN)

    suspend fun getUserAuth(): UserAuth? {
        return dataStore.data.first()[userUUID]?.let { uuid ->
            UserAuth(
                uuid = uuid,
                fcmToken = dataStore.data.first()[userFCMToken] ?: ""
            )
        }
    }

    suspend fun saveUserAuth(user: UserAuth) {
        dataStore.edit { preferences ->
            preferences[userUUID] = user.uuid
            preferences[userFCMToken] = user.fcmToken
        }
    }

    suspend fun updateFCMToken(fcmToken: String) {
        dataStore.edit { preferences -> preferences[userFCMToken] = fcmToken }
    }
}