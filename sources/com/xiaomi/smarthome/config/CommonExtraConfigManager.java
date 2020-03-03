package com.xiaomi.smarthome.config;

import android.support.annotation.NonNull;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONObject;

public class CommonExtraConfigManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13931a = "CommonExtraConfigManager";
    private static volatile CommonExtraConfigManager b;
    /* access modifiers changed from: private */
    public Map<String, List<String>> c = new HashMap();

    private CommonExtraConfigManager() {
    }

    public static CommonExtraConfigManager a() {
        if (b == null) {
            synchronized (CommonExtraConfigManager.class) {
                if (b == null) {
                    b = new CommonExtraConfigManager();
                }
            }
        }
        return b;
    }

    public Map<String, List<String>> b() {
        return this.c;
    }

    public void c() {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, "zh_CN");
            StringBuilder sb = new StringBuilder();
            sb.append("common_extra_config");
            sb.append(GlobalSetting.E ? "_preview" : "");
            jSONObject.put("name", sb.toString());
            jSONObject.put("version", "1");
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        Request request = null;
        try {
            request = new Request.Builder().a("GET").b(a(jSONObject)).a();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (request != null) {
            HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                public void onFailure(Error error, Exception exc, Response response) {
                }

                public void onSuccess(Object obj, Response response) {
                }

                public void processFailure(Call call, IOException iOException) {
                }

                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: java.lang.Object} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: org.json.JSONObject} */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void processResponse(okhttp3.Response r9) {
                    /*
                        r8 = this;
                        okhttp3.ResponseBody r9 = r9.body()     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.lang.String r9 = r9.string()     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        r0.<init>(r9)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.lang.String r9 = "result"
                        boolean r9 = r0.isNull(r9)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        if (r9 == 0) goto L_0x0016
                        return
                    L_0x0016:
                        java.lang.String r9 = "result"
                        org.json.JSONObject r9 = r0.optJSONObject(r9)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.lang.String r0 = "CommonExtraConfigManager"
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        r1.<init>()     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.lang.String r2 = "getRemoteConfig  result"
                        r1.append(r2)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.lang.String r2 = r9.toString()     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        r1.append(r2)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        com.xiaomi.smarthome.miio.Miio.b(r0, r1)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.lang.String r0 = "content"
                        boolean r0 = r9.isNull(r0)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        if (r0 == 0) goto L_0x003f
                        return
                    L_0x003f:
                        r0 = 0
                        java.lang.String r1 = "content"
                        java.lang.Object r9 = r9.get(r1)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        boolean r1 = r9 instanceof org.json.JSONObject     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        if (r1 == 0) goto L_0x004e
                        r0 = r9
                        org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        goto L_0x0059
                    L_0x004e:
                        boolean r1 = r9 instanceof java.lang.String     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        if (r1 == 0) goto L_0x0059
                        org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.lang.String r9 = (java.lang.String) r9     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        r0.<init>(r9)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                    L_0x0059:
                        if (r0 != 0) goto L_0x005c
                        return
                    L_0x005c:
                        java.lang.String r9 = "filter_model_config"
                        boolean r9 = r0.isNull(r9)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        if (r9 != 0) goto L_0x00bd
                        java.util.HashMap r9 = new java.util.HashMap     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        r9.<init>()     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.lang.String r1 = "filter_model_config"
                        org.json.JSONArray r0 = r0.optJSONArray(r1)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        if (r0 == 0) goto L_0x00b7
                        r1 = 0
                        r2 = 0
                    L_0x0073:
                        int r3 = r0.length()     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        if (r2 >= r3) goto L_0x00b7
                        org.json.JSONObject r3 = r0.getJSONObject(r2)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.lang.String r4 = "request_model"
                        java.lang.String r3 = r3.optString(r4)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        org.json.JSONObject r4 = r0.getJSONObject(r2)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.lang.String r5 = "filter_model"
                        org.json.JSONArray r4 = r4.optJSONArray(r5)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        if (r5 != 0) goto L_0x00b4
                        if (r4 == 0) goto L_0x00b4
                        int r5 = r4.length()     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        if (r5 <= 0) goto L_0x00b4
                        java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        r5.<init>()     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        r6 = 0
                    L_0x00a1:
                        int r7 = r4.length()     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        if (r6 >= r7) goto L_0x00b1
                        java.lang.String r7 = r4.getString(r6)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        r5.add(r7)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        int r6 = r6 + 1
                        goto L_0x00a1
                    L_0x00b1:
                        r9.put(r3, r5)     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                    L_0x00b4:
                        int r2 = r2 + 1
                        goto L_0x0073
                    L_0x00b7:
                        com.xiaomi.smarthome.config.CommonExtraConfigManager r0 = com.xiaomi.smarthome.config.CommonExtraConfigManager.this     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.util.Map unused = r0.c = r9     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        goto L_0x00d6
                    L_0x00bd:
                        com.xiaomi.smarthome.config.CommonExtraConfigManager r9 = com.xiaomi.smarthome.config.CommonExtraConfigManager.this     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.util.HashMap r0 = new java.util.HashMap     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        r0.<init>()     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        java.util.Map unused = r9.c = r0     // Catch:{ IOException -> 0x00d2, JSONException -> 0x00cd, Exception -> 0x00c8 }
                        goto L_0x00d6
                    L_0x00c8:
                        r9 = move-exception
                        r9.printStackTrace()
                        goto L_0x00d6
                    L_0x00cd:
                        r9 = move-exception
                        r9.printStackTrace()
                        goto L_0x00d6
                    L_0x00d2:
                        r9 = move-exception
                        r9.printStackTrace()
                    L_0x00d6:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.config.CommonExtraConfigManager.AnonymousClass1.processResponse(okhttp3.Response):void");
                }
            });
        }
    }

    @NonNull
    private String a(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }
}
