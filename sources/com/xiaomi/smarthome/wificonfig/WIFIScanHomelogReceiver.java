package com.xiaomi.smarthome.wificonfig;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;

public class WIFIScanHomelogReceiver extends BroadcastReceiver {
    public static final String ACTION_HOME_LOG_EVENT = "home_log_event";
    public static final int MSG_SCAN_TIME_1 = 10000;
    public static final int MSG_SCAN_TIME_2 = 30000;
    public static final int MSG_SCAN_TIME_3 = 2000;
    public static final String WIFI_SCAN_SERVICE_CONNECTIVITY_CHANGE_ACTION = "com.xiaomi.smarthome.wifiscanservice.CONNECTIVITY_CHANGE";
    public static final String WIFI_SCAN_SERVICE_SCAN_RESULTS_AVAILABLE_ACTION = "com.xiaomi.smarthome.wifiscanservice.SCAN_RESULTS";
    public static final String WIFI_SCAN_SERVICE_STATE_CHANGE_ACTION = "com.xiaomi.smarthome.wifiscanservice.STATE_CHANGE";
    public static final String WIFI_SCAN_SERVICE_USER_PRESENT_ACTION = "com.xiaomi.smarthome.wifiscanservice.USER_PRESENT";

    public static void onUserPresent() {
    }

    public static void onWifiStateChange() {
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals("com.xiaomi.metok.geofencing.state_change", action)) {
            initWifiScanMonitors();
            String stringExtra = intent.getStringExtra("Location");
            String stringExtra2 = intent.getStringExtra("State");
            Intent intent2 = new Intent();
            intent2.setAction("home_log_event");
            intent2.putExtra(WifiScanHomelog.n, stringExtra);
            intent2.putExtra(WifiScanHomelog.o, stringExtra2);
            WifiScanHomelog.a(intent2);
        } else if (TextUtils.equals("com.xiaomi.smarthome.wifiscanservice.SCAN_RESULTS", action)) {
            initWifiScanMonitors();
            onScanResultAvailable();
        } else if (TextUtils.equals("com.xiaomi.smarthome.wifiscanservice.STATE_CHANGE", action)) {
            initWifiScanMonitors();
            onWifiStateChange();
        } else if (TextUtils.equals("com.xiaomi.smarthome.wifiscanservice.USER_PRESENT", action)) {
            initWifiScanMonitors();
            onUserPresent();
        } else if (TextUtils.equals("com.xiaomi.smarthome.wifiscanservice.CONNECTIVITY_CHANGE", action)) {
            initWifiScanMonitors();
            onConnectivityChange();
        }
    }

    public static void initWifiScanMonitors() {
        if (WifiDeviceFinder.d().e() == null) {
            WifiDeviceFinder.d().a(SHApplication.getAppContext());
            WifiDeviceFinder.d().a();
        }
        if (WifiScanHomelog.d().h() == null) {
            WifiScanHomelog.d().a(SHApplication.getAppContext());
            WifiScanHomelog.d().a();
        }
    }

    public static void onScanResultAvailable() {
        WifiDeviceFinder.d().h();
        WifiScanHomelog.d().k();
    }

    public static void onConnectivityChange() {
        WifiScanHomelog.d().l();
    }
}
