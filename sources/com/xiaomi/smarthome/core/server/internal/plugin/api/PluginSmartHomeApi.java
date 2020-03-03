package com.xiaomi.smarthome.core.server.internal.plugin.api;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.alipay.android.phone.a.a.a;
import com.alipay.sdk.util.i;
import com.mi.global.shop.util.PushRouteUtil;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.internal.CoreAsyncCallback;
import com.xiaomi.smarthome.core.server.internal.CoreAsyncHandle;
import com.xiaomi.smarthome.core.server.internal.CoreError;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.apiparser.CoreJsonParser;
import com.xiaomi.smarthome.core.server.internal.apiparser.CoreSmartHomeApiParser;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.core.server.internal.plugin.entity.PluginConfigInfoNewResult;
import com.xiaomi.smarthome.core.server.internal.plugin.entity.PluginUpdateInfoResult;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.setting.PluginSetting;
import com.xiaomi.smarthome.stat.STAT;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginSmartHomeApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14681a = "ModelToReplaceAllPluginForTestOnly";
    private static final Object b = new Object();
    private static PluginSmartHomeApi c = null;
    private static final String d = "old_plugin_only";

    private PluginSmartHomeApi() {
    }

    public static PluginSmartHomeApi a() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new PluginSmartHomeApi();
                }
            }
        }
        return c;
    }

    private void b() {
        if (Looper.myLooper() == null) {
            throw new RuntimeException("Async Callback must have Looper");
        }
    }

    public CoreAsyncHandle a(Context context, boolean z, long j, long j2, int i, String str, final CoreAsyncCallback<PluginConfigInfoNewResult, CoreError> coreAsyncCallback) {
        b();
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("devices_last_modify", j);
            jSONObject.put("developers_last_modify", j2);
            jSONObject.put("app_version", i);
            jSONObject.put("app_platform", str);
            jSONObject.put("isLocal", false);
            jSONObject.put("v", 2);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/plugin/get_config_info_new").b((List<KeyValuePair>) arrayList).a();
            final AnonymousClass1 r3 = new CoreJsonParser<PluginConfigInfoNewResult>() {
                /* renamed from: b */
                public PluginConfigInfoNewResult a(JSONObject jSONObject) throws JSONException {
                    return PluginConfigInfoNewResult.a(jSONObject);
                }
            };
            return new CoreAsyncHandle(SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                /* renamed from: a */
                public void b(NetResult netResult) {
                }

                /* renamed from: b */
                public void a(NetResult netResult) {
                    CoreSmartHomeApiParser.a().a(netResult, r3, coreAsyncCallback);
                }

                public void a(NetError netError) {
                    coreAsyncCallback.b(new CoreError(netError.a(), netError.b()));
                }
            }));
        } catch (JSONException unused) {
            if (coreAsyncCallback != null) {
                coreAsyncCallback.b(new CoreError(-1, ""));
            }
            return new CoreAsyncHandle(null);
        }
    }

    public CoreAsyncHandle a(Context context, JSONArray jSONArray, final CoreAsyncCallback<JSONArray, CoreError> coreAsyncCallback) {
        b();
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("platform", a.f813a);
            jSONObject.put(DTransferConstants.l, jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/plugin/fetch_sdk").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass3 r5 = new CoreJsonParser<JSONArray>() {
            /* renamed from: b */
            public JSONArray a(JSONObject jSONObject) throws JSONException {
                return jSONObject.getJSONArray("info");
            }
        };
        return new CoreAsyncHandle(SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                PluginSetting.a("fetch sdk info", netResult.c);
                CoreSmartHomeApiParser.a().a(netResult, r5, coreAsyncCallback);
            }

            public void a(NetError netError) {
                coreAsyncCallback.b(new CoreError(netError.a(), netError.b()));
            }
        }));
    }

    public static final void a(Context context, String str) {
        if (GlobalSetting.s || GlobalSetting.q) {
            FileUtils.a(new File(FileUtils.a(context), f14681a), str.getBytes());
        }
    }

    public static final void a(Context context, boolean z) {
        a(context, z ? d : "");
    }

    public static final boolean a(Context context) {
        return d.equals(b(context));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        r2 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.a(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.String b(android.content.Context r2) {
        /*
            boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.s
            if (r0 != 0) goto L_0x000c
            boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.q
            if (r0 == 0) goto L_0x0009
            goto L_0x000c
        L_0x0009:
            java.lang.String r2 = ""
            return r2
        L_0x000c:
            java.io.File r0 = new java.io.File
            if (r2 != 0) goto L_0x0014
            android.content.Context r2 = com.xiaomi.smarthome.core.server.CoreService.getAppContext()
        L_0x0014:
            java.lang.String r2 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.a((android.content.Context) r2)
            java.lang.String r1 = "ModelToReplaceAllPluginForTestOnly"
            r0.<init>(r2, r1)
            boolean r2 = r0.exists()
            if (r2 != 0) goto L_0x0026
            java.lang.String r2 = ""
            return r2
        L_0x0026:
            byte[] r2 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.a((java.io.File) r0)
            if (r2 == 0) goto L_0x0037
            int r0 = r2.length
            r1 = 1
            if (r0 >= r1) goto L_0x0031
            goto L_0x0037
        L_0x0031:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r2)
            return r0
        L_0x0037:
            java.lang.String r2 = ""
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.api.PluginSmartHomeApi.b(android.content.Context):java.lang.String");
    }

    public CoreAsyncHandle a(Context context, final JSONArray jSONArray, int i, int i2, String str, final CoreAsyncCallback<List<PluginUpdateInfoResult>, CoreError> coreAsyncCallback) {
        b();
        ArrayList arrayList = new ArrayList();
        final String b2 = b((Context) null);
        final boolean equals = d.equals(b2);
        final HashSet hashSet = new HashSet();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("api_version", PluginSetting.b);
            jSONObject2.put("app_platform", a.f813a);
            ServerBean F = CoreApi.a().F();
            if (F == null) {
                F = ServerCompact.b();
            }
            jSONObject2.put("region", F.b);
            if (!equals) {
                if (!b2.isEmpty()) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                        hashSet.add(jSONArray.optJSONObject(i3).optString("model"));
                    }
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("model", b2);
                    jSONArray2.put(jSONObject3);
                    jSONObject2.put("plugins", jSONArray2);
                    jSONObject2.put("package_type", "");
                    jSONObject.put("latest_req", jSONObject2);
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("plugins", jSONArray);
                    jSONObject4.put("api_level", i);
                    jSONObject4.put("app_platform", str);
                    jSONObject.put("backup_req", jSONObject4);
                    arrayList.add(new KeyValuePair("data", jSONObject.toString()));
                    NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/plugin/fetch_plugin").b((List<KeyValuePair>) arrayList).a();
                    final AnonymousClass5 r12 = new CoreJsonParser<List<PluginUpdateInfoResult>>() {
                        /* renamed from: b */
                        public List<PluginUpdateInfoResult> a(JSONObject jSONObject) throws JSONException {
                            JSONObject jSONObject2 = jSONObject;
                            ArrayList arrayList = new ArrayList();
                            JSONArray optJSONArray = equals ? null : jSONObject2.optJSONArray("latest_info");
                            HashMap hashMap = new HashMap();
                            if (!equals && optJSONArray != null) {
                                int length = optJSONArray.length();
                                PluginUpdateInfoResult pluginUpdateInfoResult = null;
                                for (int i = 0; i < length; i++) {
                                    PluginUpdateInfoResult pluginUpdateInfoResult2 = new PluginUpdateInfoResult();
                                    JSONObject jSONObject3 = (JSONObject) optJSONArray.get(i);
                                    pluginUpdateInfoResult2.k = "rn";
                                    pluginUpdateInfoResult2.f14696a = jSONObject3.optString("model");
                                    if (!TextUtils.isEmpty(pluginUpdateInfoResult2.f14696a)) {
                                        hashMap.put(pluginUpdateInfoResult2.f14696a, pluginUpdateInfoResult2);
                                        pluginUpdateInfoResult2.c = jSONObject3.optLong("plugin_id");
                                        if (PluginManager.a(pluginUpdateInfoResult2.c)) {
                                            pluginUpdateInfoResult2.d = jSONObject3.optLong("plugin_package_id");
                                            if (PluginManager.b(pluginUpdateInfoResult2.d)) {
                                                pluginUpdateInfoResult2.h = jSONObject3.optString("download_url");
                                                if (!TextUtils.isEmpty(pluginUpdateInfoResult2.h)) {
                                                    pluginUpdateInfoResult2.l = (long) jSONObject3.optInt(DTransferConstants.l);
                                                    pluginUpdateInfoResult2.f = jSONObject3.optInt("version");
                                                    pluginUpdateInfoResult2.b = 1;
                                                    pluginUpdateInfoResult2.g = jSONObject3.optString("change_log");
                                                    pluginUpdateInfoResult2.e = jSONObject3.optInt("force_update_status");
                                                    pluginUpdateInfoResult2.j = jSONObject3.optLong("size");
                                                    PluginSetting.a("fetch plugin:", pluginUpdateInfoResult2.f14696a, Long.valueOf(pluginUpdateInfoResult2.l), Long.valueOf(pluginUpdateInfoResult2.c), Long.valueOf(pluginUpdateInfoResult2.d));
                                                    if (b2.isEmpty() || !TextUtils.equals(b2, pluginUpdateInfoResult2.f14696a)) {
                                                        arrayList.add(pluginUpdateInfoResult2);
                                                    } else {
                                                        pluginUpdateInfoResult = pluginUpdateInfoResult2;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (pluginUpdateInfoResult != null) {
                                    for (String str : hashSet) {
                                        PluginUpdateInfoResult pluginUpdateInfoResult3 = new PluginUpdateInfoResult();
                                        pluginUpdateInfoResult3.f14696a = str;
                                        pluginUpdateInfoResult3.h = pluginUpdateInfoResult.h;
                                        pluginUpdateInfoResult3.l = pluginUpdateInfoResult.l;
                                        pluginUpdateInfoResult3.j = pluginUpdateInfoResult.j;
                                        pluginUpdateInfoResult3.b = pluginUpdateInfoResult.b;
                                        pluginUpdateInfoResult3.c = pluginUpdateInfoResult.c;
                                        pluginUpdateInfoResult3.d = pluginUpdateInfoResult.d;
                                        pluginUpdateInfoResult3.f = pluginUpdateInfoResult.f;
                                        pluginUpdateInfoResult3.g = pluginUpdateInfoResult.g;
                                        pluginUpdateInfoResult3.k = pluginUpdateInfoResult.k;
                                        PluginSetting.a("FETCH OLD plugin:", pluginUpdateInfoResult3.f14696a, pluginUpdateInfoResult3.k, Long.valueOf(pluginUpdateInfoResult3.c), Long.valueOf(pluginUpdateInfoResult3.d));
                                        arrayList.add(pluginUpdateInfoResult3);
                                    }
                                }
                                if (!b2.isEmpty()) {
                                    return arrayList;
                                }
                            }
                            JSONArray optJSONArray2 = jSONObject2.optJSONArray("backup_info");
                            if (optJSONArray2 != null) {
                                int length2 = optJSONArray2.length();
                                for (int i2 = 0; i2 < length2; i2++) {
                                    PluginUpdateInfoResult pluginUpdateInfoResult4 = new PluginUpdateInfoResult();
                                    JSONObject jSONObject4 = (JSONObject) optJSONArray2.get(i2);
                                    pluginUpdateInfoResult4.f14696a = jSONObject4.optString("model");
                                    if (((PluginUpdateInfoResult) hashMap.get(pluginUpdateInfoResult4.f14696a)) != null) {
                                        System.out.println("INFO: OLD PLUGIN INFO OF " + pluginUpdateInfoResult4.f14696a);
                                    } else {
                                        pluginUpdateInfoResult4.h = jSONObject4.optString("download_url");
                                        if (StringUtil.c(pluginUpdateInfoResult4.h)) {
                                            System.out.println("WARN: EMPTY PLUGIN INFO OF " + pluginUpdateInfoResult4.f14696a);
                                        } else {
                                            pluginUpdateInfoResult4.b = jSONObject4.optInt("new_version");
                                            pluginUpdateInfoResult4.c = jSONObject4.optLong("plugin_id");
                                            pluginUpdateInfoResult4.d = jSONObject4.optLong(PushRouteUtil.k);
                                            pluginUpdateInfoResult4.e = jSONObject4.optInt("force");
                                            pluginUpdateInfoResult4.f = jSONObject4.optInt("version");
                                            pluginUpdateInfoResult4.g = jSONObject4.optString("change_log");
                                            pluginUpdateInfoResult4.i = jSONObject4.optString("safe_url");
                                            pluginUpdateInfoResult4.j = jSONObject4.optLong("length");
                                            pluginUpdateInfoResult4.k = jSONObject4.optString("type");
                                            arrayList.add(pluginUpdateInfoResult4);
                                        }
                                    }
                                }
                            }
                            return arrayList;
                        }
                    };
                    return new CoreAsyncHandle(SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                        /* renamed from: a */
                        public void b(NetResult netResult) {
                        }

                        /* renamed from: b */
                        public void a(NetResult netResult) {
                            CoreSmartHomeApiParser.a().a(netResult, r12, coreAsyncCallback);
                        }

                        public void a(NetError netError) {
                            if (jSONArray != null) {
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    if (i != 0) {
                                        sb.append(i.b);
                                    }
                                    sb.append(jSONArray.optJSONObject(i).optString("model"));
                                }
                                STAT.i.c(2, sb.toString());
                            }
                            coreAsyncCallback.b(new CoreError(netError.a(), netError.b()));
                        }
                    }));
                }
            }
            jSONObject2.put("plugins", jSONArray);
            jSONObject2.put("package_type", "");
            jSONObject.put("latest_req", jSONObject2);
            JSONObject jSONObject42 = new JSONObject();
            jSONObject42.put("plugins", jSONArray);
            jSONObject42.put("api_level", i);
            jSONObject42.put("app_platform", str);
            jSONObject.put("backup_req", jSONObject42);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            NetRequest a22 = new NetRequest.Builder().a("POST").b("/v2/plugin/fetch_plugin").b((List<KeyValuePair>) arrayList).a();
            final AnonymousClass5 r122 = new CoreJsonParser<List<PluginUpdateInfoResult>>() {
                /* renamed from: b */
                public List<PluginUpdateInfoResult> a(JSONObject jSONObject) throws JSONException {
                    JSONObject jSONObject2 = jSONObject;
                    ArrayList arrayList = new ArrayList();
                    JSONArray optJSONArray = equals ? null : jSONObject2.optJSONArray("latest_info");
                    HashMap hashMap = new HashMap();
                    if (!equals && optJSONArray != null) {
                        int length = optJSONArray.length();
                        PluginUpdateInfoResult pluginUpdateInfoResult = null;
                        for (int i = 0; i < length; i++) {
                            PluginUpdateInfoResult pluginUpdateInfoResult2 = new PluginUpdateInfoResult();
                            JSONObject jSONObject3 = (JSONObject) optJSONArray.get(i);
                            pluginUpdateInfoResult2.k = "rn";
                            pluginUpdateInfoResult2.f14696a = jSONObject3.optString("model");
                            if (!TextUtils.isEmpty(pluginUpdateInfoResult2.f14696a)) {
                                hashMap.put(pluginUpdateInfoResult2.f14696a, pluginUpdateInfoResult2);
                                pluginUpdateInfoResult2.c = jSONObject3.optLong("plugin_id");
                                if (PluginManager.a(pluginUpdateInfoResult2.c)) {
                                    pluginUpdateInfoResult2.d = jSONObject3.optLong("plugin_package_id");
                                    if (PluginManager.b(pluginUpdateInfoResult2.d)) {
                                        pluginUpdateInfoResult2.h = jSONObject3.optString("download_url");
                                        if (!TextUtils.isEmpty(pluginUpdateInfoResult2.h)) {
                                            pluginUpdateInfoResult2.l = (long) jSONObject3.optInt(DTransferConstants.l);
                                            pluginUpdateInfoResult2.f = jSONObject3.optInt("version");
                                            pluginUpdateInfoResult2.b = 1;
                                            pluginUpdateInfoResult2.g = jSONObject3.optString("change_log");
                                            pluginUpdateInfoResult2.e = jSONObject3.optInt("force_update_status");
                                            pluginUpdateInfoResult2.j = jSONObject3.optLong("size");
                                            PluginSetting.a("fetch plugin:", pluginUpdateInfoResult2.f14696a, Long.valueOf(pluginUpdateInfoResult2.l), Long.valueOf(pluginUpdateInfoResult2.c), Long.valueOf(pluginUpdateInfoResult2.d));
                                            if (b2.isEmpty() || !TextUtils.equals(b2, pluginUpdateInfoResult2.f14696a)) {
                                                arrayList.add(pluginUpdateInfoResult2);
                                            } else {
                                                pluginUpdateInfoResult = pluginUpdateInfoResult2;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (pluginUpdateInfoResult != null) {
                            for (String str : hashSet) {
                                PluginUpdateInfoResult pluginUpdateInfoResult3 = new PluginUpdateInfoResult();
                                pluginUpdateInfoResult3.f14696a = str;
                                pluginUpdateInfoResult3.h = pluginUpdateInfoResult.h;
                                pluginUpdateInfoResult3.l = pluginUpdateInfoResult.l;
                                pluginUpdateInfoResult3.j = pluginUpdateInfoResult.j;
                                pluginUpdateInfoResult3.b = pluginUpdateInfoResult.b;
                                pluginUpdateInfoResult3.c = pluginUpdateInfoResult.c;
                                pluginUpdateInfoResult3.d = pluginUpdateInfoResult.d;
                                pluginUpdateInfoResult3.f = pluginUpdateInfoResult.f;
                                pluginUpdateInfoResult3.g = pluginUpdateInfoResult.g;
                                pluginUpdateInfoResult3.k = pluginUpdateInfoResult.k;
                                PluginSetting.a("FETCH OLD plugin:", pluginUpdateInfoResult3.f14696a, pluginUpdateInfoResult3.k, Long.valueOf(pluginUpdateInfoResult3.c), Long.valueOf(pluginUpdateInfoResult3.d));
                                arrayList.add(pluginUpdateInfoResult3);
                            }
                        }
                        if (!b2.isEmpty()) {
                            return arrayList;
                        }
                    }
                    JSONArray optJSONArray2 = jSONObject2.optJSONArray("backup_info");
                    if (optJSONArray2 != null) {
                        int length2 = optJSONArray2.length();
                        for (int i2 = 0; i2 < length2; i2++) {
                            PluginUpdateInfoResult pluginUpdateInfoResult4 = new PluginUpdateInfoResult();
                            JSONObject jSONObject4 = (JSONObject) optJSONArray2.get(i2);
                            pluginUpdateInfoResult4.f14696a = jSONObject4.optString("model");
                            if (((PluginUpdateInfoResult) hashMap.get(pluginUpdateInfoResult4.f14696a)) != null) {
                                System.out.println("INFO: OLD PLUGIN INFO OF " + pluginUpdateInfoResult4.f14696a);
                            } else {
                                pluginUpdateInfoResult4.h = jSONObject4.optString("download_url");
                                if (StringUtil.c(pluginUpdateInfoResult4.h)) {
                                    System.out.println("WARN: EMPTY PLUGIN INFO OF " + pluginUpdateInfoResult4.f14696a);
                                } else {
                                    pluginUpdateInfoResult4.b = jSONObject4.optInt("new_version");
                                    pluginUpdateInfoResult4.c = jSONObject4.optLong("plugin_id");
                                    pluginUpdateInfoResult4.d = jSONObject4.optLong(PushRouteUtil.k);
                                    pluginUpdateInfoResult4.e = jSONObject4.optInt("force");
                                    pluginUpdateInfoResult4.f = jSONObject4.optInt("version");
                                    pluginUpdateInfoResult4.g = jSONObject4.optString("change_log");
                                    pluginUpdateInfoResult4.i = jSONObject4.optString("safe_url");
                                    pluginUpdateInfoResult4.j = jSONObject4.optLong("length");
                                    pluginUpdateInfoResult4.k = jSONObject4.optString("type");
                                    arrayList.add(pluginUpdateInfoResult4);
                                }
                            }
                        }
                    }
                    return arrayList;
                }
            };
            return new CoreAsyncHandle(SmartHomeRc4Api.a().a(a22, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                /* renamed from: a */
                public void b(NetResult netResult) {
                }

                /* renamed from: b */
                public void a(NetResult netResult) {
                    CoreSmartHomeApiParser.a().a(netResult, r122, coreAsyncCallback);
                }

                public void a(NetError netError) {
                    if (jSONArray != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            if (i != 0) {
                                sb.append(i.b);
                            }
                            sb.append(jSONArray.optJSONObject(i).optString("model"));
                        }
                        STAT.i.c(2, sb.toString());
                    }
                    coreAsyncCallback.b(new CoreError(netError.a(), netError.b()));
                }
            }));
        } catch (JSONException unused) {
            if (coreAsyncCallback != null) {
                coreAsyncCallback.b(new CoreError(-1, ""));
            }
            return new CoreAsyncHandle(null);
        }
    }

    public CoreAsyncHandle b(Context context, final JSONArray jSONArray, int i, int i2, String str, final CoreAsyncCallback<List<PluginUpdateInfoResult>, CoreError> coreAsyncCallback) {
        b();
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("plugins", jSONArray);
            jSONObject.put("api_level", i);
            jSONObject.put("app_version", i2);
            jSONObject.put("app_platform", str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/plugin/update_plugin").b((List<KeyValuePair>) arrayList).a();
            final AnonymousClass7 r5 = new CoreJsonParser<List<PluginUpdateInfoResult>>() {
                /* renamed from: b */
                public List<PluginUpdateInfoResult> a(JSONObject jSONObject) throws JSONException {
                    ArrayList arrayList = new ArrayList();
                    JSONArray optJSONArray = jSONObject.optJSONArray("plugins");
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        PluginUpdateInfoResult pluginUpdateInfoResult = new PluginUpdateInfoResult();
                        JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                        pluginUpdateInfoResult.f14696a = jSONObject2.optString("model");
                        pluginUpdateInfoResult.b = jSONObject2.optInt("new_version");
                        pluginUpdateInfoResult.c = jSONObject2.optLong("plugin_id");
                        pluginUpdateInfoResult.d = jSONObject2.optLong(PushRouteUtil.k);
                        pluginUpdateInfoResult.e = jSONObject2.optInt("force");
                        pluginUpdateInfoResult.f = jSONObject2.optInt("version");
                        pluginUpdateInfoResult.g = jSONObject2.optString("change_log");
                        pluginUpdateInfoResult.h = jSONObject2.optString("download_url");
                        pluginUpdateInfoResult.i = jSONObject2.optString("safe_url");
                        pluginUpdateInfoResult.j = jSONObject2.optLong("length");
                        pluginUpdateInfoResult.k = jSONObject2.optString("type");
                        arrayList.add(pluginUpdateInfoResult);
                    }
                    return arrayList;
                }
            };
            return new CoreAsyncHandle(SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                /* renamed from: a */
                public void b(NetResult netResult) {
                }

                /* renamed from: b */
                public void a(NetResult netResult) {
                    CoreSmartHomeApiParser.a().a(netResult, r5, coreAsyncCallback);
                }

                public void a(NetError netError) {
                    if (jSONArray != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            if (i != 0) {
                                sb.append(i.b);
                            }
                            sb.append(jSONArray.optJSONObject(i).optString("model"));
                        }
                        STAT.i.c(2, sb.toString());
                    }
                    coreAsyncCallback.b(new CoreError(netError.a(), netError.b()));
                }
            }));
        } catch (JSONException unused) {
            if (coreAsyncCallback != null) {
                coreAsyncCallback.b(new CoreError(-1, ""));
            }
            return new CoreAsyncHandle(null);
        }
    }
}
