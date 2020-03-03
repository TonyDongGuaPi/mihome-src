package com.xiaomi.smarthome.notificationquickop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.smarthome.SmartHomeMainActivity;

public class AuthExpiredReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Context applicationContext = context.getApplicationContext();
        if (TextUtils.equals("com.xiaomi.smarthome.notification.auth_expired", action)) {
            Intent intent2 = new Intent(applicationContext, SmartHomeMainActivity.class);
            intent2.setFlags(268468224);
            applicationContext.startActivity(intent2);
        }
    }
}
