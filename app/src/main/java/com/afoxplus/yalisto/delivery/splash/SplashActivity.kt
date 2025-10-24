package com.afoxplus.yalisto.delivery.splash

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.afoxplus.home.delivery.flow.HomeFlow
import com.afoxplus.yalisto.databinding.ActivitySplashBinding
import com.afoxplus.yalisto.delivery.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var homeFlow: HomeFlow

    private lateinit var binding: ActivitySplashBinding
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        splashViewModel.verifyNotificationPermission()
        setContentView(binding.root)
        collectNotification()
    }

    private fun collectNotification() = lifecycleScope.launch {
        repeatOnLifecycle(state = Lifecycle.State.CREATED) {
            splashViewModel.notificationPermissionState.collectLatest { state ->
                when (state) {
                    SplashViewModel.PermissionViewState.ShowedPermission -> gotoHome()
                    SplashViewModel.PermissionViewState.ShowPermissin -> askNotificationPermission()
                }
            }
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                gotoHome()
            } else {
                gotoOnboarding()
            }
        } else gotoHome()
        splashViewModel.showNotificationPermission()
    }

    private fun gotoHome() {
        homeFlow.goToHome(this)
        finish()
    }

    private fun gotoOnboarding() {
        OnboardingActivity.newStartActivity(this)
        finish()
    }

}