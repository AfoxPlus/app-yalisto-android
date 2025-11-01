package com.afoxplus.yalisto.delivery.splash.delivery.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.afoxplus.uikit.designsystem.atoms.UIKitText


@Composable
fun SplashScreen() {
    Box(modifier = Modifier.safeDrawingPadding()) {
        UIKitText(text = "Hello World")
    }
}