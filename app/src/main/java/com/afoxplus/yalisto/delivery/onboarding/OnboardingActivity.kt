package com.afoxplus.yalisto.delivery.onboarding

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.afoxplus.home.delivery.flow.HomeFlow
import com.afoxplus.uikit.designsystem.atoms.UIKitText
import com.afoxplus.uikit.designsystem.foundations.UIKitColorTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.yalisto.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity : ComponentActivity() {

    @Inject
    lateinit var homeFlow: HomeFlow

    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIKitTheme {
                val shouldShowDialog by viewModel.alertNotificationPermissionState.collectAsState()
                Surface(color = UIKitColorTheme.light01) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.splash_icon),
                            contentDescription = "YaListo Icon"
                        )
                    }
                    NotificationPermissionAlertDialog(shouldShowDialog = shouldShowDialog)
                }
                LaunchedEffect(key1 = Unit) { askNotificationPermission() }
            }
        }

    }

    @Composable
    fun NotificationPermissionAlertDialog(shouldShowDialog: Boolean) {
        if (shouldShowDialog) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.dismissAlertNotificationPermission()
                    homeFlow.goToHome(this@OnboardingActivity)
                    finish()
                },
                title = { UIKitText(text = stringResource(id = R.string.notification_permission_dialog_title)) },
                text = { UIKitText(text = stringResource(id = R.string.notification_permission_dialog_description)) },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.dismissAlertNotificationPermission()
                            goToNotificationSettings()
                        }
                    ) {
                        UIKitText(
                            text = stringResource(id = R.string.notification_permission_dialog_button),
                            color = UIKitColorTheme.light01
                        )
                    }
                }
            )
        }
    }


    private fun gotoHome() {
        homeFlow.goToHome(this@OnboardingActivity)
        finish()
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            gotoHome()
        } else viewModel.showAlertNotificationPermission()
    }

    private fun goToNotificationSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val settingsIntent: Intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            viewModel.showSettingNotificationPermission()
            startActivity(settingsIntent)
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                gotoHome()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isShowedSettingNotificationPermission()) {
            gotoHome()
        }
    }

    companion object {
        fun newStartActivity(activity: Activity) {
            val intent = Intent(activity, OnboardingActivity::class.java)
            activity.startActivity(intent)
        }
    }

}