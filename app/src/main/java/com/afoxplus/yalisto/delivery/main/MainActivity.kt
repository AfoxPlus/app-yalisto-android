package com.afoxplus.yalisto.delivery.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.afoxplus.navigation.graph.ApplicationNavGraph
import com.afoxplus.navigation.handler.NavigationHandler
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            UIKitTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ApplicationNavGraph(navigationHandler = navigationHandler)
                }
            }
        }
    }
}