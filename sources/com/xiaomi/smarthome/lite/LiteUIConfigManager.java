package com.xiaomi.smarthome.lite;

import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.BatchRpcHelper;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.devicesubscribe.DevicePropSubscriber;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.SyncCallback;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LiteUIConfigManager {

    /* renamed from: a  reason: collision with root package name */
    protected static LiteUIConfigManager f19367a;
    protected Map<String, LiteUIConfig> b = Collections.synchronizedMap(new HashMap());
    protected Map<String, Map<String, String>> c = Collections.synchronizedMap(new HashMap());
    private DevicePropSubscriber d;

    public static LiteUIConfigManager a() {
        if (f19367a == null) {
            f19367a = new LiteUIConfigManager();
        }
        return f19367a;
    }

    protected LiteUIConfigManager() {
    }

    public void a(final String str, LiteOptConfig liteOptConfig) {
        LiteUIConfig liteUIConfig;
        ArrayList<LiteComConfig> arrayList;
        if (!TextUtils.isEmpty(str)) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(SmartHomeDeviceManager.a().d());
            arrayList2.addAll(SmartHomeDeviceManager.a().k());
            Device device = null;
            int i = 0;
            while (true) {
                if (i >= arrayList2.size()) {
                    break;
                }
                Device device2 = (Device) arrayList2.get(i);
                if (device2 != null && TextUtils.equals(str, device2.did)) {
                    device = device2;
                    break;
                }
                i++;
            }
            if (device != null && !TextUtils.isEmpty(device.model) && (liteUIConfig = this.b.get(device.model)) != null && !TextUtils.isEmpty(liteUIConfig.f19366a) && (arrayList = liteUIConfig.c) != null) {
                try {
                    HashSet hashSet = new HashSet();
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        LiteComConfig liteComConfig = arrayList.get(i2);
                        if (!TextUtils.isEmpty(liteComConfig.c)) {
                            hashSet.add(liteComConfig.c);
                        }
                        ArrayList<LiteOptConfig> arrayList3 = liteComConfig.f;
                        if (arrayList3 != null) {
                            for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                                LiteOptConfig liteOptConfig2 = arrayList3.get(i3);
                                if (!TextUtils.isEmpty(liteOptConfig2.f19363a)) {
                                    String str2 = liteOptConfig2.f19363a;
                                    if (str2.startsWith("prop.")) {
                                        str2 = str2.substring("prop.".length());
                                    }
                                    hashSet.add(str2);
                                }
                            }
                        }
                    }
                    if (hashSet.size() > 0) {
                        final JSONArray jSONArray = new JSONArray();
                        Iterator it = hashSet.iterator();
                        while (it.hasNext()) {
                            jSONArray.put((String) it.next());
                        }
                        BatchRpcHelper batchRpcHelper = new BatchRpcHelper();
                        batchRpcHelper.addBatchRequest(str, "get_prop", jSONArray);
                        batchRpcHelper.commit(new AsyncCallback<JSONObject, Error>() {
                            public void onFailure(Error error) {
                            }

                            /* renamed from: a */
                            public void onSuccess(JSONObject jSONObject) {
                                try {
                                    if (TextUtils.isEmpty(str)) {
                                        LiteUIConfigManager.this.c.clear();
                                    }
                                    Map map = LiteUIConfigManager.this.c.get(str);
                                    if (map == null) {
                                        map = new HashMap();
                                    }
                                    Object opt = jSONObject.opt(str);
                                    if ((opt instanceof JSONArray) && ((JSONArray) opt).length() >= jSONArray.length()) {
                                        for (int i = 0; i < jSONArray.length(); i++) {
                                            if (((JSONArray) opt).opt(i) instanceof String) {
                                                map.put("prop." + jSONArray.optString(i), ((JSONArray) opt).optString(i));
                                            }
                                        }
                                        LiteUIConfigManager.this.c.put(str, map);
                                    }
                                    if (!TextUtils.isEmpty(str)) {
                                        SmartHomeDeviceManager.a().a(SmartHomeDeviceManager.a().b(str));
                                        return;
                                    }
                                    SmartHomeDeviceManager.a().j();
                                } catch (Exception unused) {
                                }
                            }
                        });
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    public boolean a(Device device, LiteOptConfig liteOptConfig) {
        if (TextUtils.isEmpty(liteOptConfig.f) || "null".equalsIgnoreCase(liteOptConfig.f)) {
            return false;
        }
        Map map = this.c.get(device.did);
        if (map == null) {
            map = new HashMap();
        }
        map.put(liteOptConfig.f19363a, liteOptConfig.f);
        this.c.put(device.did, map);
        SmartHomeDeviceManager.a().a(SmartHomeDeviceManager.a().b(device.did));
        return true;
    }

    public static boolean b(Device device, LiteOptConfig liteOptConfig) {
        if (!TextUtils.isEmpty(device.model) && device.model.startsWith("lumi.gateway.") && TextUtils.equals(liteOptConfig.f19363a, "light")) {
            return true;
        }
        return false;
    }

    public static boolean c(Device device, LiteOptConfig liteOptConfig) {
        if (!TextUtils.isEmpty(device.model) && device.model.startsWith("lumi.gateway.") && TextUtils.equals(liteOptConfig.f19363a, "fm_current_status")) {
            return true;
        }
        return false;
    }

    public void a(final String str, List<Device> list, List<Device> list2, boolean z) {
        AsyncCallback asyncCallback;
        LiteUIConfig liteUIConfig;
        ArrayList<LiteComConfig> arrayList;
        JSONArray jSONArray = new JSONArray();
        final ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(list);
        arrayList2.addAll(list2);
        for (int i = 0; i < arrayList2.size(); i++) {
            Device device = (Device) arrayList2.get(i);
            if (!((str != null && !str.equals(device.did)) || device == null || device.model == null || device.did == null || (liteUIConfig = this.b.get(device.model)) == null || liteUIConfig.f19366a == null || (arrayList = liteUIConfig.c) == null)) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    HashSet hashSet = new HashSet();
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        LiteComConfig liteComConfig = arrayList.get(i2);
                        if (device.model.startsWith("lumi.sensor_magnet.")) {
                            hashSet.add("event.open");
                            hashSet.add("event.close");
                        } else if (!TextUtils.isEmpty(liteComConfig.c)) {
                            hashSet.add(liteComConfig.c);
                        }
                        ArrayList<LiteOptConfig> arrayList3 = liteComConfig.f;
                        if (arrayList3 != null) {
                            for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                                LiteOptConfig liteOptConfig = arrayList3.get(i3);
                                if (!TextUtils.isEmpty(liteOptConfig.f19363a)) {
                                    hashSet.add(liteOptConfig.f19363a);
                                }
                            }
                        }
                    }
                    if (hashSet.size() > 0) {
                        jSONObject.put("did", device.did);
                        JSONArray jSONArray2 = new JSONArray();
                        Iterator it = hashSet.iterator();
                        while (it.hasNext()) {
                            jSONArray2.put((String) it.next());
                        }
                        jSONObject.put("props", jSONArray2);
                        jSONArray.put(jSONObject);
                    }
                } catch (JSONException unused) {
                }
                if (str != null) {
                    break;
                }
            }
        }
        if (jSONArray.length() > 0) {
            if (z) {
                asyncCallback = new SyncCallback<String, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(String str) {
                        LiteUIConfigManager.this.a(str, str, true);
                        LiteDeviceManager.a().b(LiteDeviceManager.a().c((List<Device>) arrayList2));
                    }
                };
            } else {
                asyncCallback = new AsyncCallback<String, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(String str) {
                        LiteUIConfigManager.this.a(str, str, false);
                        LiteDeviceManager.a().b(LiteDeviceManager.a().c((List<Device>) arrayList2));
                    }
                };
            }
            DevicelibApi.batchGetDeviceProps(CommonApplication.getAppContext(), jSONArray, asyncCallback, LiteUIConfigManager.class.getSimpleName());
        }
    }

    public boolean b() {
        return this.c != null && this.c.size() > 0;
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            if (str2 == null) {
                this.c.clear();
            }
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject optJSONObject = jSONObject.optJSONObject(next);
                if (optJSONObject != null) {
                    HashMap hashMap = new HashMap();
                    Iterator<String> keys2 = optJSONObject.keys();
                    while (keys2.hasNext()) {
                        String next2 = keys2.next();
                        Object obj = optJSONObject.get(next2);
                        if (obj != null) {
                            hashMap.put(next2, "" + obj);
                        }
                    }
                    this.c.put(next, hashMap);
                }
            }
            if (str2 != null) {
                Device b2 = SmartHomeDeviceManager.a().b(str2);
                if (!z) {
                    SmartHomeDeviceManager.a().a(b2);
                }
            } else if (!z) {
                SmartHomeDeviceManager.a().j();
                if (this.d == null) {
                    this.d = new DevicePropSubscriber();
                }
                HashSet hashSet = new HashSet();
                hashSet.addAll(SmartHomeDeviceManager.a().d());
                hashSet.addAll(SmartHomeDeviceManager.a().k());
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(hashSet);
                hashSet.clear();
                this.d.a((List<Device>) arrayList, (DevicePropSubscriber.DeviceSubscriberInterface) new DevicePropSubscriber.DeviceSubscriberInterface() {
                    public List<String> a(String str) {
                        Map<String, String> d = LiteUIConfigManager.this.d(str);
                        if (d == null || d.size() == 0) {
                            return null;
                        }
                        return new ArrayList(d.keySet());
                    }

                    public void a(String str, JSONArray jSONArray) {
                        LiteUIConfigManager.this.a(str, "", jSONArray);
                    }

                    public JSONArray b(String str) {
                        JSONArray jSONArray = new JSONArray();
                        Map<String, String> d = LiteUIConfigManager.this.d(str);
                        if (d != null) {
                            for (Map.Entry next : d.entrySet()) {
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put(((String) next.getKey()).substring(((String) next.getKey()).startsWith("prop.") ? "prop.".length() : 0), next.getValue());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                jSONArray.put(jSONObject);
                            }
                        }
                        return jSONArray;
                    }
                });
            }
        } catch (JSONException unused) {
        }
    }

    public void a(String str) {
        a(str, SmartHomeDeviceManager.a().d(), SmartHomeDeviceManager.a().k(), false);
    }

    public void a(String str, String str2, JSONArray jSONArray) {
        List<LiteDeviceAbstract> d2;
        if (!TextUtils.isEmpty(str) && jSONArray != null && jSONArray.length() != 0 && (d2 = LiteDeviceManager.a().d()) != null && d2.size() != 0) {
            int i = 0;
            while (i < d2.size()) {
                LiteDevice liteDevice = (LiteDevice) d2.get(i);
                if (liteDevice == null || liteDevice.f19353a == null || !TextUtils.equals(str, liteDevice.f19353a.did) || System.currentTimeMillis() - liteDevice.a() >= 4000) {
                    i++;
                } else {
                    return;
                }
            }
            Map map = this.c.get(str);
            if (map == null) {
                map = new HashMap();
                this.c.put(str, map);
            }
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null && !optJSONObject.isNull("key") && !optJSONObject.isNull("value")) {
                    String optString = optJSONObject.optString("key");
                    if (!TextUtils.isEmpty(optString)) {
                        Object opt = optJSONObject.opt("value");
                        if ((opt instanceof JSONArray) && ((JSONArray) opt).length() > 0) {
                            try {
                                opt = ((JSONArray) opt).getString(0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d("LiteDeviceSubscribeMgr", "key=" + optString + ",value=" + opt.toString());
                        map.put(optString, opt.toString());
                        SmartHomeDeviceManager.a().a(SmartHomeDeviceManager.a().b(str));
                    }
                }
            }
        }
    }

    public void c() {
        CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                String c = SharePrefsManager.c(CommonApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.j, (String) null);
                boolean z = true;
                if (!TextUtils.isEmpty(c)) {
                    long a2 = SharePrefsManager.a(CommonApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.k, 0);
                    if (a2 > 0) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        if (simpleDateFormat.format(new Date(a2)).equals(simpleDateFormat.format(new Date(System.currentTimeMillis())))) {
                            z = false;
                        }
                    }
                }
                if (z) {
                    LiteUIConfigManager.this.d();
                } else {
                    LiteUIConfigManager.this.c(c);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            SharePrefsManager.b(CommonApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.k, System.currentTimeMillis());
            SharePrefsManager.a(CommonApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.j, str);
            c(str);
        }
    }

    public void d() {
        DevicelibApi.getGeekDeviceDesc(CommonApplication.getAppContext(), this.b.keySet(), new AsyncCallback<String, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(String str) {
                LiteUIConfigManager.this.b(str);
                SmartHomeDeviceManager.a().p();
            }
        });
    }

    public void e() {
        DevicelibApi.getGeekDeviceDesc(CommonApplication.getAppContext(), this.b.keySet(), new SyncCallback<String, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(String str) {
                LiteUIConfigManager.this.b(str);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                this.b.clear();
                JSONArray optJSONArray = new JSONObject(str).optJSONArray("result");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        String optString = optJSONArray.optString(i);
                        LiteUIConfig liteUIConfig = new LiteUIConfig();
                        liteUIConfig.a(optString);
                        if (!TextUtils.isEmpty(liteUIConfig.f19366a)) {
                            this.b.put(liteUIConfig.f19366a, liteUIConfig);
                        }
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public boolean a(List<Device> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            Device device = list.get(i);
            if (device != null && !TextUtils.isEmpty(device.model)) {
                if (device instanceof MiTVDevice) {
                    MiTVDevice miTVDevice = (MiTVDevice) device;
                    if (this.b.get(miTVDevice.a()) == null) {
                        this.b.put(miTVDevice.a(), new LiteUIConfig());
                        z = true;
                    }
                }
                LiteUIConfig liteUIConfig = this.b.get(device.model);
                if (liteUIConfig == null) {
                    this.b.put(device.model, new LiteUIConfig());
                } else if (liteUIConfig.f19366a != null) {
                }
                z = true;
            }
        }
        return z;
    }

    public Map<String, String> d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.c.get(str);
    }

    public LiteUIConfig e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.b.get(str);
    }

    public String f(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("__")) {
            return null;
        }
        String[] split = str.split("__");
        if (split.length < 2) {
            return null;
        }
        return split[1];
    }

    public void f() {
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
    }
}
