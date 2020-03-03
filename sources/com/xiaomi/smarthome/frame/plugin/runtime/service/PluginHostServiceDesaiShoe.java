package com.xiaomi.smarthome.frame.plugin.runtime.service;

import android.os.Handler;
import android.os.Looper;

public class PluginHostServiceDesaiShoe extends PluginHostService {
    public void onCreate() {
        super.onCreate();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                PluginHostServiceDesaiShoe.this.stopSelf();
                System.exit(0);
            }
        }, 28800000);
    }
}
