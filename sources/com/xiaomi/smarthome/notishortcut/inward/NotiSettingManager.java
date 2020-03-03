package com.xiaomi.smarthome.notishortcut.inward;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.http.KeyValuePair;
import com.xiaomi.smarthome.notishortcut.AccountManager;
import com.xiaomi.smarthome.notishortcut.DeviceConfigManager;
import com.xiaomi.smarthome.notishortcut.NetUtil;
import com.xiaomi.smarthome.notishortcut.NotiSmartService;
import com.xiaomi.smarthome.notishortcut.SPHelper;
import com.xiaomi.smarthome.notishortcut.SmartNotiApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotiSettingManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1563a = "NotiSettingManager";
    public static Map<String, PropConfig> c = new HashMap();
    private static volatile NotiSettingManager g;
    public List<NotiItem> b = new ArrayList();
    public boolean d;
    public Map<String, Bitmap> e = new ConcurrentHashMap();
    public Map<String, Integer> f = new HashMap();
    /* access modifiers changed from: private */
    public Context h;

    private NotiSettingManager(Context context) {
        this.h = context;
        LogUtil.b(f1563a, "NotiSettingManager " + System.currentTimeMillis());
        try {
            LogUtil.c(f1563a, "NotiSettingManager-->Fresco.initialize...");
            Fresco.initialize(context);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.d = DeviceConfigManager.b(context);
        if (this.d) {
            LogUtil.a(f1563a, "NotiSettingManager updateFromLocal");
            a(false);
        }
    }

    public static NotiSettingManager a(Context context) {
        if (g == null) {
            synchronized (NotiSettingManager.class) {
                if (g == null) {
                    g = new NotiSettingManager(context);
                }
            }
        }
        return g;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00de, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(boolean r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            android.content.Context r0 = r7.h     // Catch:{ all -> 0x00df }
            boolean r0 = com.xiaomi.smarthome.notishortcut.SmartNotiApi.b((android.content.Context) r0)     // Catch:{ all -> 0x00df }
            if (r0 == 0) goto L_0x0012
            boolean r0 = r7.d     // Catch:{ all -> 0x00df }
            if (r0 == 0) goto L_0x0012
            r7.f()     // Catch:{ all -> 0x00df }
            monitor-exit(r7)
            return
        L_0x0012:
            boolean r0 = r7.d     // Catch:{ all -> 0x00df }
            if (r0 != 0) goto L_0x0018
            monitor-exit(r7)
            return
        L_0x0018:
            android.content.Context r0 = r7.h     // Catch:{ all -> 0x00df }
            java.lang.String r0 = com.xiaomi.smarthome.notishortcut.DeviceConfigManager.d(r0)     // Catch:{ all -> 0x00df }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x00df }
            if (r1 != 0) goto L_0x00dd
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00d4 }
            r1.<init>(r0)     // Catch:{ JSONException -> 0x00d4 }
            java.lang.String r0 = "config"
            org.json.JSONArray r0 = r1.optJSONArray(r0)     // Catch:{ JSONException -> 0x00d4 }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$PropConfig> r2 = c     // Catch:{ JSONException -> 0x00d4 }
            r2.clear()     // Catch:{ JSONException -> 0x00d4 }
            r2 = 0
            r3 = 0
        L_0x0036:
            int r4 = r0.length()     // Catch:{ JSONException -> 0x00d4 }
            if (r3 >= r4) goto L_0x004e
            org.json.JSONObject r4 = r0.optJSONObject(r3)     // Catch:{ JSONException -> 0x00d4 }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$PropConfig r4 = com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.PropConfig.a((org.json.JSONObject) r4)     // Catch:{ JSONException -> 0x00d4 }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$PropConfig> r5 = c     // Catch:{ JSONException -> 0x00d4 }
            java.lang.String r6 = r4.f21030a     // Catch:{ JSONException -> 0x00d4 }
            r5.put(r6, r4)     // Catch:{ JSONException -> 0x00d4 }
            int r3 = r3 + 1
            goto L_0x0036
        L_0x004e:
            java.lang.String r0 = "list"
            org.json.JSONArray r0 = r1.optJSONArray(r0)     // Catch:{ JSONException -> 0x00d4 }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r1 = r7.b     // Catch:{ JSONException -> 0x00d4 }
            r1.clear()     // Catch:{ JSONException -> 0x00d4 }
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ JSONException -> 0x00d4 }
            r1.<init>()     // Catch:{ JSONException -> 0x00d4 }
        L_0x005e:
            int r3 = r0.length()     // Catch:{ JSONException -> 0x00d4 }
            if (r2 >= r3) goto L_0x00aa
            org.json.JSONObject r3 = r0.optJSONObject(r2)     // Catch:{ JSONException -> 0x00d4 }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r3 = com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem.b(r3)     // Catch:{ JSONException -> 0x00d4 }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r4 = r7.b     // Catch:{ JSONException -> 0x00d4 }
            r4.add(r3)     // Catch:{ JSONException -> 0x00d4 }
            java.lang.String r4 = r3.i     // Catch:{ JSONException -> 0x00d4 }
            r1.add(r4)     // Catch:{ JSONException -> 0x00d4 }
            java.lang.String r4 = r3.i     // Catch:{ JSONException -> 0x00d4 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ JSONException -> 0x00d4 }
            if (r4 != 0) goto L_0x0083
            java.lang.String r4 = r3.i     // Catch:{ JSONException -> 0x00d4 }
            r7.c(r4)     // Catch:{ JSONException -> 0x00d4 }
        L_0x0083:
            java.lang.String r4 = r3.j     // Catch:{ JSONException -> 0x00d4 }
            r1.add(r4)     // Catch:{ JSONException -> 0x00d4 }
            java.lang.String r4 = r3.j     // Catch:{ JSONException -> 0x00d4 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ JSONException -> 0x00d4 }
            if (r4 != 0) goto L_0x0095
            java.lang.String r4 = r3.j     // Catch:{ JSONException -> 0x00d4 }
            r7.c(r4)     // Catch:{ JSONException -> 0x00d4 }
        L_0x0095:
            java.lang.String r4 = r3.k     // Catch:{ JSONException -> 0x00d4 }
            r1.add(r4)     // Catch:{ JSONException -> 0x00d4 }
            java.lang.String r4 = r3.k     // Catch:{ JSONException -> 0x00d4 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ JSONException -> 0x00d4 }
            if (r4 != 0) goto L_0x00a7
            java.lang.String r3 = r3.k     // Catch:{ JSONException -> 0x00d4 }
            r7.c(r3)     // Catch:{ JSONException -> 0x00d4 }
        L_0x00a7:
            int r2 = r2 + 1
            goto L_0x005e
        L_0x00aa:
            java.util.Map<java.lang.String, android.graphics.Bitmap> r0 = r7.e     // Catch:{ JSONException -> 0x00d4 }
            java.util.Set r0 = r0.entrySet()     // Catch:{ JSONException -> 0x00d4 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ JSONException -> 0x00d4 }
        L_0x00b4:
            boolean r2 = r0.hasNext()     // Catch:{ JSONException -> 0x00d4 }
            if (r2 == 0) goto L_0x00d8
            java.lang.Object r2 = r0.next()     // Catch:{ JSONException -> 0x00d4 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ JSONException -> 0x00d4 }
            java.lang.Object r3 = r2.getKey()     // Catch:{ JSONException -> 0x00d4 }
            boolean r3 = r1.contains(r3)     // Catch:{ JSONException -> 0x00d4 }
            if (r3 != 0) goto L_0x00b4
            java.util.Map<java.lang.String, android.graphics.Bitmap> r3 = r7.e     // Catch:{ JSONException -> 0x00d4 }
            java.lang.Object r2 = r2.getKey()     // Catch:{ JSONException -> 0x00d4 }
            r3.remove(r2)     // Catch:{ JSONException -> 0x00d4 }
            goto L_0x00b4
        L_0x00d4:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00df }
        L_0x00d8:
            if (r8 == 0) goto L_0x00dd
            r7.g()     // Catch:{ all -> 0x00df }
        L_0x00dd:
            monitor-exit(r7)
            return
        L_0x00df:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.a(boolean):void");
    }

    public boolean a(String str, JSONArray jSONArray) {
        return a(NotiItem.a(a(str), jSONArray));
    }

    public synchronized boolean a(NotiItem notiItem) {
        if (notiItem == null) {
            return false;
        }
        for (int i = 0; i < this.b.size(); i++) {
            if (TextUtils.equals(this.b.get(i).f1564a, notiItem.f1564a)) {
                this.b.get(i).b = notiItem.b;
                this.b.get(i).d = notiItem.d;
                this.b.get(i).e = notiItem.e;
                if (!TextUtils.isEmpty(notiItem.c) && c.get(notiItem.c) != null) {
                    if (notiItem.e) {
                        this.b.get(i).m = c.get(notiItem.c).g;
                        this.b.get(i).l = c.get(notiItem.c).e;
                    } else {
                        this.b.get(i).m = c.get(notiItem.c).h;
                        this.b.get(i).l = c.get(notiItem.c).f;
                    }
                }
                b();
                return true;
            }
        }
        return false;
    }

    public void a() {
        if (this.d) {
            String c2 = DeviceConfigManager.c(this.h);
            if (!TextUtils.isEmpty(c2)) {
                try {
                    JSONArray jSONArray = new JSONArray(c2);
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("dids", jSONArray);
                    } catch (JSONException unused) {
                        LogUtil.b(f1563a, "-999  : JSONException");
                    }
                    jSONObject.put("get_online_status", true);
                    jSONObject.put("get_props", true);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new KeyValuePair("data", jSONObject.toString()));
                    NetUtil.a(this.h).a(new NetUtil.MyRequest.Builder().a("/device/deviceinfo").b("GET").a((List<KeyValuePair>) arrayList).a(), (NetUtil.MyCallback) new NetUtil.MyCallback() {
                        public void a(int i, String str) {
                            if (i == 0) {
                                try {
                                    JSONArray optJSONArray = new JSONObject(str).optJSONArray("list");
                                    if (optJSONArray == null) {
                                        return;
                                    }
                                    if (optJSONArray.length() != 0) {
                                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                            NotiItem a2 = NotiItem.a(optJSONArray.getJSONObject(i2));
                                            NotiSettingManager.this.a(a2);
                                            Intent intent = new Intent(NotiSettingManager.this.h, QuickOpServiceNew.class);
                                            intent.setAction(QuickOpServiceNew.ACTION_NOTIFICATION_UPDATE);
                                            intent.putExtra("device_id", a2.f1564a);
                                            if (Build.VERSION.SDK_INT >= 26) {
                                                NotiSmartService.getAppContext().startForegroundService(intent);
                                            } else {
                                                NotiSmartService.getAppContext().startService(intent);
                                            }
                                            LogUtil.a(NotiSettingManager.f1563a, "updateDevicesFromRemote onSuccess");
                                        }
                                    }
                                } catch (JSONException unused) {
                                    LogUtil.b(NotiSettingManager.f1563a, "JSONException");
                                }
                            } else {
                                LogUtil.b(NotiSettingManager.f1563a, i + " : " + str);
                            }
                        }

                        public void b(int i, String str) {
                            LogUtil.b(NotiSettingManager.f1563a, i + " : " + str);
                        }
                    });
                } catch (Exception unused2) {
                    LogUtil.b(f1563a, "-999  : dids is not exist!");
                }
            }
        }
    }

    private String b(String str, String str2) {
        JSONArray jSONArray;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
            jSONObject.put("method", str);
            if (!TextUtils.isEmpty(str2)) {
                jSONArray = new JSONArray(str2);
            } else {
                jSONArray = new JSONArray();
            }
            jSONObject.put("params", jSONArray);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    public void b() {
        JSONArray jSONArray = new JSONArray();
        for (NotiItem a2 : this.b) {
            jSONArray.put(NotiItem.a(a2));
        }
        if (this.b == null || this.b.size() == 0) {
            DeviceConfigManager.a(this.h);
        } else {
            JSONArray jSONArray2 = new JSONArray();
            for (Map.Entry<String, PropConfig> value : c.entrySet()) {
                jSONArray2.put(PropConfig.a((PropConfig) value.getValue()));
            }
            DeviceConfigManager.a(this.h, jSONArray, jSONArray2);
            DeviceConfigManager.a(this.h, this.d);
        }
        LogUtil.a(f1563a, "回写成功");
    }

    public synchronized void c() {
        if (g != null) {
            NetUtil a2 = NetUtil.a(this.h);
            if (a2 != null) {
                a2.b();
            }
            g = null;
            LogUtil.b(f1563a, "NotiSettingManager clear" + System.currentTimeMillis());
        }
    }

    public synchronized void d() {
        if (this.b != null) {
            for (int i = 0; i < this.b.size(); i++) {
                this.b.get(i).f = false;
                this.b.get(i).g = 0;
            }
            this.f.clear();
        }
    }

    public NotiItem a(String str) {
        for (int i = 0; i < this.b.size(); i++) {
            if (TextUtils.equals(this.b.get(i).f1564a, str)) {
                return this.b.get(i);
            }
        }
        return null;
    }

    public boolean b(String str) {
        return a(str) != null;
    }

    static class PropConfig {

        /* renamed from: a  reason: collision with root package name */
        String f21030a;
        String b;
        String c;
        String d;
        String e;
        String f;
        String g;
        String h;

        PropConfig() {
        }

        public static PropConfig a(JSONObject jSONObject) {
            PropConfig propConfig = new PropConfig();
            propConfig.f21030a = jSONObject.optString("model");
            propConfig.b = jSONObject.optString("prop_name");
            propConfig.c = jSONObject.optString("prop_on");
            propConfig.d = jSONObject.optString("prop_off");
            propConfig.e = jSONObject.optString("rpc_method_on");
            propConfig.f = jSONObject.optString("rpc_method_off");
            propConfig.g = jSONObject.optString("prop_params_on");
            propConfig.h = jSONObject.optString("prop_params_off");
            return propConfig;
        }

        public static JSONObject a(PropConfig propConfig) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("model", propConfig.f21030a);
                jSONObject.put("prop_on", propConfig.c);
                if (!TextUtils.isEmpty(propConfig.g)) {
                    jSONObject.put("prop_params_on", propConfig.g);
                }
                if (!TextUtils.isEmpty(propConfig.h)) {
                    jSONObject.put("prop_params_off", propConfig.h);
                }
                jSONObject.put("prop_off", propConfig.d);
                jSONObject.put("prop_name", propConfig.b);
                jSONObject.put("rpc_method_on", propConfig.e);
                jSONObject.put("rpc_method_off", propConfig.f);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }
    }

    static class NotiItem {

        /* renamed from: a  reason: collision with root package name */
        String f1564a;
        String b;
        String c;
        boolean d;
        boolean e;
        boolean f;
        int g;
        String h;
        String i;
        String j;
        String k;
        String l;
        String m;

        NotiItem() {
        }

        public static NotiItem a(JSONObject jSONObject) {
            NotiItem notiItem = new NotiItem();
            notiItem.f1564a = jSONObject.optString("did");
            notiItem.b = jSONObject.optString("name");
            notiItem.c = jSONObject.optString("model");
            notiItem.d = jSONObject.optInt("is_online") != 0;
            JSONObject optJSONObject = jSONObject.optJSONObject("props");
            if (!(optJSONObject == null || NotiSettingManager.c.get(notiItem.c) == null)) {
                String str = NotiSettingManager.c.get(notiItem.c).b;
                String str2 = NotiSettingManager.c.get(notiItem.c).c;
                if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                    return notiItem;
                }
                if (TextUtils.equals(str2, optJSONObject.optString("prop." + str))) {
                    notiItem.e = true;
                    notiItem.m = NotiSettingManager.c.get(notiItem.c).g;
                    notiItem.l = NotiSettingManager.c.get(notiItem.c).e;
                } else {
                    notiItem.e = false;
                    notiItem.m = NotiSettingManager.c.get(notiItem.c).h;
                    notiItem.l = NotiSettingManager.c.get(notiItem.c).f;
                }
            }
            return notiItem;
        }

        public static NotiItem a(NotiItem notiItem, JSONArray jSONArray) {
            if (!(jSONArray == null || NotiSettingManager.c.get(notiItem.c) == null)) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                    if (NotiSettingManager.c.get(notiItem.c) != null) {
                        String str = NotiSettingManager.c.get(notiItem.c).b;
                        String optString = optJSONObject.optString("key");
                        String str2 = NotiSettingManager.c.get(notiItem.c).c;
                        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                            return notiItem;
                        }
                        if (!TextUtils.equals("prop." + str, optString)) {
                            return notiItem;
                        }
                        if (optJSONObject.has("value")) {
                            JSONArray optJSONArray = optJSONObject.optJSONArray("value");
                            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                                if (TextUtils.equals(str2, optJSONArray.optString(i3))) {
                                    notiItem.e = true;
                                    notiItem.m = NotiSettingManager.c.get(notiItem.c).g;
                                    notiItem.l = NotiSettingManager.c.get(notiItem.c).e;
                                    return notiItem;
                                }
                            }
                            notiItem.e = false;
                            notiItem.m = NotiSettingManager.c.get(notiItem.c).h;
                            notiItem.l = NotiSettingManager.c.get(notiItem.c).f;
                        } else {
                            continue;
                        }
                    }
                }
            }
            return notiItem;
        }

        public static NotiItem b(JSONObject jSONObject) {
            NotiItem notiItem = new NotiItem();
            notiItem.f1564a = jSONObject.optString("id");
            notiItem.b = jSONObject.optString("name");
            notiItem.c = jSONObject.optString("model");
            notiItem.d = jSONObject.optBoolean("is_online");
            notiItem.j = jSONObject.optString("ic_off");
            notiItem.i = jSONObject.optString("ic_on");
            notiItem.k = jSONObject.optString("ic_offline");
            JSONObject optJSONObject = jSONObject.optJSONObject("props");
            if (!(optJSONObject == null || TextUtils.isEmpty(notiItem.c) || NotiSettingManager.c.get(notiItem.c) == null)) {
                if (TextUtils.equals(NotiSettingManager.c.get(notiItem.c).c, optJSONObject.optString(NotiSettingManager.c.get(notiItem.c).b))) {
                    notiItem.e = true;
                    notiItem.m = NotiSettingManager.c.get(notiItem.c).g;
                    notiItem.l = NotiSettingManager.c.get(notiItem.c).e;
                } else {
                    notiItem.e = false;
                    notiItem.m = NotiSettingManager.c.get(notiItem.c).h;
                    notiItem.l = NotiSettingManager.c.get(notiItem.c).f;
                }
            }
            return notiItem;
        }

        public static JSONObject a(NotiItem notiItem) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", notiItem.f1564a);
                jSONObject.put("name", notiItem.b);
                jSONObject.put("model", notiItem.c);
                jSONObject.put("is_online", notiItem.d);
                jSONObject.put("ic_on", notiItem.i);
                jSONObject.put("ic_off", notiItem.j);
                jSONObject.put("ic_offline", notiItem.k);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(NotiSettingManager.c.get(notiItem.c).b, notiItem.e ? NotiSettingManager.c.get(notiItem.c).c : NotiSettingManager.c.get(notiItem.c).d);
                jSONObject.put("props", jSONObject2);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }
    }

    public synchronized void e() {
        for (NotiItem next : this.b) {
            if (!TextUtils.isEmpty(next.i)) {
                c(next.i);
            }
            if (!TextUtils.isEmpty(next.j)) {
                c(next.j);
            }
            if (!TextUtils.isEmpty(next.k)) {
                c(next.k);
            }
        }
    }

    private void c(final String str) {
        if (!this.e.containsKey(str) || this.e.get(str).isRecycled()) {
            if (this.e.containsKey(str) && this.e.get(str).isRecycled()) {
                this.e.remove(str);
            }
            Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setProgressiveRenderingEnabled(true).build(), this.h).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
                /* access modifiers changed from: protected */
                public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                    LogUtil.b(NotiSettingManager.f1563a, "图片下载失败");
                }

                /* access modifiers changed from: protected */
                public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                    CloseableReference result = dataSource.getResult();
                    if (result != null) {
                        CloseableImage closeableImage = (CloseableImage) result.get();
                        if (closeableImage instanceof CloseableBitmap) {
                            NotiSettingManager.this.e.put(str, ((CloseableBitmap) closeableImage).getUnderlyingBitmap());
                            if (NotiSettingManager.this.d) {
                                NotiSettingManager.this.g();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    LogUtil.b(NotiSettingManager.f1563a, "result null");
                }
            }, CallerThreadExecutor.getInstance());
        }
    }

    public boolean a(String str, String str2) {
        String a2 = AccountManager.a(this.h);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        Context context = this.h;
        if (!SPHelper.a(context, "noti_device_setting" + MD5.a(a2), "devices", str)) {
            return false;
        }
        Context context2 = this.h;
        if (SPHelper.a(context2, DeviceConfigManager.f1559a + MD5.a(a2), "dids", str2)) {
            Context context3 = this.h;
            if (SPHelper.a(context3, DeviceConfigManager.f1559a + MD5.a(a2), "is_open", "true")) {
                this.d = true;
                a(true);
                return true;
            }
        }
        return false;
    }

    public void a(final int i) {
        a(this.h, this.b.get(i).f1564a, b(this.b.get(i).l, this.b.get(i).m), new SmartNotiApi.Callback() {
            public void a(String str) {
                if (i < NotiSettingManager.this.b.size()) {
                    NotiSettingManager.this.b.get(i).e = !NotiSettingManager.this.b.get(i).e;
                    NotiSettingManager.this.b();
                    NotiSettingManager.this.a(i, 0);
                }
            }

            public void a(int i, String str) {
                NotiSettingManager.this.a(i, i);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x013e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0199, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x019e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(final int r6, int r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 < 0) goto L_0x019d
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r0 = r5.b     // Catch:{ all -> 0x019a }
            int r0 = r0.size()     // Catch:{ all -> 0x019a }
            if (r6 < r0) goto L_0x000d
            goto L_0x019d
        L_0x000d:
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r0 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r0 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r0     // Catch:{ all -> 0x019a }
            r1 = 0
            r0.f = r1     // Catch:{ all -> 0x019a }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r0 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r0 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r0     // Catch:{ all -> 0x019a }
            r2 = 2
            r0.g = r2     // Catch:{ all -> 0x019a }
            r0 = -4005001(0xffffffffffc2e377, float:NaN)
            r2 = 1000(0x3e8, double:4.94E-321)
            if (r7 == r0) goto L_0x015c
            r0 = -2
            if (r7 == r0) goto L_0x013f
            if (r7 == 0) goto L_0x007f
            r0 = 401(0x191, float:5.62E-43)
            if (r7 == r0) goto L_0x0047
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r7 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r7 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r7     // Catch:{ all -> 0x019a }
            android.content.Context r0 = r5.h     // Catch:{ all -> 0x019a }
            int r4 = com.xiaomi.smarthome.notishortcut.R.string.execute_fail     // Catch:{ all -> 0x019a }
            java.lang.String r0 = r0.getString(r4)     // Catch:{ all -> 0x019a }
            r7.h = r0     // Catch:{ all -> 0x019a }
            goto L_0x0176
        L_0x0047:
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r7 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r7 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r7     // Catch:{ all -> 0x019a }
            android.content.Context r0 = r5.h     // Catch:{ all -> 0x019a }
            int r4 = com.xiaomi.smarthome.notishortcut.R.string.noti_token_expired     // Catch:{ all -> 0x019a }
            java.lang.String r0 = r0.getString(r4)     // Catch:{ all -> 0x019a }
            r7.h = r0     // Catch:{ all -> 0x019a }
            android.content.Intent r7 = new android.content.Intent     // Catch:{ all -> 0x019a }
            android.content.Context r0 = r5.h     // Catch:{ all -> 0x019a }
            java.lang.Class<com.xiaomi.smarthome.notishortcut.inward.QuickOpServiceNew> r4 = com.xiaomi.smarthome.notishortcut.inward.QuickOpServiceNew.class
            r7.<init>(r0, r4)     // Catch:{ all -> 0x019a }
            java.lang.String r0 = "auth_expired"
            r7.setAction(r0)     // Catch:{ all -> 0x019a }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x019a }
            r4 = 26
            if (r0 < r4) goto L_0x0076
            android.content.Context r0 = com.xiaomi.smarthome.notishortcut.NotiSmartService.getAppContext()     // Catch:{ all -> 0x019a }
            r0.startForegroundService(r7)     // Catch:{ all -> 0x019a }
            goto L_0x0176
        L_0x0076:
            android.content.Context r0 = com.xiaomi.smarthome.notishortcut.NotiSmartService.getAppContext()     // Catch:{ all -> 0x019a }
            r0.startService(r7)     // Catch:{ all -> 0x019a }
            goto L_0x0176
        L_0x007f:
            java.util.Map<java.lang.String, java.lang.Integer> r7 = r5.f     // Catch:{ all -> 0x019a }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r0 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r0 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r0     // Catch:{ all -> 0x019a }
            java.lang.String r0 = r0.f1564a     // Catch:{ all -> 0x019a }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x019a }
            r7.put(r0, r1)     // Catch:{ all -> 0x019a }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r7 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r7 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r7     // Catch:{ all -> 0x019a }
            r0 = 1
            r7.d = r0     // Catch:{ all -> 0x019a }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r7 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r7 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r7     // Catch:{ all -> 0x019a }
            java.lang.String r7 = r7.c     // Catch:{ all -> 0x019a }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r0 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r0 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r0     // Catch:{ all -> 0x019a }
            boolean r0 = r0.e     // Catch:{ all -> 0x019a }
            if (r0 == 0) goto L_0x00ee
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r0 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r0 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r0     // Catch:{ all -> 0x019a }
            android.content.Context r1 = r5.h     // Catch:{ all -> 0x019a }
            int r4 = com.xiaomi.smarthome.notishortcut.R.string.noti_device_on     // Catch:{ all -> 0x019a }
            java.lang.String r1 = r1.getString(r4)     // Catch:{ all -> 0x019a }
            r0.h = r1     // Catch:{ all -> 0x019a }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r0 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r0 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r0     // Catch:{ all -> 0x019a }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$PropConfig> r1 = c     // Catch:{ all -> 0x019a }
            java.lang.Object r1 = r1.get(r7)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$PropConfig r1 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.PropConfig) r1     // Catch:{ all -> 0x019a }
            java.lang.String r1 = r1.g     // Catch:{ all -> 0x019a }
            r0.m = r1     // Catch:{ all -> 0x019a }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r0 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r0 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r0     // Catch:{ all -> 0x019a }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$PropConfig> r1 = c     // Catch:{ all -> 0x019a }
            java.lang.Object r7 = r1.get(r7)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$PropConfig r7 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.PropConfig) r7     // Catch:{ all -> 0x019a }
            java.lang.String r7 = r7.e     // Catch:{ all -> 0x019a }
            r0.l = r7     // Catch:{ all -> 0x019a }
            goto L_0x0128
        L_0x00ee:
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r0 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r0 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r0     // Catch:{ all -> 0x019a }
            android.content.Context r1 = r5.h     // Catch:{ all -> 0x019a }
            int r4 = com.xiaomi.smarthome.notishortcut.R.string.noti_device_off     // Catch:{ all -> 0x019a }
            java.lang.String r1 = r1.getString(r4)     // Catch:{ all -> 0x019a }
            r0.h = r1     // Catch:{ all -> 0x019a }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r0 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r0 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r0     // Catch:{ all -> 0x019a }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$PropConfig> r1 = c     // Catch:{ all -> 0x019a }
            java.lang.Object r1 = r1.get(r7)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$PropConfig r1 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.PropConfig) r1     // Catch:{ all -> 0x019a }
            java.lang.String r1 = r1.h     // Catch:{ all -> 0x019a }
            r0.m = r1     // Catch:{ all -> 0x019a }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r0 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r0 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r0     // Catch:{ all -> 0x019a }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$PropConfig> r1 = c     // Catch:{ all -> 0x019a }
            java.lang.Object r7 = r1.get(r7)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$PropConfig r7 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.PropConfig) r7     // Catch:{ all -> 0x019a }
            java.lang.String r7 = r7.f     // Catch:{ all -> 0x019a }
            r0.l = r7     // Catch:{ all -> 0x019a }
        L_0x0128:
            r5.g()     // Catch:{ all -> 0x019a }
            android.os.Handler r7 = com.xiaomi.smarthome.notishortcut.NotiSmartService.getHandler()     // Catch:{ all -> 0x019a }
            if (r7 == 0) goto L_0x013d
            android.os.Handler r7 = com.xiaomi.smarthome.notishortcut.NotiSmartService.getHandler()     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$4 r0 = new com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$4     // Catch:{ all -> 0x019a }
            r0.<init>(r6)     // Catch:{ all -> 0x019a }
            r7.postDelayed(r0, r2)     // Catch:{ all -> 0x019a }
        L_0x013d:
            monitor-exit(r5)
            return
        L_0x013f:
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r7 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r7 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r7     // Catch:{ all -> 0x019a }
            android.content.Context r0 = r5.h     // Catch:{ all -> 0x019a }
            int r4 = com.xiaomi.smarthome.notishortcut.R.string.noti_device_offline     // Catch:{ all -> 0x019a }
            java.lang.String r0 = r0.getString(r4)     // Catch:{ all -> 0x019a }
            r7.h = r0     // Catch:{ all -> 0x019a }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r7 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r7 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r7     // Catch:{ all -> 0x019a }
            r7.d = r1     // Catch:{ all -> 0x019a }
            goto L_0x0176
        L_0x015c:
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r7 = r5.b     // Catch:{ all -> 0x019a }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem r7 = (com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.NotiItem) r7     // Catch:{ all -> 0x019a }
            android.content.Context r0 = r5.h     // Catch:{ all -> 0x019a }
            int r4 = com.xiaomi.smarthome.notishortcut.R.string.noti_device_expired     // Catch:{ all -> 0x019a }
            java.lang.String r0 = r0.getString(r4)     // Catch:{ all -> 0x019a }
            r7.h = r0     // Catch:{ all -> 0x019a }
            r5.g()     // Catch:{ all -> 0x019a }
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r7 = r5.b     // Catch:{ all -> 0x019a }
            r7.remove(r6)     // Catch:{ all -> 0x019a }
        L_0x0176:
            java.util.List<com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$NotiItem> r7 = r5.b     // Catch:{ all -> 0x019a }
            int r7 = r7.size()     // Catch:{ all -> 0x019a }
            if (r7 > 0) goto L_0x0180
            r5.d = r1     // Catch:{ all -> 0x019a }
        L_0x0180:
            r5.b()     // Catch:{ all -> 0x019a }
            r5.g()     // Catch:{ all -> 0x019a }
            android.os.Handler r7 = com.xiaomi.smarthome.notishortcut.NotiSmartService.getHandler()     // Catch:{ all -> 0x019a }
            if (r7 == 0) goto L_0x0198
            android.os.Handler r7 = com.xiaomi.smarthome.notishortcut.NotiSmartService.getHandler()     // Catch:{ all -> 0x019a }
            com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$5 r0 = new com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager$5     // Catch:{ all -> 0x019a }
            r0.<init>(r6)     // Catch:{ all -> 0x019a }
            r7.postDelayed(r0, r2)     // Catch:{ all -> 0x019a }
        L_0x0198:
            monitor-exit(r5)
            return
        L_0x019a:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L_0x019d:
            monitor-exit(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager.a(int, int):void");
    }

    public void f() {
        if (NotiSmartService.getAppContext() != null) {
            Intent intent = new Intent(this.h, QuickOpServiceNew.class);
            intent.setAction(QuickOpServiceNew.ACTION_NOTIFICATION_CANCEL);
            if (Build.VERSION.SDK_INT >= 26) {
                NotiSmartService.getAppContext().startForegroundService(intent);
            } else {
                NotiSmartService.getAppContext().startService(intent);
            }
        }
    }

    public void g() {
        if (NotiSmartService.getAppContext() != null) {
            Intent intent = new Intent(this.h, QuickOpServiceNew.class);
            intent.setAction(QuickOpServiceNew.ACTION_NOTIFICATION_UPDATE);
            if (Build.VERSION.SDK_INT >= 26) {
                NotiSmartService.getAppContext().startForegroundService(intent);
            } else {
                NotiSmartService.getAppContext().startService(intent);
            }
        }
    }

    private void a(Context context, String str, String str2, final SmartNotiApi.Callback callback) {
        LogUtil.a(f1563a, "rpc did: " + str);
        LogUtil.a(f1563a, str2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", str2));
        try {
            NetUtil.MyRequest.Builder builder = new NetUtil.MyRequest.Builder();
            NetUtil.a(context).a(builder.a("/home/rpc/" + str).b("POST").a((List<KeyValuePair>) arrayList).a(), (NetUtil.MyCallback) new NetUtil.MyCallback() {
                public void a(int i, String str) {
                    LogUtil.a(NotiSettingManager.f1563a, "rpc code: " + i);
                    LogUtil.a(NotiSettingManager.f1563a, "rpc result: " + str);
                    if (i == 0) {
                        if (callback != null) {
                            callback.a(str);
                        }
                    } else if (callback != null) {
                        SmartNotiApi.Callback callback = callback;
                        callback.a(i, "message: " + str);
                        LogUtil.a(NotiSettingManager.f1563a, "rpc business error: code: " + i + " ,message: " + str);
                    }
                }

                public void b(int i, String str) {
                    if (callback != null) {
                        SmartNotiApi.Callback callback = callback;
                        callback.a(i, "msg: " + str);
                        LogUtil.a(NotiSettingManager.f1563a, "rpc onError: code: " + i + " ,message: " + str);
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (callback != null) {
                callback.a(-999, "Request Fail!");
                LogUtil.a(f1563a, "rpc onError: " + e2.getMessage());
            }
        }
    }
}
