package com.afoxplus.yalisto.delivery.app

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class YaListoFCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        //respond to received messages
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //update server
    }
}