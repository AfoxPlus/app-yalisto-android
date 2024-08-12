package com.afoxplus.yalisto.delivery.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.afoxplus.home.delivery.flow.HomeFlow
import com.afoxplus.yalisto.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var homeFlow: HomeFlow

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                delay(1500)
                homeFlow.goToHome(this@SplashActivity)
                finish()
            }
        }
    }
}