package com.afoxplus.yalisto.delivery.app

import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.afoxplus.yalisto.R
import com.afoxplus.yalisto.cross.constants.GlobalConstants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class YaListoFCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        showNotification(message)
    }

    private fun showNotification(message: RemoteMessage) {
        val notificationManager = getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(this, GlobalConstants.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.icon_notification_scan)
            .setContentText(message.notification?.title)
            .setContentText(message.notification?.body)
            .build()
        notificationManager.notify(1, notification)
    }
}