package com.alipay.deviceid.module.x;

import android.content.Context;
import com.facebook.react.modules.appstate.AppStateModule;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ck extends ch {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f909a = {AppStateModule.APP_STATE_ACTIVE, "bssid", "cid", "omac", "la", "lac", "lo", "mcc", "mnc", "acc", DeviceTagInterface.e, "strength", "carrier", "nettype", "t"};
    private Map<String, Object> b;

    public ck(Context context) {
        this();
        k.a();
        l.a();
        m a2 = m.a(context);
        this.b.put(AppStateModule.APP_STATE_ACTIVE, e.a(a2.e) ? "false" : "true");
        String str = a2.c;
        String str2 = a2.i;
        String str3 = a2.b;
        String str4 = a2.j;
        String str5 = a2.f932a;
        String str6 = a2.g;
        String str7 = a2.h;
        String str8 = a2.d;
        String str9 = a2.f;
        String e = k.e(context);
        String b2 = l.b(context);
        this.b.put("bssid", e.a(str) ? "" : str);
        this.b.put("cid", e.a(str2) ? "" : str2);
        this.b.put("omac", new JSONArray());
        this.b.put("la", e.a(str3) ? "" : str3);
        this.b.put("lac", e.a(str4) ? "" : str4);
        this.b.put("lo", e.a(str5) ? "" : str5);
        this.b.put("mcc", e.a(str6) ? "" : str6);
        this.b.put("mnc", e.a(str7) ? "" : str7);
        this.b.put("acc", "");
        this.b.put(DeviceTagInterface.e, e.a(str8) ? "" : str8);
        this.b.put("strength", e.a(str9) ? "" : str9);
        this.b.put("carrier", e.a(e) ? "" : e);
        this.b.put("nettype", e.a(b2) ? "" : b2);
        this.b.put("t", Long.valueOf(System.currentTimeMillis()));
    }

    private ck() {
        this.b = new HashMap();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        for (String str : f909a) {
            Object obj = this.b.get(str);
            if (obj != null && ((obj instanceof String) || (obj instanceof JSONArray))) {
                try {
                    jSONObject.put(str, obj);
                } catch (JSONException unused) {
                }
            }
        }
        return jSONObject;
    }
}
