package com.youpin.weex.app.adapter;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.util.i;
import com.taobao.weex.bridge.JSCallback;
import com.xiaomi.youpin.cookie.YouPinCookieManager;
import com.youpin.weex.app.common.ModuleUtils;
import com.youpin.weex.app.module.cookie.ICookieAdapter;
import java.io.IOException;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YPCookieAdapter implements ICookieAdapter {
    private String a(String str) {
        return str;
    }

    public void set(Map<String, Object> map, JSCallback jSCallback) throws Exception {
        if (map == null || map.size() <= 0) {
            ModuleUtils.b("cookie size is 0!", jSCallback);
            return;
        }
        String str = (String) map.get("name");
        String str2 = (String) map.get("value");
        String str3 = (String) map.get("domain");
        String str4 = (String) map.get("origin");
        String str5 = (String) map.get("path");
        String str6 = (String) map.get("version");
        String str7 = (String) map.get("expires");
        if (str == null || str2 == null || str3 == null) {
            ModuleUtils.b("name value domain has null", jSCallback);
            return;
        }
        String a2 = a(str3);
        StringBuilder sb = new StringBuilder(str + "=" + str2 + "; domain=" + a2);
        if (!TextUtils.isEmpty(str5)) {
            sb.append("; path=");
            sb.append(str5);
        }
        if (!TextUtils.isEmpty(str7)) {
            sb.append("; expires=");
            sb.append(str7);
        }
        if (!TextUtils.isEmpty(str6)) {
            sb.append("; version=");
            sb.append(str6);
        }
        YouPinCookieManager.a().a(a2, sb.toString());
        ModuleUtils.a("success", jSCallback);
    }

    public void setFromResponse(String str, Map<String, Object> map, JSCallback jSCallback) throws Exception {
        if (str == null || map == null || map.size() == 0) {
            ModuleUtils.b("missing parameters", jSCallback);
            return;
        }
        String a2 = a(str);
        String str2 = (String) map.get("Set-Cookie");
        if (TextUtils.isEmpty(str2)) {
            ModuleUtils.b("cookie is null", jSCallback);
        }
        YouPinCookieManager.a().a(a2, str2);
        ModuleUtils.a("success", jSCallback);
    }

    public void getFromResponse(String str, JSCallback jSCallback) {
        if (TextUtils.isEmpty(str)) {
            ModuleUtils.b("missing parameters", jSCallback);
        }
        String a2 = a(str);
        try {
            Response execute = new OkHttpClient().newCall(new Request.Builder().url(a2).build()).execute();
            if (!execute.isSuccessful()) {
                ModuleUtils.b("request fail", jSCallback);
                return;
            }
            String str2 = execute.headers().get("Set-Cookie");
            YouPinCookieManager.a().a(a2, str2);
            ModuleUtils.a(b(str2), jSCallback);
        } catch (IOException e) {
            e.printStackTrace();
            ModuleUtils.b(e.toString(), jSCallback);
        }
    }

    @Deprecated
    public void getAll(JSCallback jSCallback) throws Exception {
        ModuleUtils.b("not support", jSCallback);
    }

    public void get(String str, JSCallback jSCallback) throws Exception {
        if (TextUtils.isEmpty(str)) {
            ModuleUtils.b("missing parameters", jSCallback);
            return;
        }
        ModuleUtils.a(b(YouPinCookieManager.a().b(a(str))), jSCallback);
    }

    public void clearByName(String str, String str2, JSCallback jSCallback) throws Exception {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            ModuleUtils.b("missing parameters", jSCallback);
            return;
        }
        YouPinCookieManager.a().b(str, a(str2));
        ModuleUtils.a("success", jSCallback);
    }

    public void clearAll(JSCallback jSCallback) throws Exception {
        YouPinCookieManager.a().b();
        ModuleUtils.a("success", jSCallback);
    }

    private JSONObject b(String str) {
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        for (String trim : str.split(i.b)) {
            String trim2 = trim.trim();
            int indexOf = trim2.indexOf("=");
            jSONObject.put(trim2.substring(0, indexOf), (Object) trim2.substring(indexOf + 1));
        }
        return jSONObject;
    }
}
