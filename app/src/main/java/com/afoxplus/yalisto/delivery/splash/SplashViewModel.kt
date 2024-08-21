package com.afoxplus.yalisto.delivery.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalisto.domain.repositories.GlobalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val globalRepository: GlobalRepository,
    private val uiKitCoroutineDispatcher: UIKitCoroutineDispatcher
) :
    ViewModel() {

    private val mNotificationPermissionState: MutableSharedFlow<PermissionViewState> by lazy { MutableSharedFlow() }
    val notificationPermissionState = mNotificationPermissionState.asSharedFlow()

    fun verifyNotificationPermission() {
        viewModelScope.launch(uiKitCoroutineDispatcher.getMainDispatcher()) {
            //Added delay to splash
            delay(1000)
            val notificationPermissionResult = globalRepository.isShowedNotificationPermission()
            if (notificationPermissionResult) mNotificationPermissionState.emit(PermissionViewState.ShowedPermission)
            else mNotificationPermissionState.emit(PermissionViewState.ShowPermissin)
        }
    }

    fun showNotificationPermission() {
        viewModelScope.launch(uiKitCoroutineDispatcher.getMainDispatcher()) {
            globalRepository.showNotificationPermission()
        }
    }

    sealed interface PermissionViewState {
        object ShowedPermission : PermissionViewState
        object ShowPermissin : PermissionViewState
    }
}