package com.amap.openapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import com.amap.location.common.log.ALLog;
import com.amap.openapi.df;
import java.util.List;

public class dj implements di {

    /* renamed from: a  reason: collision with root package name */
    private WifiManager f4698a;

    public dj(Context context) {
        this.f4698a = (WifiManager) context.getSystemService("wifi");
    }

    @Nullable
    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public List<ScanResult> a() {
        try {
            if (this.f4698a == null) {
                return null;
            }
            return this.f4698a.getScanResults();
        } catch (SecurityException unused) {
            ALLog.d("@_24_3_@", "@_24_3_1_@");
            return null;
        }
    }

    public void a(Context context, final df.a aVar) {
        try {
            context.getApplicationContext().registerReceiver(new BroadcastReceiver() {

                /* renamed from: a  reason: collision with root package name */
                df.a f4699a = aVar;

                public void onReceive(Context context, Intent intent) {
                    if (intent != null && "android.net.wifi.SCAN_RESULTS".equals(intent.getAction()) && this.f4699a != null) {
                        this.f4699a.a();
                    }
                }
            }, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
        } catch (Throwable unused) {
        }
    }

    @RequiresPermission("android.permission.CHANGE_WIFI_STATE")
    public boolean b() {
        try {
            return this.f4698a != null && this.f4698a.startScan();
        } catch (SecurityException unused) {
            ALLog.d("@_24_3_@", "@_24_3_2_@");
            return false;
        } catch (Exception e) {
            ALLog.d("@_24_3_@", "@_24_3_3_@" + e.toString());
            return false;
        }
    }

    public boolean c() {
        try {
            if (this.f4698a == null) {
                return false;
            }
            return this.f4698a.isWifiEnabled();
        } catch (Exception e) {
            ALLog.a("@_24_3_@", "@_24_3_9_@", e);
            return false;
        }
    }
}
