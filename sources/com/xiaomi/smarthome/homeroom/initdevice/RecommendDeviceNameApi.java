package com.xiaomi.smarthome.homeroom.initdevice;

import com.mi.global.shop.model.Tags;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.miio.LanguageUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecommendDeviceNameApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18303a = "pref_recommend_config_names";
    private static final String b = "RecommendDeviceNameApi";
    /* access modifiers changed from: private */
    public Map<String, OnRecommendNameListener> c = new HashMap();
    private String d;
    /* access modifiers changed from: private */
    public JSONArray e;

    public interface OnRecommendNameListener {
        void onRecommendName(String str, List<String> list);
    }

    public RecommendDeviceNameApi() {
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                RecommendDeviceNameApi.this.a();
            }
        });
    }

    public void a(String str, OnRecommendNameListener onRecommendNameListener) {
        if (str == null) {
            return;
        }
        if (this.e == null) {
            this.c.put(str, onRecommendNameListener);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(str, onRecommendNameListener);
        a((Map<String, OnRecommendNameListener>) hashMap);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0081 A[SYNTHETIC, Splitter:B:39:0x0081] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00db A[SYNTHETIC, Splitter:B:47:0x00db] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r6 = this;
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.String r1 = "recommend_device_name_config"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
            r1 = 0
            java.lang.String r2 = "pref_recommend_config_names"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getString(r2, r3)     // Catch:{ Exception -> 0x0079, all -> 0x0076 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0079, all -> 0x0076 }
            if (r3 == 0) goto L_0x0042
            android.content.Context r2 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ Exception -> 0x0079, all -> 0x0076 }
            android.content.res.AssetManager r2 = r2.getAssets()     // Catch:{ Exception -> 0x0079, all -> 0x0076 }
            java.lang.String r3 = "cardControl/recommend_deivce_name.json"
            java.io.InputStream r2 = r2.open(r3)     // Catch:{ Exception -> 0x0079, all -> 0x0076 }
            int r3 = r2.available()     // Catch:{ Exception -> 0x003f, all -> 0x003b }
            byte[] r3 = new byte[r3]     // Catch:{ Exception -> 0x003f, all -> 0x003b }
            r2.read(r3)     // Catch:{ Exception -> 0x003f, all -> 0x003b }
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x003f, all -> 0x003b }
            java.lang.String r5 = "utf8"
            r4.<init>(r3, r5)     // Catch:{ Exception -> 0x003f, all -> 0x003b }
            r3 = r2
            r2 = r4
            goto L_0x0043
        L_0x003b:
            r0 = move-exception
            r3 = r2
            goto L_0x00d9
        L_0x003f:
            r1 = move-exception
            r3 = r2
            goto L_0x007c
        L_0x0042:
            r3 = r1
        L_0x0043:
            boolean r2 = r6.a((java.lang.String) r2)     // Catch:{ Exception -> 0x0074 }
            if (r2 == 0) goto L_0x006c
            org.json.JSONArray r2 = r6.e     // Catch:{ Exception -> 0x0074 }
            if (r2 == 0) goto L_0x006c
            org.json.JSONArray r2 = r6.e     // Catch:{ Exception -> 0x0074 }
            int r2 = r2.length()     // Catch:{ Exception -> 0x0074 }
            if (r2 == 0) goto L_0x006c
            java.lang.String r1 = "RecommendDeviceNameApi"
            java.lang.String r2 = "use cache"
            com.xiaomi.smarthome.framework.log.LogUtil.c(r1, r2)     // Catch:{ Exception -> 0x0074 }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.homeroom.initdevice.RecommendDeviceNameApi$OnRecommendNameListener> r1 = r6.c     // Catch:{ Exception -> 0x0074 }
            r1.clear()     // Catch:{ Exception -> 0x0074 }
            if (r3 == 0) goto L_0x006b
            r3.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x006b
        L_0x0067:
            r0 = move-exception
            r0.printStackTrace()
        L_0x006b:
            return
        L_0x006c:
            r6.e = r1     // Catch:{ Exception -> 0x0074 }
            if (r3 == 0) goto L_0x0089
            r3.close()     // Catch:{ IOException -> 0x0085 }
            goto L_0x0089
        L_0x0074:
            r1 = move-exception
            goto L_0x007c
        L_0x0076:
            r0 = move-exception
            r3 = r1
            goto L_0x00d9
        L_0x0079:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L_0x007c:
            r1.printStackTrace()     // Catch:{ all -> 0x00d8 }
            if (r3 == 0) goto L_0x0089
            r3.close()     // Catch:{ IOException -> 0x0085 }
            goto L_0x0089
        L_0x0085:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0089:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            android.content.Context r2 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.String r3 = "home.mi.com"
            java.lang.String r4 = "st.iot.home.mi.com"
            java.lang.String r2 = com.xiaomi.smarthome.setting.ServerRouteUtil.a(r2, r3, r4)
            r1.append(r2)
            java.lang.String r2 = "/newoperation/recommendNames"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "RecommendDeviceNameApi"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "request "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.c(r2, r3)
            com.xiaomi.smarthome.library.http.Request$Builder r2 = new com.xiaomi.smarthome.library.http.Request$Builder
            r2.<init>()
            java.lang.String r3 = "GET"
            com.xiaomi.smarthome.library.http.Request$Builder r2 = r2.a((java.lang.String) r3)
            com.xiaomi.smarthome.library.http.Request$Builder r1 = r2.b((java.lang.String) r1)
            com.xiaomi.smarthome.library.http.Request r1 = r1.a()
            com.xiaomi.smarthome.homeroom.initdevice.RecommendDeviceNameApi$2 r2 = new com.xiaomi.smarthome.homeroom.initdevice.RecommendDeviceNameApi$2
            r2.<init>(r0)
            com.xiaomi.smarthome.library.http.HttpApi.a((com.xiaomi.smarthome.library.http.Request) r1, (com.xiaomi.smarthome.library.http.async.AsyncHandler) r2)
            return
        L_0x00d8:
            r0 = move-exception
        L_0x00d9:
            if (r3 == 0) goto L_0x00e3
            r3.close()     // Catch:{ IOException -> 0x00df }
            goto L_0x00e3
        L_0x00df:
            r1 = move-exception
            r1.printStackTrace()
        L_0x00e3:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.initdevice.RecommendDeviceNameApi.a():void");
    }

    /* access modifiers changed from: private */
    public boolean a(String str) {
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("data");
            this.e = optJSONObject.optJSONArray(Tags.Kuwan.PRODUCT_TYPE);
            a(this.c);
            if (optJSONObject.optLong("expireTs") > System.currentTimeMillis()) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
            LogUtil.b(b, "parseResult:" + str);
            return false;
        }
    }

    private void a(Map<String, OnRecommendNameListener> map) {
        if (this.e != null) {
            int i = 0;
            while (i < this.e.length()) {
                try {
                    JSONObject optJSONObject = this.e.optJSONObject(i);
                    JSONArray optJSONArray = optJSONObject.optJSONArray("model");
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        for (Map.Entry next : map.entrySet()) {
                            if (((String) next.getKey()).equals(optJSONArray.optString(i2))) {
                                a(optJSONObject, (OnRecommendNameListener) next.getValue());
                                return;
                            }
                        }
                    }
                    i++;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }
    }

    private void a(JSONObject jSONObject, final OnRecommendNameListener onRecommendNameListener) {
        if (jSONObject != null) {
            Locale I = CoreApi.a().I();
            if (I == null) {
                I = Locale.getDefault();
            }
            final String str = "";
            JSONArray jSONArray = null;
            JSONObject optJSONObject = jSONObject.optJSONObject("defaultName");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("recommend");
            try {
                if (LanguageUtil.a(I, Locale.CHINA)) {
                    if (optJSONObject != null) {
                        str = optJSONObject.optString("zh_CN");
                    }
                    if (optJSONObject2 != null) {
                        jSONArray = optJSONObject2.optJSONArray("zh_CN");
                    }
                } else if (LanguageUtil.a(I, Locale.TRADITIONAL_CHINESE)) {
                    if (optJSONObject != null) {
                        str = optJSONObject.optString("zh_TW");
                    }
                    if (optJSONObject2 != null) {
                        jSONArray = optJSONObject2.optJSONArray("zh_TW");
                    }
                } else if (LanguageUtil.a(I, LanguageUtil.v)) {
                    if (optJSONObject != null) {
                        str = optJSONObject.optString("zh_HK");
                    }
                    if (optJSONObject2 != null) {
                        jSONArray = optJSONObject2.optJSONArray("zh_HK");
                    }
                } else {
                    String language = I.getLanguage();
                    if (LanguageUtil.a(I, new Locale("in", "ID"))) {
                        language = "id";
                    }
                    if (optJSONObject != null) {
                        str = optJSONObject.optString(language);
                    }
                    if (optJSONObject2 != null) {
                        jSONArray = optJSONObject2.optJSONArray(language);
                    }
                }
                final ArrayList arrayList = new ArrayList();
                if (jSONArray != null) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(jSONArray.optString(i));
                    }
                }
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        onRecommendNameListener.onRecommendName(str, arrayList);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
