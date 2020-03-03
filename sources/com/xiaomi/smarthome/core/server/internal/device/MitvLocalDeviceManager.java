package com.xiaomi.smarthome.core.server.internal.device;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.device.DiscoverManager;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.deviceId.DeviceIdCompat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MitvLocalDeviceManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14545a = "mitv_local_devices_report_time";
    private static MitvLocalDeviceManager b;
    private static Object c = new Object();
    private DiscoverManager.DeviceDiscoverListener d;
    /* access modifiers changed from: private */
    public ArrayList<Device> e = new ArrayList<>();
    private SharedPreferences f;
    private long g;
    private Handler h = new Handler(Looper.getMainLooper());

    private MitvLocalDeviceManager(Context context) {
        this.f = PreferenceManager.getDefaultSharedPreferences(context);
        this.g = this.f.getLong(f14545a, 0);
    }

    public static MitvLocalDeviceManager a(Context context) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new MitvLocalDeviceManager(context);
                }
            }
        }
        return b;
    }

    public static MitvLocalDeviceManager a() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new MitvLocalDeviceManager(CoreService.getAppContext());
                }
            }
        }
        return b;
    }

    public synchronized void a(DiscoverManager.DeviceDiscoverListener deviceDiscoverListener) {
        this.d = deviceDiscoverListener;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.xiaomi.smarthome.core.entity.device.Device r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r4 == 0) goto L_0x0050
            java.lang.String r0 = r4.k()     // Catch:{ all -> 0x004d }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x000e
            goto L_0x0050
        L_0x000e:
            java.util.ArrayList<com.xiaomi.smarthome.core.entity.device.Device> r0 = r3.e     // Catch:{ all -> 0x004d }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x004d }
        L_0x0014:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x0030
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x004d }
            com.xiaomi.smarthome.core.entity.device.Device r1 = (com.xiaomi.smarthome.core.entity.device.Device) r1     // Catch:{ all -> 0x004d }
            java.lang.String r2 = r4.k()     // Catch:{ all -> 0x004d }
            java.lang.String r1 = r1.k()     // Catch:{ all -> 0x004d }
            boolean r1 = r2.equals(r1)     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x0014
            monitor-exit(r3)
            return
        L_0x0030:
            java.util.ArrayList<com.xiaomi.smarthome.core.entity.device.Device> r0 = r3.e     // Catch:{ all -> 0x004d }
            r0.add(r4)     // Catch:{ all -> 0x004d }
            com.xiaomi.smarthome.core.server.internal.device.DiscoverManager$DeviceDiscoverListener r0 = r3.d     // Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x003e
            com.xiaomi.smarthome.core.server.internal.device.DiscoverManager$DeviceDiscoverListener r0 = r3.d     // Catch:{ all -> 0x004d }
            r0.a(r4)     // Catch:{ all -> 0x004d }
        L_0x003e:
            android.os.Handler r4 = r3.h     // Catch:{ all -> 0x004d }
            com.xiaomi.smarthome.core.server.internal.device.MitvLocalDeviceManager$1 r0 = new com.xiaomi.smarthome.core.server.internal.device.MitvLocalDeviceManager$1     // Catch:{ all -> 0x004d }
            r0.<init>()     // Catch:{ all -> 0x004d }
            r1 = 60000(0xea60, double:2.9644E-319)
            r4.postDelayed(r0, r1)     // Catch:{ all -> 0x004d }
            monitor-exit(r3)
            return
        L_0x004d:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x0050:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.MitvLocalDeviceManager.a(com.xiaomi.smarthome.core.entity.device.Device):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(com.xiaomi.smarthome.core.entity.device.Device r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r4 == 0) goto L_0x004d
            java.lang.String r0 = r4.k()     // Catch:{ all -> 0x004a }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x000e
            goto L_0x004d
        L_0x000e:
            r0 = 0
        L_0x000f:
            java.util.ArrayList<com.xiaomi.smarthome.core.entity.device.Device> r1 = r3.e     // Catch:{ all -> 0x004a }
            int r1 = r1.size()     // Catch:{ all -> 0x004a }
            if (r0 >= r1) goto L_0x0048
            java.lang.String r1 = r4.k()     // Catch:{ all -> 0x004a }
            java.util.ArrayList<com.xiaomi.smarthome.core.entity.device.Device> r2 = r3.e     // Catch:{ all -> 0x004a }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x004a }
            com.xiaomi.smarthome.core.entity.device.Device r2 = (com.xiaomi.smarthome.core.entity.device.Device) r2     // Catch:{ all -> 0x004a }
            java.lang.String r2 = r2.k()     // Catch:{ all -> 0x004a }
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x0045
            com.xiaomi.smarthome.core.server.internal.device.DiscoverManager$DeviceDiscoverListener r4 = r3.d     // Catch:{ all -> 0x004a }
            if (r4 == 0) goto L_0x003e
            com.xiaomi.smarthome.core.server.internal.device.DiscoverManager$DeviceDiscoverListener r4 = r3.d     // Catch:{ all -> 0x004a }
            java.util.ArrayList<com.xiaomi.smarthome.core.entity.device.Device> r1 = r3.e     // Catch:{ all -> 0x004a }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x004a }
            com.xiaomi.smarthome.core.entity.device.Device r1 = (com.xiaomi.smarthome.core.entity.device.Device) r1     // Catch:{ all -> 0x004a }
            r4.b(r1)     // Catch:{ all -> 0x004a }
        L_0x003e:
            java.util.ArrayList<com.xiaomi.smarthome.core.entity.device.Device> r4 = r3.e     // Catch:{ all -> 0x004a }
            r4.remove(r0)     // Catch:{ all -> 0x004a }
            monitor-exit(r3)
            return
        L_0x0045:
            int r0 = r0 + 1
            goto L_0x000f
        L_0x0048:
            monitor-exit(r3)
            return
        L_0x004a:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x004d:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.MitvLocalDeviceManager.b(com.xiaomi.smarthome.core.entity.device.Device):void");
    }

    public synchronized void b() {
        this.e.clear();
    }

    public void a(List<Device> list) {
        if (list != null && !list.isEmpty() && System.currentTimeMillis() - this.g >= 86400000) {
            if (ActivityCompat.checkSelfPermission(CoreService.getAppContext(), "android.permission.READ_PHONE_STATE") != 0) {
                new SecurityException("need READ_PHONE_STATE").printStackTrace();
                return;
            }
            String a2 = DeviceIdCompat.a(CoreService.getAppContext());
            if (!TextUtils.isEmpty(a2)) {
                String a3 = MD5.a(a2);
                if (!TextUtils.isDigitsOnly(a3)) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("api_type", "scan");
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("imei", a3);
                        JSONArray jSONArray = new JSONArray();
                        for (Device k : list) {
                            jSONArray.put(k.k());
                        }
                        jSONObject2.put("scan_result", jSONArray);
                        jSONObject.put("data", jSONObject2);
                        this.g = System.currentTimeMillis();
                        this.f.edit().putLong(f14545a, this.g).apply();
                        a(jSONObject);
                    } catch (JSONException unused) {
                    }
                }
            }
        }
    }

    private void a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("app_id", 10004);
            jSONObject2.put("params", jSONObject);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject2.toString()));
        SmartHomeRc4Api.a().a(new NetRequest.Builder().a("POST").b("/third/api").b((List<KeyValuePair>) arrayList).a(), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            public void a(NetError netError) {
            }

            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
            }
        });
    }

    public synchronized List<Device> c() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        arrayList.addAll(this.e);
        return arrayList;
    }
}
