package com.afoxplus.yalisto.delivery.app

import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.afoxplus.uikit.designsystem.foundations.UIKitIconTheme
import com.afoxplus.yalisto.cross.constants.GlobalConstants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class YaListoFCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        showNotification(message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //update server
    }

    private fun showNotification(message: RemoteMessage) {
        val notificationManager = getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(this, GlobalConstants.NOTIFICATION_CHANNEL_ID)
            .setContentText(message.notification?.title)
            .setContentText(message.notification?.body)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(1, notification)
    }
}