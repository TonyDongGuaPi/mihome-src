package com.xiaomi.assemble.control;

import android.content.Context;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.xiaomi.mipush.sdk.FCMPushHelper;
import java.util.Map;

public class MiFirebaseMessagingService extends FirebaseMessagingService {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10006a = "FCM-PUSH";

    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i(f10006a, "fcm onMessageReceived");
        if (remoteMessage.getData().size() > 0) {
            Map<String, String> data = remoteMessage.getData();
            Log.i(f10006a, "get fcm data message");
            if (remoteMessage.getNotification() != null) {
                Log.i(f10006a, "get fcm notification with data when app in foreground");
                FCMPushHelper.a((Context) this, data);
                return;
            }
            Log.i(f10006a, "get fcm passThough message");
            FCMPushHelper.b(this, data);
        }
    }

    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.i(f10006a, "onDeletedMessages");
        FCMPushHelper.a();
    }

    public void onMessageSent(String str) {
        Log.i(f10006a, str + "");
        super.onMessageSent(str);
    }

    public void onSendError(String str, Exception exc) {
        Log.i(f10006a, str + " || " + exc.toString());
        super.onSendError(str, exc);
    }
}
