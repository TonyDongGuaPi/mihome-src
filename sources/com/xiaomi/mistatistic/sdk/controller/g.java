package com.xiaomi.mistatistic.sdk.controller;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.mistatistic.sdk.BuildSetting;
import com.xiaomi.mistatistic.sdk.controller.asyncjobs.a;
import com.xiaomi.mistatistic.sdk.controller.d;
import com.xiaomi.mistatistic.sdk.controller.j;
import com.xiaomi.mistatistic.sdk.data.HttpEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class g {

    /* renamed from: a  reason: collision with root package name */
    private static g f12026a = new g();
    private HttpEventFilter b = new HttpEventFilter() {
        public HttpEvent a(HttpEvent httpEvent) {
            String a2 = httpEvent.a();
            if (TextUtils.isEmpty(a2)) {
                return null;
            }
            httpEvent.b(a2.split("\\?")[0]);
            return httpEvent;
        }
    };
    /* access modifiers changed from: private */
    public List<HttpEvent> c = new LinkedList();
    private Handler d = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1023) {
                d.b().a((d.a) new d.a() {
                    public void a() {
                        final List<HttpEvent> list;
                        if (g.this.c()) {
                            try {
                                List<HttpEvent> b = g.this.b();
                                int size = b.size();
                                if (size > 0) {
                                    int i = 0;
                                    while (i < size) {
                                        int i2 = i + 30;
                                        if (i2 >= size) {
                                            list = b.subList(i, size);
                                        } else {
                                            list = b.subList(i, i2);
                                        }
                                        g.this.a(list, (j.b) new j.b() {
                                            /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
                                            /* JADX WARNING: Removed duplicated region for block: B:9:0x003b A[Catch:{ Exception -> 0x0074 }] */
                                            /* Code decompiled incorrectly, please refer to instructions dump. */
                                            public void a(java.lang.String r3) {
                                                /*
                                                    r2 = this;
                                                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                                                    r0.<init>()
                                                    java.lang.String r1 = "http data complete, result="
                                                    r0.append(r1)
                                                    r0.append(r3)
                                                    java.lang.String r0 = r0.toString()
                                                    com.xiaomi.mistatistic.sdk.controller.h.a(r0)
                                                    boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x0074 }
                                                    if (r0 != 0) goto L_0x0038
                                                    org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0074 }
                                                    r0.<init>(r3)     // Catch:{ Exception -> 0x0074 }
                                                    java.lang.String r3 = "ok"
                                                    java.lang.String r1 = "status"
                                                    java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x0074 }
                                                    boolean r3 = r3.equals(r1)     // Catch:{ Exception -> 0x0074 }
                                                    if (r3 == 0) goto L_0x0038
                                                    com.xiaomi.mistatistic.sdk.controller.g$2$1 r3 = com.xiaomi.mistatistic.sdk.controller.g.AnonymousClass2.AnonymousClass1.this     // Catch:{ Exception -> 0x0074 }
                                                    com.xiaomi.mistatistic.sdk.controller.g$2 r3 = com.xiaomi.mistatistic.sdk.controller.g.AnonymousClass2.this     // Catch:{ Exception -> 0x0074 }
                                                    com.xiaomi.mistatistic.sdk.controller.g r3 = com.xiaomi.mistatistic.sdk.controller.g.this     // Catch:{ Exception -> 0x0074 }
                                                    r3.a((org.json.JSONObject) r0)     // Catch:{ Exception -> 0x0074 }
                                                    r3 = 1
                                                    goto L_0x0039
                                                L_0x0038:
                                                    r3 = 0
                                                L_0x0039:
                                                    if (r3 == 0) goto L_0x007a
                                                    com.xiaomi.mistatistic.sdk.controller.g$2$1 r3 = com.xiaomi.mistatistic.sdk.controller.g.AnonymousClass2.AnonymousClass1.this     // Catch:{ Exception -> 0x0074 }
                                                    com.xiaomi.mistatistic.sdk.controller.g$2 r3 = com.xiaomi.mistatistic.sdk.controller.g.AnonymousClass2.this     // Catch:{ Exception -> 0x0074 }
                                                    com.xiaomi.mistatistic.sdk.controller.g r3 = com.xiaomi.mistatistic.sdk.controller.g.this     // Catch:{ Exception -> 0x0074 }
                                                    java.util.List r3 = r3.c     // Catch:{ Exception -> 0x0074 }
                                                    monitor-enter(r3)     // Catch:{ Exception -> 0x0074 }
                                                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
                                                    r0.<init>()     // Catch:{ all -> 0x0071 }
                                                    java.lang.String r1 = "upload success, synchronizing remove http events "
                                                    r0.append(r1)     // Catch:{ all -> 0x0071 }
                                                    java.util.List r1 = r2     // Catch:{ all -> 0x0071 }
                                                    int r1 = r1.size()     // Catch:{ all -> 0x0071 }
                                                    r0.append(r1)     // Catch:{ all -> 0x0071 }
                                                    java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0071 }
                                                    com.xiaomi.mistatistic.sdk.controller.h.a(r0)     // Catch:{ all -> 0x0071 }
                                                    com.xiaomi.mistatistic.sdk.controller.g$2$1 r0 = com.xiaomi.mistatistic.sdk.controller.g.AnonymousClass2.AnonymousClass1.this     // Catch:{ all -> 0x0071 }
                                                    com.xiaomi.mistatistic.sdk.controller.g$2 r0 = com.xiaomi.mistatistic.sdk.controller.g.AnonymousClass2.this     // Catch:{ all -> 0x0071 }
                                                    com.xiaomi.mistatistic.sdk.controller.g r0 = com.xiaomi.mistatistic.sdk.controller.g.this     // Catch:{ all -> 0x0071 }
                                                    java.util.List r0 = r0.c     // Catch:{ all -> 0x0071 }
                                                    java.util.List r1 = r2     // Catch:{ all -> 0x0071 }
                                                    r0.removeAll(r1)     // Catch:{ all -> 0x0071 }
                                                    monitor-exit(r3)     // Catch:{ all -> 0x0071 }
                                                    goto L_0x007a
                                                L_0x0071:
                                                    r0 = move-exception
                                                    monitor-exit(r3)     // Catch:{ all -> 0x0071 }
                                                    throw r0     // Catch:{ Exception -> 0x0074 }
                                                L_0x0074:
                                                    r3 = move-exception
                                                    java.lang.String r0 = "upload events response exception:"
                                                    com.xiaomi.mistatistic.sdk.controller.h.a((java.lang.String) r0, (java.lang.Throwable) r3)
                                                L_0x007a:
                                                    return
                                                */
                                                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mistatistic.sdk.controller.g.AnonymousClass2.AnonymousClass1.AnonymousClass1.a(java.lang.String):void");
                                            }
                                        });
                                        i = i2;
                                    }
                                }
                            } catch (IOException e) {
                                h.a("", (Throwable) e);
                            } catch (JSONException e2) {
                                h.a("", (Throwable) e2);
                            }
                        } else if (g.this.d()) {
                            try {
                                g.this.a(new JSONArray().toString(), (j.b) new j.b() {
                                    public void a(String str) {
                                        h.a("upload empty http events result:" + str);
                                    }
                                });
                            } catch (IOException e3) {
                                h.a("", (Throwable) e3);
                            } catch (JSONException e4) {
                                h.a("", (Throwable) e4);
                            }
                        }
                    }
                });
            }
        }
    };

    public static g a() {
        return f12026a;
    }

    private g() {
    }

    public void a(HttpEventFilter httpEventFilter) {
        this.b = httpEventFilter;
    }

    public List<HttpEvent> b() {
        LinkedList linkedList;
        synchronized (this.c) {
            linkedList = new LinkedList(this.c);
        }
        return linkedList;
    }

    public boolean c() {
        return System.currentTimeMillis() > k.a(c.a(), "rt_ban_time", 0) && Math.random() * 10000.0d <= ((double) k.a(c.a(), "rt_upload_rate", 10000));
    }

    public boolean d() {
        return System.currentTimeMillis() - k.a(c.a(), "rt_update_time", 0) > 86400000;
    }

    public void a(HttpEvent httpEvent) {
        Context a2 = c.a();
        if (a2 == null) {
            h.a("add http event without initialization.");
        } else if (BuildSetting.a(a2)) {
            h.a("disabled the http event upload");
        } else if (!a(httpEvent.a()) || BuildSetting.c()) {
            if (this.b != null && !httpEvent.a().equals(f())) {
                httpEvent = this.b.a(httpEvent);
            }
            if (httpEvent != null && !TextUtils.isEmpty(httpEvent.a())) {
                synchronized (this.c) {
                    this.c.add(httpEvent);
                    if (this.c.size() > 100) {
                        this.c.remove(0);
                    }
                }
                if (!this.d.hasMessages(1023) && !httpEvent.a().equals(f())) {
                    a.b();
                    this.d.sendEmptyMessageDelayed(1023, e());
                }
            }
        }
    }

    public long e() {
        return k.a(c.a(), "rt_upload_delay", 300000);
    }

    private String f() {
        return BuildSetting.a() ? "http://10.99.168.145:8097/realtime_network" : "https://data.mistat.xiaomi.com/realtime_network";
    }

    private boolean a(String str) {
        return str.equals(f()) || str.equals("https://data.mistat.xiaomi.com/micrash") || str.equals("https://data.mistat.xiaomi.com/mistats") || str.equals("http://data.mistat.xiaomi.com/mistats/v2") || str.equals("http://abtest.mistat.xiaomi.com/experiments");
    }

    /* access modifiers changed from: private */
    public void a(List<HttpEvent> list, j.b bVar) throws IOException, JSONException {
        HashMap hashMap = new HashMap();
        if (list != null && !list.isEmpty()) {
            for (HttpEvent next : list) {
                String a2 = next.a();
                if (!TextUtils.isEmpty(a2)) {
                    if (hashMap.containsKey(a2)) {
                        ((List) hashMap.get(a2)).add(next);
                    } else {
                        hashMap.put(a2, new ArrayList());
                        ((List) hashMap.get(a2)).add(next);
                    }
                }
            }
            if (!hashMap.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (String str : hashMap.keySet()) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (HttpEvent e : (List) hashMap.get(str)) {
                        jSONArray2.put(e.e());
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("url", str);
                    jSONObject.put("data", jSONArray2);
                    jSONArray.put(jSONObject);
                }
                a(jSONArray.toString(), bVar);
            }
        }
    }

    public void a(String str, j.b bVar) throws IOException, JSONException {
        TreeMap treeMap = new TreeMap();
        treeMap.put("app_id", c.b());
        treeMap.put("app_package", c.g());
        treeMap.put("app_key", c.c());
        treeMap.put("device_uuid", new e().a());
        treeMap.put("device_os", "android" + Build.VERSION.SDK_INT);
        treeMap.put("device_model", Build.MODEL);
        treeMap.put("app_version", c.e());
        treeMap.put("app_channel", c.d());
        treeMap.put("time", String.valueOf(System.currentTimeMillis()));
        treeMap.put("net_value", str);
        j.a(f(), treeMap, bVar);
    }

    public void a(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("data")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            int optInt = jSONObject2.optInt(IjkMediaMeta.IJKM_KEY_SAMPLE_RATE, 10000);
            int optInt2 = jSONObject2.optInt("delay", com.alipay.security.mobile.module.http.constant.a.f1173a);
            long optLong = jSONObject2.optLong("ban_time", 0);
            k.b(c.a(), "rt_upload_rate", optInt);
            k.b(c.a(), "rt_upload_delay", (long) optInt2);
            k.b(c.a(), "rt_ban_time", optLong);
            k.b(c.a(), "rt_update_time", System.currentTimeMillis());
        }
    }
}
