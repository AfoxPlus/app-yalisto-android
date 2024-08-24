package com.afoxplus.yalisto.data.repositories

import com.afoxplus.yalisto.data.sources.local.preferences.UserAuthPreferenceDataSource
import com.afoxplus.yalisto.domain.entities.UserAuth
import com.afoxplus.yalisto.domain.repositories.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class AuthDataRepository @Inject constructor(private val userAuthDataSource: UserAuthPreferenceDataSource) :
    AuthRepository {
    override suspend fun updateFCMToken(newToken: String) {
        userAuthDataSource.updateFCMToken(newToken)
    }

    override suspend fun getUserAuth(): UserAuth {
        var userAuth = userAuthDataSource.getUserAuth()
        if (userAuth == null) {
            val uuid = UUID.randomUUID().toString()
            val fcmToken = Firebase.messaging.token.await()
            userAuth = UserAuth(uuid, fcmToken)
            userAuthDataSource.saveUserAuth(user = userAuth)
        }
        return userAuth
    }
}