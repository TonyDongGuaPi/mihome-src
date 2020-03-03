package com.xiaomi.mistatistic.sdk.data;

import android.text.TextUtils;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.xiaomi.mistatistic.sdk.controller.h;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractEvent {

    /* renamed from: a  reason: collision with root package name */
    protected long f12063a = System.currentTimeMillis();

    public abstract String a();

    public abstract JSONObject b() throws JSONException;

    public abstract StatEventPojo c();

    public void a(long j) {
        this.f12063a = j;
    }

    private static Map<String, String> a(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.optString(next));
        }
        return hashMap;
    }

    public static AbstractEvent a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("category");
            String optString2 = jSONObject.optString("key");
            String optString3 = jSONObject.optString("type");
            String optString4 = jSONObject.optString("value");
            JSONObject optJSONObject = jSONObject.optJSONObject("params");
            Map<String, String> a2 = optJSONObject != null ? a(optJSONObject) : null;
            if (TextUtils.equals(optString3, "count")) {
                return new c(optString, optString2, (long) Integer.parseInt(optString4), a2);
            }
            if (TextUtils.equals(optString3, "event")) {
                return new e(optString, optString2, a2);
            }
            if (TextUtils.equals(optString3, "numeric")) {
                return new g(optString, optString2, (long) Integer.parseInt(optString4));
            }
            if (TextUtils.equals(optString3, BindingXConstants.j)) {
                return new h(optString, optString2, optString4);
            }
            return null;
        } catch (JSONException e) {
            h.a("", (Throwable) e);
            return null;
        }
    }
}
