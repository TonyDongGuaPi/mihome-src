package com.xiaomi.smarthome.wificonfig;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.config.WifiConnectionConfig;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.UserApi;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.miio.activity.HomeLogContants;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WifiLogManager {

    /* renamed from: a  reason: collision with root package name */
    private static WifiLogManager f22927a = null;
    private static final int b = 1;
    private static final int c = 2;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public List<IListener> e = new ArrayList();
    /* access modifiers changed from: private */
    public Handler f = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                for (IListener a2 : WifiLogManager.this.e) {
                    a2.a();
                }
                boolean unused = WifiLogManager.this.d = false;
            }
        }
    };

    public interface IListener {
        void a();
    }

    public void a(boolean z) {
    }

    public static WifiLogManager a() {
        if (f22927a == null) {
            f22927a = new WifiLogManager();
        }
        return f22927a;
    }

    private WifiLogManager() {
    }

    public void a(IListener iListener) {
        this.e.remove(iListener);
        this.e.add(iListener);
    }

    public void b(IListener iListener) {
        this.e.remove(iListener);
    }

    /* access modifiers changed from: package-private */
    public void b(final boolean z) {
        final MessageHandlerThread messageHandlerThread = new MessageHandlerThread("sync_thread");
        messageHandlerThread.start();
        Handler handler = new Handler();
        handler.post(new Runnable() {
            public void run() {
                if (!CoreApi.a().q()) {
                    boolean unused = WifiLogManager.this.d = false;
                } else if (TextUtils.isEmpty(SHConfig.a().c(HomeLogContants.f11749a)) || z) {
                    UserApi.a().a(SHApplication.getAppContext(), WifiLogManager.this.b(), (AsyncCallback<Map<String, String>, Error>) new AsyncCallback<Map<String, String>, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Map<String, String> map) {
                            WifiLogManager.this.a(map.get(HomeLogContants.f11749a));
                            WifiLogManager.this.f.sendEmptyMessage(1);
                            messageHandlerThread.quit();
                        }

                        public void onFailure(Error error) {
                            WifiLogManager.this.f.sendEmptyMessage(1);
                            messageHandlerThread.quit();
                        }
                    });
                } else {
                    WifiLogManager.this.f.sendEmptyMessage(1);
                    boolean unused2 = WifiLogManager.this.d = false;
                }
            }
        });
        handler.sendEmptyMessageDelayed(2, 6000);
    }

    /* access modifiers changed from: package-private */
    public void a(TreeMap<String, String> treeMap, TreeMap<String, String> treeMap2) {
        Iterator<Map.Entry<String, String>> it = treeMap.entrySet().iterator();
        Iterator<Map.Entry<String, String>> it2 = treeMap2.entrySet().iterator();
        TreeMap treeMap3 = new TreeMap();
        TreeMap treeMap4 = new TreeMap();
        Map.Entry entry = null;
        Map.Entry entry2 = null;
        loop0:
        while (true) {
            boolean z = true;
            while (true) {
                boolean z2 = true;
                while (true) {
                    if (it2.hasNext() && it.hasNext()) {
                        if (z) {
                            entry = it2.next();
                        }
                        if (z2) {
                            entry2 = it.next();
                        }
                        if (Math.abs((Long.valueOf((String) entry.getKey()).longValue() / 1000) - (Long.valueOf((String) entry2.getKey()).longValue() / 1000)) >= 1) {
                            if (Long.valueOf((String) entry.getKey()).longValue() - Long.valueOf((String) entry2.getKey()).longValue() < 0) {
                                break;
                            }
                            treeMap4.put(entry.getKey(), entry.getValue());
                            z = true;
                            z2 = false;
                        }
                    } else if (!(entry == null || entry2 == null || Math.abs((Long.valueOf((String) entry.getKey()).longValue() / 1000) - (Long.valueOf((String) entry2.getKey()).longValue() / 1000)) < 1)) {
                        treeMap3.put(entry2.getKey(), entry2.getValue());
                        treeMap4.put(entry.getKey(), entry.getValue());
                    }
                }
                treeMap3.put(entry2.getKey(), entry2.getValue());
                z = false;
            }
        }
        treeMap3.put(entry2.getKey(), entry2.getValue());
        treeMap4.put(entry.getKey(), entry.getValue());
        while (it.hasNext()) {
            Map.Entry next = it.next();
            treeMap3.put(next.getKey(), next.getValue());
        }
        while (it2.hasNext()) {
            Map.Entry next2 = it2.next();
            treeMap4.put(next2.getKey(), next2.getValue());
        }
        for (Map.Entry entry3 : treeMap3.entrySet()) {
            WifiConnectionConfig.a().a((String) entry3.getKey(), (String) entry3.getValue());
        }
        for (Map.Entry entry4 : treeMap4.entrySet()) {
            a(Long.valueOf((String) entry4.getKey()).longValue() / 1000, HomeLogContants.h, (String) entry4.getValue());
        }
    }

    /* access modifiers changed from: package-private */
    public void a(long j, String str, String str2) {
        if (CoreApi.a().q()) {
            final String str3 = str;
            final long j2 = j;
            final String str4 = str2;
            SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                public void run() {
                    UserApi.a().a(SHApplication.getAppContext(), str3, j2, str4, new AsyncCallback<Long, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Long l) {
                        }

                        public void onFailure(Error error) {
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r9) {
        /*
            r8 = this;
            if (r9 != 0) goto L_0x0003
            return
        L_0x0003:
            com.xiaomi.smarthome.config.SHConfig r0 = com.xiaomi.smarthome.config.SHConfig.a()
            java.lang.String r1 = "wifi_location_config"
            java.lang.String r0 = r0.c(r1)
            r1 = 0
            long r3 = r8.b()
            r5 = 0
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch:{ JSONException -> 0x002b }
            r6.<init>(r9)     // Catch:{ JSONException -> 0x002b }
            r9 = 0
            org.json.JSONObject r9 = r6.getJSONObject(r9)     // Catch:{ JSONException -> 0x002b }
            java.lang.String r6 = "time"
            long r6 = r9.optLong(r6)     // Catch:{ JSONException -> 0x002b }
            java.lang.String r1 = "value"
            java.lang.String r9 = r9.optString(r1)     // Catch:{ JSONException -> 0x002c }
            goto L_0x002d
        L_0x002b:
            r6 = r1
        L_0x002c:
            r9 = r5
        L_0x002d:
            boolean r1 = android.text.TextUtils.isEmpty(r9)
            if (r1 != 0) goto L_0x0075
            int r1 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x0038
            goto L_0x0075
        L_0x0038:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x005d
            int r1 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0043
            goto L_0x005d
        L_0x0043:
            boolean r9 = r0.equals(r9)
            if (r9 != 0) goto L_0x0096
            long r0 = r8.b()
            java.lang.String r9 = "wifi_location_config"
            com.xiaomi.smarthome.config.SHConfig r2 = com.xiaomi.smarthome.config.SHConfig.a()
            java.lang.String r3 = "wifi_location_config"
            java.lang.String r2 = r2.c(r3)
            r8.a(r0, r9, r2)
            goto L_0x0096
        L_0x005d:
            com.xiaomi.smarthome.config.SHConfig r0 = com.xiaomi.smarthome.config.SHConfig.a()
            java.lang.String r1 = "wifi_location_config"
            r0.a((java.lang.String) r1, (java.lang.String) r9)
            android.content.Context r9 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "reset_wifi_location"
            r0.<init>(r1)
            r9.sendBroadcast(r0)
            goto L_0x0096
        L_0x0075:
            com.xiaomi.smarthome.config.SHConfig r9 = com.xiaomi.smarthome.config.SHConfig.a()
            java.lang.String r0 = "set_location"
            int r9 = r9.a(r0)
            r0 = -1
            if (r9 != r0) goto L_0x0083
            return
        L_0x0083:
            long r0 = r8.b()
            java.lang.String r9 = "wifi_location_config"
            com.xiaomi.smarthome.config.SHConfig r2 = com.xiaomi.smarthome.config.SHConfig.a()
            java.lang.String r3 = "wifi_location_config"
            java.lang.String r2 = r2.c(r3)
            r8.a(r0, r9, r2)
        L_0x0096:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.wificonfig.WifiLogManager.a(java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public long b() {
        String c2 = SHConfig.a().c(HomeLogContants.f11749a);
        if (c2 != null) {
            try {
                JSONArray jSONArray = new JSONArray(c2);
                long j = 0;
                for (int i = 0; i < jSONArray.length(); i++) {
                    long optLong = jSONArray.getJSONObject(0).optLong(HomeLogContants.e) / 1000;
                    if (optLong > j) {
                        j = optLong;
                    }
                }
                return j;
            } catch (JSONException unused) {
            }
        }
        return 0;
    }

    public void c() {
        a(b(), HomeLogContants.f11749a, "");
    }

    public void d() {
        String c2 = SHConfig.a().c(HomeLogContants.f11749a);
        if (c2 != null) {
            try {
                JSONArray jSONArray = new JSONArray(c2);
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        jSONArray.getJSONObject(i).put(HomeLogContants.e, System.currentTimeMillis());
                    }
                }
                SHConfig.a().a(HomeLogContants.f11749a, jSONArray.toString());
                a(b(), HomeLogContants.f11749a, SHConfig.a().c(HomeLogContants.f11749a));
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public TreeMap<String, String> b(String str) {
        TreeMap<String, String> treeMap = new TreeMap<>(new Comparator() {
            public int compare(Object obj, Object obj2) {
                if (obj == null || obj2 == null) {
                    return 0;
                }
                return String.valueOf(obj2).compareTo(String.valueOf(obj));
            }
        });
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                treeMap.put(String.valueOf(Long.valueOf(jSONObject.optString("time")).longValue() * 1000), jSONObject.optString("value"));
            }
        } catch (JSONException unused) {
        }
        return treeMap;
    }
}
