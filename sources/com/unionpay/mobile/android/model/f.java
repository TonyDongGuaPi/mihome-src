package com.unionpay.mobile.android.model;

import java.util.HashMap;
import org.json.JSONObject;

public final class f implements e {

    /* renamed from: a  reason: collision with root package name */
    private HashMap<String, Object> f9577a = new HashMap<>();

    public final JSONObject a(String str) {
        Object obj = this.f9577a.get(str);
        if (obj == null || !(obj instanceof JSONObject)) {
            return null;
        }
        return (JSONObject) obj;
    }

    public final void a(String str, Object obj) {
        this.f9577a.put(str, obj);
    }
}
