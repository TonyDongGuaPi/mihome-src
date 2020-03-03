package com.xiaomi.smarthome.device;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.deviceId.DeviceIdCompat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MitvLocalDeviceManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14909a = "MitvLocalDeviceManager";
    static final String b = "mitv_local_devices_report_time";
    public static MitvLocalDeviceManager h;
    ArrayList<Device> c = new ArrayList<>();
    boolean d = false;
    SharedPreferences e = PreferenceManager.getDefaultSharedPreferences(SHApplication.getAppContext());
    long f = this.e.getLong(b, 0);
    Handler g = new Handler(Looper.getMainLooper());

    public static synchronized MitvLocalDeviceManager a() {
        MitvLocalDeviceManager mitvLocalDeviceManager;
        synchronized (MitvLocalDeviceManager.class) {
            if (h == null) {
                h = new MitvLocalDeviceManager();
            }
            mitvLocalDeviceManager = h;
        }
        return mitvLocalDeviceManager;
    }

    private MitvLocalDeviceManager() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.xiaomi.smarthome.device.Device r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r4 == 0) goto L_0x0043
            java.lang.String r0 = r4.did     // Catch:{ all -> 0x0040 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x000c
            goto L_0x0043
        L_0x000c:
            r0 = 0
        L_0x000d:
            java.util.ArrayList<com.xiaomi.smarthome.device.Device> r1 = r3.c     // Catch:{ all -> 0x0040 }
            int r1 = r1.size()     // Catch:{ all -> 0x0040 }
            if (r0 >= r1) goto L_0x002c
            java.lang.String r1 = r4.did     // Catch:{ all -> 0x0040 }
            java.util.ArrayList<com.xiaomi.smarthome.device.Device> r2 = r3.c     // Catch:{ all -> 0x0040 }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x0040 }
            com.xiaomi.smarthome.device.Device r2 = (com.xiaomi.smarthome.device.Device) r2     // Catch:{ all -> 0x0040 }
            java.lang.String r2 = r2.did     // Catch:{ all -> 0x0040 }
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x0029
            monitor-exit(r3)
            return
        L_0x0029:
            int r0 = r0 + 1
            goto L_0x000d
        L_0x002c:
            java.util.ArrayList<com.xiaomi.smarthome.device.Device> r0 = r3.c     // Catch:{ all -> 0x0040 }
            r0.add(r4)     // Catch:{ all -> 0x0040 }
            android.os.Handler r4 = r3.g     // Catch:{ all -> 0x0040 }
            com.xiaomi.smarthome.device.MitvLocalDeviceManager$1 r0 = new com.xiaomi.smarthome.device.MitvLocalDeviceManager$1     // Catch:{ all -> 0x0040 }
            r0.<init>()     // Catch:{ all -> 0x0040 }
            r1 = 60000(0xea60, double:2.9644E-319)
            r4.postDelayed(r0, r1)     // Catch:{ all -> 0x0040 }
            monitor-exit(r3)
            return
        L_0x0040:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x0043:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.MitvLocalDeviceManager.a(com.xiaomi.smarthome.device.Device):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(com.xiaomi.smarthome.device.Device r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r4 == 0) goto L_0x0036
            java.lang.String r0 = r4.did     // Catch:{ all -> 0x0033 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x000c
            goto L_0x0036
        L_0x000c:
            r0 = 0
        L_0x000d:
            java.util.ArrayList<com.xiaomi.smarthome.device.Device> r1 = r3.c     // Catch:{ all -> 0x0033 }
            int r1 = r1.size()     // Catch:{ all -> 0x0033 }
            if (r0 >= r1) goto L_0x0031
            java.lang.String r1 = r4.did     // Catch:{ all -> 0x0033 }
            java.util.ArrayList<com.xiaomi.smarthome.device.Device> r2 = r3.c     // Catch:{ all -> 0x0033 }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x0033 }
            com.xiaomi.smarthome.device.Device r2 = (com.xiaomi.smarthome.device.Device) r2     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = r2.did     // Catch:{ all -> 0x0033 }
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0033 }
            if (r1 == 0) goto L_0x002e
            java.util.ArrayList<com.xiaomi.smarthome.device.Device> r4 = r3.c     // Catch:{ all -> 0x0033 }
            r4.remove(r0)     // Catch:{ all -> 0x0033 }
            monitor-exit(r3)
            return
        L_0x002e:
            int r0 = r0 + 1
            goto L_0x000d
        L_0x0031:
            monitor-exit(r3)
            return
        L_0x0033:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x0036:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.MitvLocalDeviceManager.b(com.xiaomi.smarthome.device.Device):void");
    }

    public synchronized void b() {
        this.c.clear();
    }

    public void a(List<Device> list) {
        if (list != null && list.size() != 0 && System.currentTimeMillis() - this.f >= 86400000) {
            TelephonyManager telephonyManager = (TelephonyManager) SHApplication.getAppContext().getSystemService("phone");
            if (ActivityCompat.checkSelfPermission(SHApplication.getAppContext(), "android.permission.READ_PHONE_STATE") != 0) {
                new SecurityException("need READ_PHONE_STATE").printStackTrace();
                return;
            }
            String a2 = DeviceIdCompat.a(SHApplication.getAppContext());
            if (!TextUtils.isEmpty(a2)) {
                String a3 = MD5.a(a2);
                if (!TextUtils.isEmpty(a3)) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("api_type", "scan");
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("imei", a3);
                        JSONArray jSONArray = new JSONArray();
                        for (Device device : list) {
                            jSONArray.put(device.did);
                        }
                        jSONObject2.put("scan_result", jSONArray);
                        jSONObject.put("data", jSONObject2);
                        Log.d(f14909a, "reportLocalSearchMitvDevices");
                        AnonymousClass2 r7 = new Callback<JSONObject>() {
                            /* renamed from: a */
                            public void onSuccess(JSONObject jSONObject) {
                                Log.d(MitvLocalDeviceManager.f14909a, "onSuccess:" + jSONObject.toString());
                            }

                            public void onFailure(int i, String str) {
                                Log.d(MitvLocalDeviceManager.f14909a, "onFailure");
                            }
                        };
                        this.f = System.currentTimeMillis();
                        this.e.edit().putLong(b, this.f).apply();
                        RemoteAsyncApiHelper.a().a(SHApplication.getAppContext(), (String[]) null, 10004, jSONObject, r7);
                    } catch (JSONException unused) {
                    }
                }
            }
        }
    }
}
