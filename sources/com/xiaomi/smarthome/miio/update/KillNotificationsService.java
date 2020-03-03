package com.xiaomi.smarthome.miio.update;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class KillNotificationsService extends Service {
    String TAG = getClass().getSimpleName();

    /* renamed from: a  reason: collision with root package name */
    private final IBinder f19973a = new KillBinder(this);

    public class KillBinder extends Binder {
        public final Service service;

        public KillBinder(Service service2) {
            this.service = service2;
        }
    }

    public IBinder onBind(Intent intent) {
        return this.f19973a;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Log.d(this.TAG, "-----------------onStartCommand--------------------------------");
        return 1;
    }

    public void onCreate() {
        Log.d(this.TAG, "-----------------onCreate--------------------------------");
    }

    public void onTaskRemoved(Intent intent) {
        Log.d(this.TAG, "-----------------onTaskRemoved--------------------------------");
        AppUpdateManger.a().k();
        AppUpdateManger.a().l();
    }

    public void onDestroy() {
        Log.d(this.TAG, "-----------------onDestroy--------------------------------");
        AppUpdateManger.a().k();
        AppUpdateManger.a().l();
        super.onDestroy();
    }
}
