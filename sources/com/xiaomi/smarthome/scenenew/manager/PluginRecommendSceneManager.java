package com.xiaomi.smarthome.scenenew.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.smarthome.AppConfigHelper;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginRecommendSceneManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f21960a = 4;
    public static final int b = 1000;
    public static final String c = "plugin_recommend_scene_";
    private static PluginRecommendSceneManager e;
    private SharedPreferences d;

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject a(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject b(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    private PluginRecommendSceneManager() {
    }

    public static PluginRecommendSceneManager a() {
        if (e == null) {
            synchronized (PluginRecommendSceneManager.class) {
                if (e == null) {
                    e = new PluginRecommendSceneManager();
                }
            }
        }
        return e;
    }

    public void a(String str, final int i, final AsyncCallback<List<SceneApi.SmartHomeScene>, Error> asyncCallback) {
        RemoteSceneApi.a().a(SHApplication.getAppContext(), 4, 15, str, (String) null, false, (String) null, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                ArrayList arrayList = new ArrayList();
                if (jSONObject != null) {
                    int i = 0;
                    while (jSONObject.has(String.valueOf(i))) {
                        try {
                            SceneApi.SmartHomeScene a2 = SceneApi.SmartHomeScene.a(jSONObject.optJSONObject(String.valueOf(i)), false);
                            if (a2 != null) {
                                arrayList.add(a2);
                            }
                            i++;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        if (((SceneApi.SmartHomeScene) arrayList.get(size)).i != i) {
                            arrayList.remove(size);
                        }
                    }
                }
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(arrayList);
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public void b(String str, int i, AsyncCallback<PluginRecommendSceneInfo, Error> asyncCallback) {
        String a2 = a(c + str);
        if (!TextUtils.isEmpty(a2)) {
            try {
                PluginRecommendSceneInfo parse = PluginRecommendSceneInfo.parse(new JSONObject(a2));
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(parse);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("api_version", 4);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/scene/get_rec_in_plugin").b((List<KeyValuePair>) arrayList).a(), new JsonParser(str) {
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final Object parse(JSONObject jSONObject) {
                    return PluginRecommendSceneManager.this.a(this.f$1, jSONObject);
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException e3) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), e3.getMessage()));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ PluginRecommendSceneInfo a(String str, JSONObject jSONObject) throws JSONException {
        a(c + str, jSONObject.toString());
        return PluginRecommendSceneInfo.parse(jSONObject);
    }

    public void a(String str, String str2, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/get_ble_keysetting").b((List<KeyValuePair>) arrayList).a(), $$Lambda$PluginRecommendSceneManager$Ve5KUiiG0nFgE6hHft6qlmzeeQ0.INSTANCE, Crypto.RC4, asyncCallback);
        } catch (JSONException e2) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), e2.getMessage()));
            }
        }
    }

    public void a(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        a((List<String>) arrayList, asyncCallback);
    }

    public void a(List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            for (String put : list) {
                jSONArray.put(put);
            }
            jSONObject.put("dids", jSONArray);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/get_ble_gatewayinfo").b((List<KeyValuePair>) arrayList).a(), $$Lambda$PluginRecommendSceneManager$XdBAgikl8WuM24vnhYGwmI7OFc.INSTANCE, Crypto.RC4, asyncCallback);
        } catch (JSONException e2) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), e2.getMessage()));
            }
        }
    }

    public void a(final AsyncCallback<JSONObject, Error> asyncCallback) {
        new AppConfigHelper(SHApplication.getAppContext()).a("scene_rec_result_temp_youpin", "1", "zh_CN", (AppConfigHelper.OnCacheHandler<JSONObject>) null, (AppConfigHelper.JsonAsyncHandler) new AppConfigHelper.JsonAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject, Response response) {
                if (asyncCallback != null && jSONObject.has("result") && jSONObject.optJSONObject("result") != null) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("result");
                    if (optJSONObject.has("content")) {
                        try {
                            asyncCallback.onSuccess(new JSONObject(optJSONObject.optString("content")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(error.a(), error.b()));
                }
            }
        });
    }

    public void a(String str, String str2, boolean z, boolean z2, AsyncCallback<List<JSONObject>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("sr_id", str);
            }
            jSONObject.put("need_blegateway", true);
            jSONObject.put("need_zigbeegateway", true);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/scene/get_scene_rec_product_url").b((List<KeyValuePair>) arrayList).a(), new JsonParser(str2, z, z2) {
            private final /* synthetic */ String f$0;
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ boolean f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object parse(JSONObject jSONObject) {
                return PluginRecommendSceneManager.a(this.f$0, this.f$1, this.f$2, jSONObject);
            }
        }, Crypto.RC4, asyncCallback);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List a(String str, boolean z, boolean z2, JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        ArrayList arrayList = new ArrayList();
        JSONObject optJSONObject3 = jSONObject.optJSONObject(str.toLowerCase());
        if (optJSONObject3 != null) {
            Iterator<String> keys = optJSONObject3.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject optJSONObject4 = optJSONObject3.optJSONObject(next);
                if (optJSONObject4 != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(next, optJSONObject4.optString("good_url"));
                    arrayList.add(jSONObject2);
                }
            }
        }
        if (z && (optJSONObject2 = jSONObject.optJSONObject("BleGateway".toLowerCase())) != null) {
            Iterator<String> keys2 = optJSONObject2.keys();
            while (keys2.hasNext()) {
                String next2 = keys2.next();
                JSONObject optJSONObject5 = optJSONObject2.optJSONObject(next2);
                if (optJSONObject5 != null) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put(next2, optJSONObject5.optString("good_url"));
                    arrayList.add(jSONObject3);
                }
            }
        }
        if (z2 && (optJSONObject = jSONObject.optJSONObject("ZigbeeGateway".toLowerCase())) != null) {
            Iterator<String> keys3 = optJSONObject.keys();
            while (keys3.hasNext()) {
                String next3 = keys3.next();
                JSONObject optJSONObject6 = optJSONObject.optJSONObject(next3);
                if (optJSONObject6 != null) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put(next3, optJSONObject6.optString("good_url"));
                    arrayList.add(jSONObject4);
                }
            }
        }
        return arrayList;
    }

    public void a(String str, String str2) {
        if (this.d == null) {
            String a2 = MD5Util.a(CoreApi.a().s());
            if (!TextUtils.isEmpty(a2)) {
                Context appContext = SHApplication.getAppContext();
                this.d = appContext.getSharedPreferences(a2 + "scene_list_cache", 0);
            }
        }
        if (this.d != null) {
            SharedPreferences.Editor edit = this.d.edit();
            edit.putString(str, str2);
            edit.apply();
        }
    }

    public String a(String str) {
        if (this.d == null) {
            String a2 = MD5Util.a(CoreApi.a().s());
            if (!TextUtils.isEmpty(a2)) {
                Context appContext = SHApplication.getAppContext();
                this.d = appContext.getSharedPreferences(a2 + "scene_list_cache", 0);
            }
        }
        return this.d != null ? this.d.getString(str, "") : "";
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.content.Intent a(android.content.Context r7, java.lang.String r8, java.lang.String r9) {
        /*
            r6 = this;
            r0 = 0
            long r1 = java.lang.Long.parseLong(r9)     // Catch:{ Exception -> 0x008f }
            r3 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r3
            r3 = 2
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r1 = 1
            if (r5 != 0) goto L_0x002b
            android.content.Intent r2 = new android.content.Intent     // Catch:{ Exception -> 0x008f }
            java.lang.Class<com.xiaomi.smarthome.scenenew.pluginrecommend.CreateSceneFromRecommendActivity> r3 = com.xiaomi.smarthome.scenenew.pluginrecommend.CreateSceneFromRecommendActivity.class
            r2.<init>(r7, r3)     // Catch:{ Exception -> 0x008f }
            java.lang.String r7 = "need_choose_detail"
            r2.putExtra(r7, r1)     // Catch:{ Exception -> 0x008f }
            java.lang.String r7 = "sr_id"
            java.lang.Integer r1 = new java.lang.Integer     // Catch:{ Exception -> 0x008f }
            r1.<init>(r9)     // Catch:{ Exception -> 0x008f }
            r2.putExtra(r7, r1)     // Catch:{ Exception -> 0x008f }
            java.lang.String r7 = "did"
            r2.putExtra(r7, r8)     // Catch:{ Exception -> 0x008f }
            return r2
        L_0x002b:
            r2 = -1
            int r3 = r9.hashCode()     // Catch:{ Exception -> 0x008f }
            switch(r3) {
                case 1507423: goto L_0x0051;
                case 1507424: goto L_0x0048;
                case 1507425: goto L_0x003e;
                case 1507426: goto L_0x0034;
                default: goto L_0x0033;
            }     // Catch:{ Exception -> 0x008f }
        L_0x0033:
            goto L_0x005b
        L_0x0034:
            java.lang.String r1 = "1003"
            boolean r1 = r9.equals(r1)     // Catch:{ Exception -> 0x008f }
            if (r1 == 0) goto L_0x005b
            r1 = 3
            goto L_0x005c
        L_0x003e:
            java.lang.String r1 = "1002"
            boolean r1 = r9.equals(r1)     // Catch:{ Exception -> 0x008f }
            if (r1 == 0) goto L_0x005b
            r1 = 2
            goto L_0x005c
        L_0x0048:
            java.lang.String r3 = "1001"
            boolean r3 = r9.equals(r3)     // Catch:{ Exception -> 0x008f }
            if (r3 == 0) goto L_0x005b
            goto L_0x005c
        L_0x0051:
            java.lang.String r1 = "1000"
            boolean r1 = r9.equals(r1)     // Catch:{ Exception -> 0x008f }
            if (r1 == 0) goto L_0x005b
            r1 = 0
            goto L_0x005c
        L_0x005b:
            r1 = -1
        L_0x005c:
            switch(r1) {
                case 0: goto L_0x0078;
                case 1: goto L_0x0061;
                case 2: goto L_0x0061;
                case 3: goto L_0x0061;
                default: goto L_0x005f;
            }     // Catch:{ Exception -> 0x008f }
        L_0x005f:
            r1 = r0
            goto L_0x008e
        L_0x0061:
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x008f }
            java.lang.Class<com.xiaomi.smarthome.scenenew.pluginrecommend.LightActionStartActivity> r2 = com.xiaomi.smarthome.scenenew.pluginrecommend.LightActionStartActivity.class
            r1.<init>(r7, r2)     // Catch:{ Exception -> 0x008f }
            java.lang.String r7 = "sr_id"
            java.lang.Integer r2 = new java.lang.Integer     // Catch:{ Exception -> 0x008f }
            r2.<init>(r9)     // Catch:{ Exception -> 0x008f }
            r1.putExtra(r7, r2)     // Catch:{ Exception -> 0x008f }
            java.lang.String r7 = "did"
            r1.putExtra(r7, r8)     // Catch:{ Exception -> 0x008f }
            goto L_0x008e
        L_0x0078:
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x008f }
            java.lang.Class<com.xiaomi.smarthome.scenenew.actiivity.PluginRecommendSceneActivity> r2 = com.xiaomi.smarthome.scenenew.actiivity.PluginRecommendSceneActivity.class
            r1.<init>(r7, r2)     // Catch:{ Exception -> 0x008f }
            java.lang.String r7 = "sr_id"
            java.lang.Integer r2 = new java.lang.Integer     // Catch:{ Exception -> 0x008f }
            r2.<init>(r9)     // Catch:{ Exception -> 0x008f }
            r1.putExtra(r7, r2)     // Catch:{ Exception -> 0x008f }
            java.lang.String r7 = "did"
            r1.putExtra(r7, r8)     // Catch:{ Exception -> 0x008f }
        L_0x008e:
            return r1
        L_0x008f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scenenew.manager.PluginRecommendSceneManager.a(android.content.Context, java.lang.String, java.lang.String):android.content.Intent");
    }
}
