package com.afoxplus.yalisto.delivery.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {

    private val mAlertNotificationPermissionState: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow(false)
    }
    private val mSettingNotificationPermissionState: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow(false)
    }
    val alertNotificationPermissionState = mAlertNotificationPermissionState.asStateFlow()

    fun showAlertNotificationPermission() {
        mAlertNotificationPermissionState.value = true
    }

    fun dismissAlertNotificationPermission() {
        mAlertNotificationPermissionState.value = false
    }

    fun showSettingNotificationPermission() {
        mSettingNotificationPermissionState.value = true
    }

    fun isShowedSettingNotificationPermission(): Boolean {
        return mSettingNotificationPermissionState.value
    }

}