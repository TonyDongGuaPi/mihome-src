package com.xiaomi.smarthome.wificonfig;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.config.SHConfigPhone;
import com.xiaomi.smarthome.config.WifiConnectionConfig;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.miio.activity.HomeLogContants;
import com.xiaomi.smarthome.miio.activity.HomeLogWifiSetting;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.wificonfig.WifiLogManager;
import com.xiaomi.smarthome.wificonfig.WifiScanServices;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WifiScanHomelog implements WifiScanServices.WIFIScanMonitor {
    private static WifiScanHomelog H = null;
    private static final int J = 10;

    /* renamed from: a  reason: collision with root package name */
    public static final String f22934a = "WifiScanHomelog";
    public static final String b = "reset_wifi_location";
    public static final String c = "update_remote_wifi_log";
    public static final String d = "wifi_home_predict";
    public static final String e = "wifi_office_predict";
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    public static final int i = 4;
    public static final int j = 6;
    public static final String n = "home_log_event_location";
    public static final String o = "home_log_event_state";
    public static final String p = "Unknown_Place";
    public static final String q = "Leave";
    public static final String r = "Back";
    private Map<String, String> A = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public String B;
    private int C = 0;
    private Context D;
    /* access modifiers changed from: private */
    public int E = 0;
    private WIFIScanHomelogViaMIUI F;
    private SceneManager.IScenceListener G = new SceneManager.IScenceListener() {
        public void onRefreshScenceSuccess(int i) {
            List<SceneApi.SmartHomeScene> list;
            WifiScanHomelog.this.c("Get Scene Sucess");
            int unused = WifiScanHomelog.this.E = 0;
            if (WifiScanHomelog.this.v.size() != 0) {
                while (!WifiScanHomelog.this.v.isEmpty()) {
                    StartSceneInfo startSceneInfo = (StartSceneInfo) WifiScanHomelog.this.v.poll();
                    if (System.currentTimeMillis() - startSceneInfo.c <= 300000) {
                        if (startSceneInfo.f22917a.equalsIgnoreCase("home")) {
                            list = SceneManager.x().s();
                        } else {
                            if (startSceneInfo.f22917a.equalsIgnoreCase(HomeLogContants.j) || startSceneInfo.f22917a.equalsIgnoreCase("office")) {
                                if (startSceneInfo.b == null || startSceneInfo.b.equals("home")) {
                                    list = SceneManager.x().r();
                                }
                            } else if (startSceneInfo.f22917a.equalsIgnoreCase(SmartHomeSceneUtility.c)) {
                                list = SceneManager.x().b(startSceneInfo.b, SmartHomeSceneUtility.c);
                            } else if (startSceneInfo.f22917a.equalsIgnoreCase(SmartHomeSceneUtility.d)) {
                                list = SceneManager.x().b(startSceneInfo.b, SmartHomeSceneUtility.d);
                            } else if (startSceneInfo.f22917a.equalsIgnoreCase(SmartHomeSceneUtility.f21464a)) {
                                list = SceneManager.x().b(startSceneInfo.b, SmartHomeSceneUtility.f21464a);
                            } else if (startSceneInfo.f22917a.equalsIgnoreCase(SmartHomeSceneUtility.b)) {
                                list = SceneManager.x().b(startSceneInfo.b, SmartHomeSceneUtility.b);
                            }
                            list = null;
                        }
                        if (list != null) {
                            WifiScanHomelog.this.c("Start Scene " + startSceneInfo.f22917a + ", " + list.size());
                            for (SceneApi.SmartHomeScene next : list) {
                                if (next.n) {
                                    String str = null;
                                    for (SceneApi.Condition next2 : next.l) {
                                        if (next2.d != null) {
                                            str = next2.d.f21528a;
                                        } else if (next2.f != null) {
                                            str = next2.f.l;
                                        }
                                        RemoteSceneApi.a().a(SHApplication.getAppContext(), next.f, str, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                            /* renamed from: a */
                                            public void onSuccess(Void voidR) {
                                            }

                                            public void onFailure(Error error) {
                                            }
                                        });
                                    }
                                    if (!(next.x == null || next.x.e == null || next.x.e.size() <= 0)) {
                                        for (SceneApi.Condition next3 : next.x.e) {
                                            if (next3.d != null && next3.l) {
                                                RemoteSceneApi.a().a(SHApplication.getAppContext(), next.f, next3.d.f21528a, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                                    /* renamed from: a */
                                                    public void onSuccess(Void voidR) {
                                                    }

                                                    public void onFailure(Error error) {
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            return;
                        }
                    }
                }
            }
        }

        public void onRefreshScenceFailed(int i) {
            WifiScanHomelog.this.c("Get Scene Failed");
            WifiScanHomelog.b(WifiScanHomelog.this);
            if (WifiScanHomelog.this.E > 3) {
                int unused = WifiScanHomelog.this.E = 0;
            } else {
                WifiScanHomelog.this.k.sendEmptyMessageDelayed(4, 5000);
            }
        }
    };
    private BroadcastReceiver I = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!action.equals("android.net.wifi.SCAN_RESULTS")) {
                if (action.equals(WifiScanHomelog.b)) {
                    WifiScanHomelog.this.m.sendEmptyMessage(2);
                    String unused = WifiScanHomelog.this.B = null;
                } else if (action.equals(WifiScanHomelog.c)) {
                    WifiConnectionConfig.a().b();
                    SHConfig.a().b();
                    WifiLogManager.a().a(true);
                }
            }
        }
    };
    private WifiLogManager.IListener K = new WifiLogManager.IListener() {
        public void a() {
            WifiScanHomelog.this.m.sendEmptyMessage(3);
        }
    };
    Handler k = new ScanHandler();
    MessageHandlerThread l;
    Handler m;
    /* access modifiers changed from: private */
    public WifiManager s;
    private ConnectivityManager t;
    private boolean u = false;
    /* access modifiers changed from: private */
    public ConcurrentLinkedQueue<StartSceneInfo> v = new ConcurrentLinkedQueue<>();
    /* access modifiers changed from: private */
    public Map<String, Integer> w = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<String, Integer> x = new ConcurrentHashMap();
    private Map<String, String> y = new ConcurrentHashMap();
    private Map<String, ScanResult> z = new ConcurrentHashMap();

    static /* synthetic */ int b(WifiScanHomelog wifiScanHomelog) {
        int i2 = wifiScanHomelog.E;
        wifiScanHomelog.E = i2 + 1;
        return i2;
    }

    public static WifiScanHomelog d() {
        if (H == null) {
            H = new WifiScanHomelog();
        }
        return H;
    }

    private static class ScanHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<WifiScanHomelog> f22941a;

        private ScanHandler(WifiScanHomelog wifiScanHomelog) {
            this.f22941a = new WeakReference<>(wifiScanHomelog);
        }

        public void handleMessage(Message message) {
            WifiScanHomelog wifiScanHomelog = (WifiScanHomelog) this.f22941a.get();
            if (wifiScanHomelog != null) {
                int i = message.what;
                if (i != 4) {
                    if (i == 6) {
                        JSONArray jSONArray = new JSONArray();
                        for (Map.Entry entry : wifiScanHomelog.w.entrySet()) {
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put(DeviceTagInterface.e, entry.getKey());
                                jSONObject.put("times", entry.getValue());
                                jSONArray.put(jSONObject);
                            } catch (JSONException unused) {
                            }
                        }
                        SHConfigPhone.a().a(WifiScanHomelog.d, jSONArray.toString());
                        JSONArray jSONArray2 = new JSONArray();
                        for (Map.Entry entry2 : wifiScanHomelog.x.entrySet()) {
                            try {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put(DeviceTagInterface.e, entry2.getKey());
                                jSONObject2.put("times", entry2.getValue());
                                jSONArray.put(jSONObject2);
                            } catch (JSONException unused2) {
                            }
                        }
                        SHConfigPhone.a().a(WifiScanHomelog.e, jSONArray2.toString());
                        sendEmptyMessageDelayed(6, 3600000);
                    }
                } else if (message.obj != null) {
                    StartSceneInfo startSceneInfo = (StartSceneInfo) message.obj;
                    SceneManager.x().a(startSceneInfo.c);
                    wifiScanHomelog.v.offer(startSceneInfo);
                }
            }
        }
    }

    private class InternalHandler extends Handler {
        public InternalHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    try {
                        Long valueOf = Long.valueOf(System.currentTimeMillis());
                        WifiScanHomelog.this.b(WifiScanHomelog.this.s.getConnectionInfo().getSSID());
                        WifiScanHomelog.this.j();
                        WifiScanHomelog.this.e();
                        Log.e(WifiScanHomelog.f22934a, "start process end time - " + (System.currentTimeMillis() - valueOf.longValue()));
                        return;
                    } catch (Exception unused) {
                        return;
                    }
                case 2:
                    WifiScanHomelog.this.f();
                    return;
                case 3:
                    WifiInfo connectionInfo = WifiScanHomelog.this.s.getConnectionInfo();
                    WifiScanHomelog.this.f();
                    String unused2 = WifiScanHomelog.this.B = null;
                    WifiScanHomelog.this.b(connectionInfo.getSSID());
                    return;
                default:
                    return;
            }
        }
    }

    public static String a(String str) {
        if (str == null) {
            return "";
        }
        return (str.length() >= 2 && str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') ? str.substring(1, str.length() - 1) : str;
    }

    public static JSONArray a(List<ScanResult> list, String str) {
        int i2;
        ArrayList arrayList = new ArrayList();
        Iterator<ScanResult> it = list.iterator();
        while (true) {
            i2 = 0;
            if (!it.hasNext()) {
                break;
            }
            ScanResult next = it.next();
            if (!next.BSSID.equalsIgnoreCase("00:00:00:00:00:00")) {
                int i3 = 0;
                while (true) {
                    if (i3 >= arrayList.size()) {
                        break;
                    } else if (((Integer) ((Pair) arrayList.get(i3)).first).intValue() >= next.level) {
                        arrayList.add(i3, new Pair(Integer.valueOf(next.level), next.BSSID));
                        i2 = 1;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (i2 == 0 && arrayList.size() < 10) {
                    arrayList.add(new Pair(Integer.valueOf(next.level), next.BSSID));
                }
                if (arrayList.size() > 10) {
                    arrayList.remove(9);
                }
            }
        }
        JSONArray jSONArray = new JSONArray();
        while (i2 < arrayList.size()) {
            jSONArray.put(((Pair) arrayList.get(i2)).second);
            i2++;
        }
        return jSONArray;
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        List<ScanResult> scanResults;
        String a2 = a(str);
        if (!TextUtils.isEmpty(a2) && this.A.containsKey(a2) && (scanResults = this.s.getScanResults()) != null && scanResults.size() > 0) {
            JSONArray a3 = a(scanResults, a2);
            for (ScanResult next : scanResults) {
                if (!next.BSSID.equalsIgnoreCase("00:00:00:00:00:00")) {
                    this.y.put(next.BSSID, this.A.get(a2));
                }
            }
            try {
                JSONArray jSONArray = new JSONArray(SHConfig.a().c(HomeLogContants.f11749a));
                int i2 = 0;
                while (true) {
                    if (i2 >= jSONArray.length()) {
                        break;
                    }
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    if (jSONObject.getString(HomeLogContants.d).equals(this.A.get(a2))) {
                        JSONObject optJSONObject = jSONObject.optJSONObject(HomeLogContants.g);
                        if (optJSONObject == null) {
                            optJSONObject = new JSONObject();
                            jSONObject.put(HomeLogContants.g, optJSONObject);
                        }
                        optJSONObject.put(a2, a3);
                    } else {
                        i2++;
                    }
                }
                SHConfig.a().a(HomeLogContants.f11749a, jSONArray.toString());
            } catch (JSONException unused) {
            }
        }
    }

    public void e() {
        WifiInfo connectionInfo;
        WifiInfo connectionInfo2;
        int hours = new Date(System.currentTimeMillis()).getHours();
        int i2 = 0;
        if (hours >= 2 && hours < 5 && (connectionInfo2 = this.s.getConnectionInfo()) != null && connectionInfo2.getSSID() != null) {
            this.w.put(connectionInfo2.getSSID(), Integer.valueOf((this.w.get(connectionInfo2.getSSID()) == null ? 0 : this.w.get(connectionInfo2.getSSID()).intValue()) + 1));
        }
        if (hours >= 14 && hours < 17 && (connectionInfo = this.s.getConnectionInfo()) != null && connectionInfo.getSSID() != null) {
            if (this.x.get(connectionInfo.getSSID()) != null) {
                i2 = this.x.get(connectionInfo.getSSID()).intValue();
            }
            this.x.put(connectionInfo.getSSID(), Integer.valueOf(i2 + 1));
        }
    }

    public void a() {
        H = this;
        if (this.F == null) {
            this.F = new WIFIScanHomelogViaMIUI();
            this.F.a(this.D);
        }
        this.F.a();
        this.s = (WifiManager) this.D.getSystemService("wifi");
        this.t = (ConnectivityManager) this.D.getSystemService("connectivity");
        this.D.registerReceiver(this.I, new IntentFilter("android.intent.action.USER_PRESENT"));
        SceneManager.x().a(this.G);
        this.D.registerReceiver(this.I, new IntentFilter(b));
        this.D.registerReceiver(this.I, new IntentFilter(c));
        WifiLogManager.a().a(this.K);
        try {
            this.l = new MessageHandlerThread(f22934a);
            this.l.start();
            this.m = new InternalHandler(this.l.getLooper());
            this.m.sendEmptyMessage(2);
            this.k.sendEmptyMessageDelayed(6, 3600000);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void f() {
        this.y.clear();
        String c2 = SHConfig.a().c(HomeLogContants.f11749a);
        if (!TextUtils.isEmpty(c2)) {
            try {
                JSONArray jSONArray = new JSONArray(c2);
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    String optString = jSONObject.optString(HomeLogContants.d);
                    JSONArray optJSONArray = jSONObject.optJSONArray(HomeLogContants.c);
                    if (optJSONArray != null) {
                        if (optJSONArray.length() != 0) {
                            JSONObject optJSONObject = jSONObject.optJSONObject(HomeLogContants.g);
                            if (!(optJSONObject == null || optJSONArray == null)) {
                                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                                    JSONArray optJSONArray2 = optJSONObject.optJSONArray(optJSONArray.getString(i3));
                                    if (optJSONArray2 != null) {
                                        for (int i4 = 0; i4 < optJSONArray2.length(); i4++) {
                                            this.y.put(optJSONArray2.getString(i4), optString);
                                        }
                                    }
                                }
                            }
                            if (optJSONArray != null) {
                                for (int i5 = 0; i5 < optJSONArray.length(); i5++) {
                                    this.A.put(optJSONArray.getString(i5), optString);
                                }
                            }
                            this.F.b(optJSONArray, optString);
                        }
                    }
                    String optString2 = jSONObject.optString(HomeLogContants.b);
                    if (!TextUtils.isEmpty(optString2)) {
                        this.A.put(optString2, optString);
                    }
                    this.F.b(optJSONArray, optString);
                }
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void g() {
        this.w.clear();
        this.x.clear();
        String c2 = SHConfigPhone.a().c(d);
        String c3 = SHConfigPhone.a().c(e);
        if (c2 != null) {
            try {
                JSONArray jSONArray = new JSONArray(c2);
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                    this.w.put(optJSONObject.getString(DeviceTagInterface.e), Integer.valueOf(optJSONObject.getInt("times")));
                }
            } catch (JSONException unused) {
            }
        }
        if (c3 != null) {
            try {
                JSONArray jSONArray2 = new JSONArray(c3);
                for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                    JSONObject optJSONObject2 = jSONArray2.optJSONObject(i3);
                    this.w.put(optJSONObject2.getString(DeviceTagInterface.e), Integer.valueOf(optJSONObject2.getInt("times")));
                }
            } catch (JSONException unused2) {
            }
        }
    }

    public void b() {
        this.D.unregisterReceiver(this.I);
        SceneManager.x().b(this.G);
        if (this.l != null) {
            this.l.quit();
            this.l = null;
        }
        this.F.b();
        H = null;
    }

    public void a(Context context) {
        this.D = context;
        this.F = new WIFIScanHomelogViaMIUI();
        this.F.a(context);
    }

    public boolean c() {
        if (!this.F.c() || i() != null) {
            return false;
        }
        return true;
    }

    public Context h() {
        return this.D;
    }

    /* access modifiers changed from: package-private */
    public String i() {
        String convertToQuotedString;
        WifiInfo connectionInfo = this.s.getConnectionInfo();
        if (connectionInfo == null || (convertToQuotedString = HomeLogWifiSetting.convertToQuotedString(connectionInfo.getSSID())) == null || !this.A.containsKey(convertToQuotedString)) {
            return null;
        }
        return this.A.get(convertToQuotedString);
    }

    /* access modifiers changed from: package-private */
    public void j() {
        String i2 = i();
        c("Start Check Wifi Location");
        List<ScanResult> list = null;
        if (i2 == null) {
            try {
                list = this.s.getScanResults();
            } catch (Exception unused) {
            }
            if (list != null) {
                for (ScanResult next : list) {
                    if (!this.z.containsKey(next.BSSID)) {
                        this.z.put(next.BSSID, next);
                    }
                }
                WifiInfo connectionInfo = this.s.getConnectionInfo();
                if (!(connectionInfo == null || connectionInfo.getBSSID() == null || !this.y.containsKey(connectionInfo.getBSSID()))) {
                    i2 = this.y.get(connectionInfo.getBSSID());
                }
            } else {
                return;
            }
        }
        if (i2 == null) {
            Iterator<Map.Entry<String, ScanResult>> it = this.z.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next2 = it.next();
                if (this.y.containsKey(next2.getKey())) {
                    i2 = this.y.get(next2.getKey());
                    break;
                }
            }
        }
        if (i2 == null) {
            i2 = HomeLogContants.j;
        }
        if (list != null) {
            if (this.B == null || this.u) {
                TreeMap<String, String> c2 = WifiConnectionConfig.a().c();
                boolean z2 = true;
                if (c2.size() == 0) {
                    Message obtainMessage = this.k.obtainMessage();
                    obtainMessage.what = 4;
                    StartSceneInfo startSceneInfo = new StartSceneInfo();
                    startSceneInfo.c = System.currentTimeMillis();
                    startSceneInfo.f22917a = i2;
                    obtainMessage.obj = startSceneInfo;
                    this.k.sendMessage(obtainMessage);
                } else {
                    Iterator<Map.Entry<String, String>> it2 = c2.entrySet().iterator();
                    if (!it2.hasNext() || ((String) it2.next().getValue()).equalsIgnoreCase(i2)) {
                        z2 = false;
                    } else {
                        Message obtainMessage2 = this.k.obtainMessage();
                        StartSceneInfo startSceneInfo2 = new StartSceneInfo();
                        startSceneInfo2.f22917a = i2;
                        startSceneInfo2.c = System.currentTimeMillis();
                        obtainMessage2.obj = startSceneInfo2;
                        this.k.sendMessage(obtainMessage2);
                    }
                }
                if (z2) {
                    WifiConnectionConfig.a().a(String.valueOf(Long.valueOf(System.currentTimeMillis())), i2);
                }
            } else if (!this.B.equalsIgnoreCase(i2)) {
                WifiConnectionConfig.a().a(String.valueOf(Long.valueOf(System.currentTimeMillis())), i2);
                Message obtainMessage3 = this.k.obtainMessage();
                obtainMessage3.what = 4;
                StartSceneInfo startSceneInfo3 = new StartSceneInfo();
                startSceneInfo3.f22917a = i2;
                startSceneInfo3.c = System.currentTimeMillis();
                startSceneInfo3.b = this.B;
                obtainMessage3.obj = startSceneInfo3;
                this.k.sendMessage(obtainMessage3);
            }
            this.B = i2;
            this.u = false;
            this.C = 0;
            this.z.clear();
        }
    }

    public void k() {
        WIFIScanHomelogViaMIUI wIFIScanHomelogViaMIUI = this.F;
        Handler handler = this.m;
        if (wIFIScanHomelogViaMIUI != null && handler != null && !wIFIScanHomelogViaMIUI.d()) {
            handler.removeMessages(1);
            handler.sendEmptyMessage(1);
        }
    }

    public void l() {
        NetworkInfo activeNetworkInfo;
        try {
            if (!this.F.d() && (activeNetworkInfo = ((ConnectivityManager) h().getSystemService("connectivity")).getActiveNetworkInfo()) != null && activeNetworkInfo.getType() == 1) {
                b(((WifiManager) h().getSystemService("wifi")).getConnectionInfo().getSSID());
            }
            NetworkInfo activeNetworkInfo2 = this.t.getActiveNetworkInfo();
            if (activeNetworkInfo2 != null && activeNetworkInfo2.getType() == 1) {
                try {
                    if (new JSONArray(SHConfig.a().c(HomeLogContants.f11749a)).length() < 2) {
                        SHConfig.a().a(HomeLogContants.l, System.currentTimeMillis());
                    }
                } catch (JSONException unused) {
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void c(String str) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/mnt/sdcard/wifi_config.log", "rw");
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write(("time - " + simpleDateFormat.format(date) + ", ").getBytes());
            randomAccessFile.write(str.getBytes());
            randomAccessFile.write(10);
            randomAccessFile.close();
        } catch (FileNotFoundException | IOException unused) {
        }
    }

    public static String m() {
        if (H != null) {
            return H.B;
        }
        return null;
    }

    public static void a(StartSceneInfo startSceneInfo) {
        Message obtainMessage = d().k.obtainMessage();
        obtainMessage.what = 4;
        obtainMessage.obj = startSceneInfo;
        d().k.sendMessage(obtainMessage);
        WifiScanHomelog d2 = d();
        d2.c(startSceneInfo.b + ", " + startSceneInfo.f22917a);
    }

    public static void a(Intent intent) {
        String stringExtra = intent.getStringExtra(n);
        String stringExtra2 = intent.getStringExtra(o);
        if (H != null && H.F != null && H.F.d() && !TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(stringExtra2) && !TextUtils.equals(p, stringExtra)) {
            StartSceneInfo startSceneInfo = new StartSceneInfo();
            startSceneInfo.c = System.currentTimeMillis();
            if (TextUtils.equals(r, stringExtra2) && TextUtils.equals("home", stringExtra)) {
                startSceneInfo.f22917a = "home";
            } else if (TextUtils.equals(q, stringExtra2) && TextUtils.equals("home", stringExtra)) {
                startSceneInfo.f22917a = HomeLogContants.j;
            } else {
                return;
            }
            Message obtainMessage = d().k.obtainMessage();
            obtainMessage.what = 4;
            obtainMessage.obj = startSceneInfo;
            d().k.sendMessage(obtainMessage);
            WifiScanHomelog d2 = d();
            d2.c(startSceneInfo.b + ", " + startSceneInfo.f22917a);
        }
    }

    public boolean n() {
        if (this.F == null) {
            return false;
        }
        return this.F.d();
    }

    public WIFIScanHomelogViaMIUI o() {
        return this.F;
    }
}
