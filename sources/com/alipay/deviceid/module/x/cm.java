package com.alipay.deviceid.module.x;

import com.xiaomi.verificationsdk.internal.Constants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class cm extends ch {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f911a = {"dev", "loc", Constants.d, "usr"};
    public Map<String, Object> b;
    public ch c;
    public ch d;
    public ch e;
    public ch f;

    public cm() {
        this((byte) 0);
    }

    private cm(byte b2) {
        this.b = new HashMap();
    }

    /* renamed from: b */
    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        for (String str : f911a) {
            Object obj = this.b.get(str);
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
