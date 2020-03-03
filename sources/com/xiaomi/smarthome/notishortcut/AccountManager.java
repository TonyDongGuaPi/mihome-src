package com.xiaomi.smarthome.notishortcut;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.library.common.util.CommonUtil;
import com.xiaomi.smarthome.library.common.util.XMEncryptUtils;
import com.xiaomi.smarthome.notishortcut.inward.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1557a = "AccountManager";
    private static final String b = "account_setting";
    private static final String c = "1.0";

    public static synchronized void a(Context context, String str) {
        synchronized (AccountManager.class) {
            LogUtil.b(f1557a, "setUserId start");
            if (!TextUtils.isEmpty(str)) {
                try {
                    SPHelper.a(context, b, "userId", c(context, str));
                    SPHelper.a(context, b, "sp_version", "1.0");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            LogUtil.b(f1557a, "setUserId end:  " + SPHelper.b(context, b, "userId", ""));
        }
    }

    public static synchronized String a(Context context) {
        synchronized (AccountManager.class) {
            if (TextUtils.equals("1.0", SPHelper.b(context, b, "sp_version", ""))) {
                String d = d(context, SPHelper.b(context, b, "userId", ""));
                return d;
            }
            String b2 = SPHelper.b(context, b, "userId", "");
            a(context, b2);
            return b2;
        }
    }

    public static synchronized void a(Context context, String str, long j, String str2, String str3) {
        synchronized (AccountManager.class) {
            LogUtil.b(f1557a, "setServiceTokenInfo start");
            ServiceTokenInfo serviceTokenInfo = new ServiceTokenInfo();
            serviceTokenInfo.f1558a = str;
            serviceTokenInfo.c = j;
            serviceTokenInfo.b = str2;
            serviceTokenInfo.d = str3;
            try {
                SPHelper.a(context, b, "service_token_info", c(context, ServiceTokenInfo.a(serviceTokenInfo).toString()));
                SPHelper.a(context, b, "sp_version", "1.0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return;
    }

    public static synchronized ServiceTokenInfo b(Context context) {
        synchronized (AccountManager.class) {
            if (TextUtils.equals("1.0", SPHelper.b(context, b, "sp_version", ""))) {
                ServiceTokenInfo a2 = ServiceTokenInfo.a(d(context, SPHelper.b(context, b, "service_token_info", "")));
                return a2;
            }
            ServiceTokenInfo serviceTokenInfo = new ServiceTokenInfo();
            serviceTokenInfo.f1558a = SPHelper.b(context, b, "serviceToken", "");
            serviceTokenInfo.c = Long.parseLong(SPHelper.b(context, b, "timeDiff", "0"));
            serviceTokenInfo.b = SPHelper.b(context, b, "ssecurity", "");
            serviceTokenInfo.d = SPHelper.b(context, b, "domain", "");
            SPHelper.a(context, b, "serviceToken");
            SPHelper.a(context, b, "timeDiff");
            SPHelper.a(context, b, "ssecurity");
            SPHelper.a(context, b, "domain");
            a(context, serviceTokenInfo.f1558a, serviceTokenInfo.c, serviceTokenInfo.b, serviceTokenInfo.d);
            return serviceTokenInfo;
        }
    }

    private static String c(Context context, String str) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return XMEncryptUtils.b(str, CommonUtil.a(context));
    }

    private static String d(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return XMEncryptUtils.a(str, CommonUtil.a(context));
    }

    public static void c(Context context) {
        SPHelper.a(context, b);
    }

    static class ServiceTokenInfo {

        /* renamed from: a  reason: collision with root package name */
        String f1558a;
        String b;
        long c;
        String d;

        ServiceTokenInfo() {
        }

        static ServiceTokenInfo a(String str) {
            ServiceTokenInfo serviceTokenInfo = new ServiceTokenInfo();
            if (TextUtils.isEmpty(str)) {
                return serviceTokenInfo;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("serviceToken")) {
                    serviceTokenInfo.f1558a = jSONObject.getString("serviceToken");
                }
                if (jSONObject.has("ssecurity")) {
                    serviceTokenInfo.b = jSONObject.getString("ssecurity");
                }
                if (jSONObject.has("domain")) {
                    serviceTokenInfo.d = jSONObject.getString("domain");
                }
                if (jSONObject.has("timeDiff")) {
                    serviceTokenInfo.c = jSONObject.getLong("timeDiff");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return serviceTokenInfo;
        }

        static JSONObject a(ServiceTokenInfo serviceTokenInfo) {
            JSONObject jSONObject = new JSONObject();
            if (serviceTokenInfo == null) {
                return jSONObject;
            }
            try {
                if (!TextUtils.isEmpty(serviceTokenInfo.f1558a)) {
                    jSONObject.put("serviceToken", serviceTokenInfo.f1558a);
                }
                if (!TextUtils.isEmpty(serviceTokenInfo.b)) {
                    jSONObject.put("ssecurity", serviceTokenInfo.b);
                }
                jSONObject.put("timeDiff", serviceTokenInfo.c);
                if (!TextUtils.isEmpty(serviceTokenInfo.d)) {
                    jSONObject.put("domain", serviceTokenInfo.d);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }

    public static void b(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                a(context, jSONObject.optString("userId"));
                JSONObject optJSONObject = jSONObject.optJSONObject("serviceTokens");
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString("xiaomiio");
                    JSONObject jSONObject2 = new JSONObject(optString);
                    LogUtil.a(f1557a, optString);
                    Context context2 = context;
                    a(context2, jSONObject2.optString("serviceToken"), jSONObject2.optLong("timeDiff"), jSONObject2.optString("ssecurity"), jSONObject2.optString("domain"));
                }
            } catch (JSONException unused) {
            }
        }
    }
}
