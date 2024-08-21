package com.afoxplus.yalisto.data.sources.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.afoxplus.yalisto.domain.entities.UserAuth
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserAuthPreferenceDataSource @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        const val PREFERENCES_STRING_USER_UUID = "user_uuid"
        const val PREFERENCES_STRING_USER_FCM_TOKEN = "user_fcm_token"
        const val PREFERENCES_BOOLEAN_SHOW_NOTIFICATION = "user_show_notification"
    }

    private val userUUID = stringPreferencesKey(PREFERENCES_STRING_USER_UUID)
    private val userFCMToken = stringPreferencesKey(PREFERENCES_STRING_USER_FCM_TOKEN)
    private val userShowNotificationPermission =
        booleanPreferencesKey(PREFERENCES_BOOLEAN_SHOW_NOTIFICATION)

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

    suspend fun showNotificationPermission() {
        dataStore.edit { preferences -> preferences[userShowNotificationPermission] = true }
    }

    suspend fun isShowedNotificationPermission(): Boolean {
        return dataStore.data.first()[userShowNotificationPermission] ?: false
    }
}