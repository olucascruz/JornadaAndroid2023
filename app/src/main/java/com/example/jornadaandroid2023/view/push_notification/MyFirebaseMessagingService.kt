package com.example.jornadaandroid2023.view.push_notification

import android.util.Log
import com.example.jornadaandroid2023.view.push_notification.PushNotificationManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    companion object {
        const val CHANNEL_ID = "DEFAULT"
    }

    override fun onNewToken(token: String) {
        Log.d("FIREBASE MESSAGING", "new token $token")

    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("FIREBASE MESSAGING", "messagem recieved $message")
        val title = message.notification?.title
        val body = message.notification?.body

        if (title == null || body == null) {
            return
        }

        // Se quiser pegar os dados extras, usar: `message.data`

        PushNotificationManager.sendPushNotication(this, title, body)
    }

}