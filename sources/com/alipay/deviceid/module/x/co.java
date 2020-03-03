package com.alipay.deviceid.module.x;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class co extends ch {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f916a = {"appKey", "t", "wua"};
    private Map<String, Object> b;

    public co() {
        this((byte) 0);
    }

    private co(byte b2) {
        this.b = new HashMap();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        for (String str : f916a) {
            Object obj = this.b.get(str);
            if (obj != null && (obj instanceof String)) {
                try {
                    jSONObject.put(str, obj);
                } catch (JSONException unused) {
                }
            }
        }
        return jSONObject;
    }
}
