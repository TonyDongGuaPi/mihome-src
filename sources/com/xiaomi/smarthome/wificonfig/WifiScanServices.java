package com.xiaomi.smarthome.wificonfig;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.miio.activity.HomeLogContants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class WifiScanServices extends Service {
    public static final String ACTION_HOME_LOG_EVENT = "home_log_event";
    public static final int MSG_REPORT_WIFI_CHANGED = 2;
    public static final int MSG_SCAN_TIME_1 = 10000;
    public static final int MSG_SCAN_TIME_2 = 30000;
    public static final int MSG_SCAN_TIME_3 = 2000;
    public static final int MSG_SCAN_WIFI = 1;
    public static final String TAG = "WifiScanServices";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static String f22945a = "shedule_timer";
    /* access modifiers changed from: private */
    public static int b = 10000;
    /* access modifiers changed from: private */
    public static int c = 600000;
    /* access modifiers changed from: private */
    public static boolean h = true;
    private static WifiScanServices k;
    /* access modifiers changed from: private */
    public WifiManager d;
    /* access modifiers changed from: private */
    public PowerManager e;
    private SmartHomeDeviceManager f = SmartHomeDeviceManager.a();
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public PendingIntent i;
    /* access modifiers changed from: private */
    public AlarmManager j;
    private List<WIFIScanMonitor> l = new ArrayList();
    private BroadcastReceiver m = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!action.equals("android.net.wifi.SCAN_RESULTS")) {
                if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                    if (WifiScanServices.this.d.getWifiState() == 3) {
                        try {
                            WifiScanServices.this.j.cancel(WifiScanServices.this.i);
                            WifiScanServices.this.j.set(0, System.currentTimeMillis() + 5000, WifiScanServices.this.i);
                        } catch (Exception unused) {
                        }
                    }
                } else if (action.equals("android.intent.action.USER_PRESENT")) {
                    WifiScanServices.this.j.cancel(WifiScanServices.this.i);
                    WifiScanServices.this.j.set(0, System.currentTimeMillis() + 5000, WifiScanServices.this.i);
                } else if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                    if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                        if (WifiScanServices.this.mCurrentSSID != null) {
                            WifiScanServices.this.mHandler.sendEmptyMessageDelayed(2, 300000);
                        }
                    } else if (WifiScanServices.this.mHandler.hasMessages(2)) {
                        WifiScanServices.this.mHandler.removeMessages(2);
                    } else {
                        String ssid = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getSSID();
                        try {
                            if (new JSONArray(SHConfig.a().c(HomeLogContants.f11749a)).length() < 2) {
                                SHConfig.a().a(HomeLogContants.l, System.currentTimeMillis());
                            }
                        } catch (JSONException unused2) {
                        }
                        if (WifiScanServices.this.mCurrentSSID == null || !WifiScanServices.this.mCurrentSSID.equalsIgnoreCase(ssid)) {
                            WifiScanServices.this.mCurrentSSID = ssid;
                        }
                    }
                } else if (action.equals(WifiScanServices.f22945a)) {
                    if (!WifiScanServices.this.d.isWifiEnabled()) {
                        if (WifiScanServices.this.g) {
                            SHConfig.a().a(0, "wifi_show_disable");
                        }
                        boolean unused3 = WifiScanServices.this.g = false;
                    } else {
                        if (!WifiScanServices.this.g) {
                            SHConfig.a().a(0, "wifi_show_disable");
                        }
                        boolean unused4 = WifiScanServices.this.g = true;
                        if (WifiScanServices.this.a()) {
                            WifiScanServices.this.d.startScan();
                        }
                    }
                    WifiScanServices.this.j.set(0, System.currentTimeMillis() + ((long) WifiScanServices.c), WifiScanServices.this.i);
                }
            }
        }
    };
    String mCurrentSSID = "";
    Handler mHandler = new ScanHandler();
    String mLastSSID = "";

    public static class MyReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
        }
    }

    public interface WIFIScanMonitor {
        void a();

        void a(Context context);

        void b();

        boolean c();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void setScanTimePeriod(int i2) {
        b = i2;
        Log.e("scan2", "setScanTimePeriod " + i2);
    }

    public static boolean isScan() {
        return h;
    }

    public static void stopScan() {
        h = false;
        if (k != null) {
            k.removeScanWifi();
        }
    }

    public static void startScan() {
        h = true;
        if (k != null) {
            k.restartScanWifi();
        }
    }

    /* access modifiers changed from: package-private */
    public void restartScanWifi() {
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, 300);
    }

    /* access modifiers changed from: package-private */
    public void removeScanWifi() {
        this.mHandler.removeMessages(1);
    }

    private static class ScanHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<WifiScanServices> f22947a;

        private ScanHandler(WifiScanServices wifiScanServices) {
            this.f22947a = new WeakReference<>(wifiScanServices);
        }

        public void handleMessage(Message message) {
            WifiScanServices wifiScanServices = (WifiScanServices) this.f22947a.get();
            if (wifiScanServices != null) {
                switch (message.what) {
                    case 1:
                        if (wifiScanServices.d.isWifiEnabled() && wifiScanServices.e.isScreenOn() && WifiScanServices.h) {
                            wifiScanServices.d.startScan();
                        }
                        if (!wifiScanServices.d.isWifiEnabled()) {
                            if (wifiScanServices.g) {
                                SHConfig.a().a(0, "wifi_show_disable");
                            }
                            boolean unused = wifiScanServices.g = false;
                        } else {
                            if (!wifiScanServices.g) {
                                SHConfig.a().a(0, "wifi_show_disable");
                            }
                            boolean unused2 = wifiScanServices.g = true;
                        }
                        removeMessages(1);
                        sendEmptyMessageDelayed(1, (long) WifiScanServices.b);
                        Log.e("scan2", "MSG_SCAN_WIFI");
                        return;
                    case 2:
                        removeMessages(2);
                        wifiScanServices.mCurrentSSID = null;
                        DeviceApi.getInstance().netChange(SHApplication.getAppContext(), wifiScanServices.mLastSSID, wifiScanServices.mCurrentSSID, new AsyncCallback<Void, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Void voidR) {
                            }

                            public void onFailure(Error error) {
                            }
                        });
                        wifiScanServices.mLastSSID = "";
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        this.i = PendingIntent.getBroadcast(this, 10000, new Intent(f22945a), 1073741824);
        k = this;
        this.e = (PowerManager) getSystemService(CameraPropertyBase.l);
        this.d = (WifiManager) getSystemService("wifi");
        this.g = this.d.isWifiEnabled();
        WifiInfo connectionInfo = this.d.getConnectionInfo();
        if (connectionInfo != null) {
            this.mCurrentSSID = connectionInfo.getSSID();
        }
        registerReceiver(this.m, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
        registerReceiver(this.m, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        registerReceiver(this.m, new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"));
        registerReceiver(this.m, new IntentFilter("android.intent.action.USER_PRESENT"));
        registerReceiver(this.m, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        registerReceiver(this.m, new IntentFilter(f22945a));
        try {
            int i2 = Settings.System.getInt(getContentResolver(), "wifi_sleep_policy", 0);
            Settings.System.putInt(getContentResolver(), "wifi_sleep_policy", 2);
            SHConfig.a().a("wifi_sleelp_policy", i2);
        } catch (Exception unused) {
        }
        this.l.add(new WifiDeviceFinder());
        this.l.add(new WifiScanHomelog());
        for (WIFIScanMonitor next : this.l) {
            next.a(this);
            next.a();
        }
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        if (intent != null && TextUtils.equals("home_log_event", intent.getAction())) {
            WifiScanHomelog.a(intent);
        }
        Log.e("aaaa", "Service Start");
        return 1;
    }

    public void onStart(Intent intent, int i2) {
        super.onStart(intent, i2);
        ((AlarmManager) getSystemService("alarm")).set(0, System.currentTimeMillis() + 1000, PendingIntent.getBroadcast(this, 10000, new Intent(f22945a), 1073741824));
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.m);
        this.mHandler.removeMessages(1);
        try {
            this.j.cancel(this.i);
        } catch (Exception unused) {
        }
        for (WIFIScanMonitor b2 : this.l) {
            b2.b();
        }
        this.l.clear();
        k = null;
    }

    /* access modifiers changed from: private */
    public boolean a() {
        for (WIFIScanMonitor c2 : this.l) {
            if (c2.c()) {
                return true;
            }
        }
        return false;
    }
}
