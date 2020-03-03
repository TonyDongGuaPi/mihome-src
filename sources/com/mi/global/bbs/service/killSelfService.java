package com.mi.global.bbs.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class killSelfService extends Service {
    private static long stopDelayed = 1000;
    /* access modifiers changed from: private */
    public String PackageName;
    private Handler handler = new Handler();

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        stopDelayed = intent.getLongExtra("Delayed", 1000);
        this.PackageName = intent.getStringExtra("PackageName");
        this.handler.postDelayed(new Runnable() {
            public void run() {
                killSelfService.this.startActivity(killSelfService.this.getPackageManager().getLaunchIntentForPackage(killSelfService.this.PackageName));
                killSelfService.this.stopSelf();
            }
        }, stopDelayed);
        return super.onStartCommand(intent, i, i2);
    }
}
