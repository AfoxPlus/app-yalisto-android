package com.afoxplus.yalisto.domain.repositories

import com.afoxplus.yalisto.domain.entities.UserAuth

interface AuthRepository {
    suspend fun updateFCMToken(newToken: String)
    suspend fun getUserAuth(): UserAuth
}