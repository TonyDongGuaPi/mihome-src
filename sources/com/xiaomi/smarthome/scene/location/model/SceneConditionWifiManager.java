package com.xiaomi.smarthome.scene.location.model;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.wificonfig.WIFIScanHomelogViaMIUI;
import com.xiaomi.smarthome.wificonfig.WifiScanHomelog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;

public class SceneConditionWifiManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f21605a = "WG_";
    private static SceneConditionWifiManager b;
    private boolean c = false;
    private Map<String, JSONArray> d = new HashMap();
    private volatile boolean e = false;
    /* access modifiers changed from: private */
    public Handler f = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    };
    private SceneManager.IScenceListener g = new SceneManager.IScenceListener() {
        public void onRefreshScenceFailed(int i) {
        }

        public void onRefreshScenceSuccess(int i) {
            if (i == 1) {
                SceneConditionWifiManager.this.f.post(new Runnable() {
                    public void run() {
                        SceneConditionWifiManager.this.d();
                    }
                });
            }
        }
    };

    public static SceneConditionWifiManager a() {
        if (b == null) {
            synchronized (SceneConditionWifiManager.class) {
                if (b == null) {
                    b = new SceneConditionWifiManager();
                }
            }
        }
        return b;
    }

    private SceneConditionWifiManager() {
        SceneManager.x().a(this.g);
    }

    public void a(final List<SceneApi.SmartHomeScene> list) {
        this.f.post(new Runnable() {
            public void run() {
                if (list != null && list.size() != 0) {
                    Map a2 = SceneConditionWifiManager.this.b((List<SceneApi.SmartHomeScene>) list);
                    if (!a2.isEmpty()) {
                        SceneConditionWifiManager.this.a((Map<String, JSONArray>) a2);
                    }
                }
            }
        });
    }

    public void a(final SceneApi.SmartHomeScene smartHomeScene) {
        this.f.post(new Runnable() {
            public void run() {
                String str;
                HashMap hashMap = new HashMap();
                for (SceneApi.Condition next : smartHomeScene.l) {
                    if (!(next == null || next.h == null || TextUtils.isEmpty(next.h.o))) {
                        if (next.h.o.startsWith("enter_WG_")) {
                            str = next.h.o.substring(SceneApi.ConditionELLocation.l.length());
                        } else if (next.h.o.startsWith("leave_WG_")) {
                            str = next.h.o.substring(SceneApi.ConditionELLocation.m.length());
                        }
                        if (!TextUtils.isEmpty(str)) {
                            JSONArray jSONArray = null;
                            if (!TextUtils.isEmpty(next.h.n)) {
                                try {
                                    jSONArray = new JSONArray(next.h.n);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    jSONArray = new JSONArray();
                                }
                            }
                            hashMap.put(str, jSONArray);
                        }
                    }
                }
                SceneConditionWifiManager.this.b((Map<String, JSONArray>) hashMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public Map<String, JSONArray> b(List<SceneApi.SmartHomeScene> list) {
        String str;
        HashMap hashMap = new HashMap();
        for (SceneApi.SmartHomeScene next : list) {
            if (!(next == null || next.l == null)) {
                for (SceneApi.Condition next2 : next.l) {
                    if (!(next2 == null || next2.h == null || TextUtils.isEmpty(next2.h.o))) {
                        if (next2.h.o.startsWith("enter_WG_")) {
                            str = next2.h.o.substring(SceneApi.ConditionELLocation.l.length());
                        } else if (next2.h.o.startsWith("leave_WG_")) {
                            str = next2.h.o.substring(SceneApi.ConditionELLocation.m.length());
                        }
                        if (!TextUtils.isEmpty(str)) {
                            JSONArray jSONArray = null;
                            if (!TextUtils.isEmpty(next2.h.n)) {
                                try {
                                    jSONArray = new JSONArray(next2.h.n);
                                } catch (JSONException e2) {
                                    e2.printStackTrace();
                                    jSONArray = new JSONArray();
                                }
                            }
                            hashMap.put(str, jSONArray);
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public void d() {
        List<SceneApi.SmartHomeScene> h = SceneManager.x().h();
        if (h != null && h.size() != 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(h);
            Map<String, JSONArray> b2 = b((List<SceneApi.SmartHomeScene>) arrayList);
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            for (Map.Entry next : this.d.entrySet()) {
                if (!b2.containsKey(next.getKey())) {
                    hashMap2.put(next.getKey(), next.getValue());
                }
            }
            a((Map<String, JSONArray>) hashMap2);
            for (Map.Entry next2 : b2.entrySet()) {
                if (!this.d.containsKey(next2.getKey())) {
                    hashMap.put(next2.getKey(), next2.getValue());
                }
            }
            b((Map<String, JSONArray>) hashMap);
            this.d = b2;
        }
    }

    /* access modifiers changed from: private */
    public void a(Map<String, JSONArray> map) {
        WIFIScanHomelogViaMIUI o;
        if (map != null && map.size() != 0 && (o = WifiScanHomelog.d().o()) != null) {
            for (Map.Entry<String, JSONArray> key : map.entrySet()) {
                o.b(new JSONArray(), (String) key.getKey());
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(Map<String, JSONArray> map) {
        WIFIScanHomelogViaMIUI o;
        if (map != null && map.size() != 0 && (o = WifiScanHomelog.d().o()) != null) {
            for (Map.Entry next : map.entrySet()) {
                o.b((JSONArray) next.getValue(), (String) next.getKey());
            }
        }
    }

    public void b() {
        try {
            SceneManager.x().b(this.g);
        } catch (Exception unused) {
        }
    }

    public String c() {
        String str;
        Random random = new Random();
        do {
            str = f21605a + (random.nextInt(1000000) + 1000);
        } while (this.d.containsKey(str));
        return str;
    }
}
