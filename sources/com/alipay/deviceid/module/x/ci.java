package com.alipay.deviceid.module.x;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class ci extends ch {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f907a = {"apdid", "h", "imei", "imsi", "mac", "px", "sensor", "umid", "utdid", "apdidToken", "w", "tid", "idfa", "gss", "gss2", "usb", "wi"};
    public ch b;
    public Map<String, Object> c;

    public ci(Context context, String str, String str2, String str3, String str4, String str5) {
        this();
        k.a();
        l.a();
        this.c.put("apdid", str);
        this.c.put("umid", str2);
        this.c.put("utdid", str3);
        this.c.put("tid", str4);
        this.c.put("apdidToken", str5);
        this.c.put("imei", k.a(context));
        this.c.put("imsi", k.b(context));
        this.c.put("mac", k.l(context));
        this.c.put("px", k.i(context));
        this.c.put("w", k.j(context));
        this.c.put("h", k.k(context));
        this.c.put("idfa", "");
        this.c.put("gss", l.a("gsm.sim.state", ""));
        this.c.put("gss2", l.a("gsm.sim.state.2", ""));
        this.c.put("usb", l.a("sys.usb.state", ""));
        this.c.put("wi", cd.a(l.a("wifi.interface", "")) ? "" : l.a("wifi.interface", ""));
    }

    private ci() {
        this.c = new HashMap();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        for (String str : f907a) {
            Object obj = this.c.get(str);
            if (obj != null && (obj instanceof String)) {
                try {
                    jSONObject.put(str, obj);
                } catch (JSONException unused) {
                }
            } else if (obj != null && (obj instanceof ch)) {
                jSONObject.put(str, ((ch) obj).a());
            }
        }
        return jSONObject;
    }
}
