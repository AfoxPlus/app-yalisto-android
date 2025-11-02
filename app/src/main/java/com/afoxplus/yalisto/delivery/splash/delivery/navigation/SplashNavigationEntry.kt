package com.afoxplus.yalisto.delivery.splash.delivery.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.afoxplus.navigation.entry.NavigationEntry
import com.afoxplus.navigation.routers.SplashRoute
import com.afoxplus.yalisto.delivery.splash.delivery.screens.SplashScreen
import javax.inject.Inject

class SplashNavigationEntry @Inject constructor() : NavigationEntry {
    override fun NavGraphBuilder.register(navController: NavController) {
        composable<SplashRoute> {
            SplashScreen()
        }
    }
}