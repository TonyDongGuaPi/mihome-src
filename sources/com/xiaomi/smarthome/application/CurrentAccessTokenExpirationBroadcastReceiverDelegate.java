package com.xiaomi.smarthome.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.AccessTokenManager;
import com.facebook.FacebookSdk;
import com.xiaomi.jr.common.utils.ReflectUtil;

public class CurrentAccessTokenExpirationBroadcastReceiverDelegate extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (FacebookSdk.isInitialized() && AccessTokenManager.ACTION_CURRENT_ACCESS_TOKEN_CHANGED.equals(intent.getAction())) {
            try {
                ReflectUtil.a((Class<?>) AccessTokenManager.class, "currentAccessTokenChanged", (Class<?>[]) new Class[0]).invoke(ReflectUtil.a((Class<?>) AccessTokenManager.class, "getInstance", (Class<?>[]) new Class[0]).invoke((Object) null, new Object[0]), new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
