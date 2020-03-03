package com.xiaomi.smarthome.notishortcut;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;

public class NotiSmartService extends Service {

    /* renamed from: a  reason: collision with root package name */
    private static Context f1561a;
    private static Handler c;
    private SmartNoti b = new SmartNoti();

    public static Context getAppContext() {
        return f1561a;
    }

    public static Handler getHandler() {
        return c;
    }

    public void onCreate() {
        super.onCreate();
        f1561a = this;
        if (c == null) {
            c = new Handler(Looper.getMainLooper());
        }
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return this.b;
    }
}
