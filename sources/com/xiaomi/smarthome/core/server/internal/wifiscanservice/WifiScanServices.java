package com.xiaomi.smarthome.core.server.internal.wifiscanservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.core.client.IClientCallback;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.config.SHConfig;
import java.lang.ref.WeakReference;

public class WifiScanServices {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14742a = "WifiScanServices";
    public static final String b = "com.xiaomi.smarthome.wifiscanservice.SCAN_RESULTS";
    public static final String c = "com.xiaomi.smarthome.wifiscanservice.STATE_CHANGE";
    public static final String d = "com.xiaomi.smarthome.wifiscanservice.USER_PRESENT";
    public static final String e = "com.xiaomi.smarthome.wifiscanservice.CONNECTIVITY_CHANGE";
    public static final int f = 1;
    /* access modifiers changed from: private */
    public static int h = 30000;
    /* access modifiers changed from: private */
    public static IClientCallback l = null;
    /* access modifiers changed from: private */
    public static boolean m = false;
    private static WifiScanServices n;
    Handler g = new ScanHandler();
    /* access modifiers changed from: private */
    public WifiManager i;
    /* access modifiers changed from: private */
    public PowerManager j;
    /* access modifiers changed from: private */
    public boolean k = false;
    private BroadcastReceiver o = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (WifiScanServices.l != null) {
                Bundle bundle = new Bundle();
                if (action.equals("android.net.wifi.SCAN_RESULTS") && WifiScanServices.a()) {
                    bundle.putString("result", "com.xiaomi.smarthome.wifiscanservice.SCAN_RESULTS");
                } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                    bundle.putString("result", "com.xiaomi.smarthome.wifiscanservice.STATE_CHANGE");
                } else if (action.equals("android.intent.action.USER_PRESENT")) {
                    bundle.putString("result", "com.xiaomi.smarthome.wifiscanservice.USER_PRESENT");
                } else if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    bundle.putString("result", "com.xiaomi.smarthome.wifiscanservice.CONNECTIVITY_CHANGE");
                }
                if (bundle.containsKey("result")) {
                    try {
                        WifiScanServices.l.onSuccess(bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    public static void a(int i2) {
        h = i2;
    }

    public static boolean a() {
        return m;
    }

    public static void b() {
        m = false;
        if (n != null) {
            n.e();
        }
    }

    public static void c() {
        m = true;
        if (n != null) {
            n.d();
        }
    }

    public static void a(IClientCallback iClientCallback) {
        m = true;
        l = iClientCallback;
        if (n != null) {
            n.d();
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        this.g.removeMessages(1);
        this.g.sendEmptyMessageDelayed(1, 300);
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.g.removeMessages(1);
    }

    private static class ScanHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<WifiScanServices> f14744a;

        private ScanHandler(WifiScanServices wifiScanServices) {
            this.f14744a = new WeakReference<>(wifiScanServices);
        }

        public void handleMessage(Message message) {
            WifiScanServices wifiScanServices = (WifiScanServices) this.f14744a.get();
            if (wifiScanServices != null && message.what == 1) {
                try {
                    if (wifiScanServices.i.isWifiEnabled() && wifiScanServices.j.isScreenOn() && WifiScanServices.m) {
                        wifiScanServices.i.startScan();
                    }
                    if (!wifiScanServices.i.isWifiEnabled()) {
                        if (wifiScanServices.k) {
                            SHConfig.a().a(0, "wifi_show_disable");
                        }
                        boolean unused = wifiScanServices.k = false;
                    } else {
                        if (!wifiScanServices.k) {
                            SHConfig.a().a(0, "wifi_show_disable");
                        }
                        boolean unused2 = wifiScanServices.k = true;
                    }
                    removeMessages(1);
                    sendEmptyMessageDelayed(1, (long) WifiScanServices.h);
                    Log.e("scan2", "MSG_SCAN_WIFI");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Context k() {
        return CoreService.getAppContext();
    }

    public void f() {
        n = this;
        this.j = (PowerManager) k().getSystemService(CameraPropertyBase.l);
        this.i = (WifiManager) k().getSystemService("wifi");
        try {
            this.k = this.i.isWifiEnabled();
        } catch (Exception unused) {
            this.k = false;
        }
        k().registerReceiver(this.o, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
        k().registerReceiver(this.o, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        k().registerReceiver(this.o, new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"));
        k().registerReceiver(this.o, new IntentFilter("android.intent.action.USER_PRESENT"));
        k().registerReceiver(this.o, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        try {
            int i2 = Settings.System.getInt(k().getContentResolver(), "wifi_sleep_policy", 0);
            Settings.System.putInt(k().getContentResolver(), "wifi_sleep_policy", 2);
            SHConfig.a().a("wifi_sleelp_policy", (long) i2);
            if (m) {
                d();
            }
        } catch (Exception unused2) {
        }
    }

    public void g() {
        k().unregisterReceiver(this.o);
        this.g.removeMessages(1);
        n = null;
    }
}
