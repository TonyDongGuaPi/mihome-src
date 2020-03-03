package com.alipay.deviceid.module.x;

import com.facebook.internal.AnalyticsEvents;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class cg extends ch {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f906a = {AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "sdk"};
    private Map<String, Object> b;

    public cg() {
        this((byte) 0);
    }

    private cg(byte b2) {
        this.b = new HashMap();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        for (String str : f906a) {
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
