package com.xiaomi.smarthome.shop.utils;

import android.text.TextUtils;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.shop.DeviceShopRefreshListener;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.HashMap;
import org.json.JSONObject;

@Deprecated
public class DeviceShopPushListener extends PushListener {
    private static final String b = "push";

    /* renamed from: a  reason: collision with root package name */
    private HashMap<String, DeviceShopRefreshListener> f22187a = new HashMap<>();
    private String c;

    public boolean a(String str, String str2) {
        this.c = str;
        a(str2);
        return true;
    }

    public boolean b(String str, String str2) {
        this.c = str;
        a(str2);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("action");
            Miio.h("push", "action: " + optString);
            Miio.h("push", "source: " + ("push?msgId=" + this.c));
            if (!TextUtils.isEmpty(optString)) {
                if (TextUtils.equals(optString, "1")) {
                    OpenApi.a();
                } else if (TextUtils.equals(optString, "2")) {
                    String optString2 = jSONObject.optString(ApiConst.j);
                    if (!TextUtils.isEmpty(optString2)) {
                        UrlDispatchManger.a().c(String.format("https://home.mi.com/shop/detail?gid=%s", new Object[]{optString2}));
                    }
                } else if (!TextUtils.equals(optString, "3")) {
                    if (TextUtils.equals(optString, "4")) {
                        UrlDispatchManger.a().c(jSONObject.optString("url"));
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public void a(String str, DeviceShopRefreshListener deviceShopRefreshListener) {
        if (deviceShopRefreshListener != null && !TextUtils.isEmpty(str)) {
            this.f22187a.put(str, deviceShopRefreshListener);
        }
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str) || !this.f22187a.containsKey(str)) {
            return false;
        }
        this.f22187a.remove(str);
        return true;
    }

    public boolean c(String str) {
        return this.f22187a.containsKey(str);
    }
}
