package com.xiaomi.smarthome.frame.login.util;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.youpin.login.api.manager.LocalPhoneDataCache;

public class LoginEventUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16346a = "action.passwordlogin.login.complete";
    public static final String b = "login_success";
    public static final String c = "token_expired";

    public static void a(Context context, boolean z) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent(f16346a);
        intent.putExtra("login_success", z);
        instance.sendBroadcast(intent);
    }

    public static void a(Context context) {
        LocalPhoneDataCache.a().c();
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(c));
    }
}
