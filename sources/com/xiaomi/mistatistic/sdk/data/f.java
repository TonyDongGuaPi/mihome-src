package com.xiaomi.mistatistic.sdk.data;

import android.text.TextUtils;
import com.xiaomi.mistatistic.sdk.controller.h;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class f extends AbstractEvent {
    protected long b;
    private String c;
    private String d;
    private Map<String, String> e;

    public abstract String d();

    public f(String str, String str2, long j) {
        this(str, str2, j, (Map<String, String>) null);
    }

    public f(String str, String str2, long j, Map<String, String> map) {
        this.c = str;
        this.d = str2;
        this.b = j;
        if (map == null) {
            this.e = null;
        } else {
            this.e = new HashMap(map);
        }
    }

    public String a() {
        return this.c;
    }

    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("category", this.c);
        jSONObject.put("key", this.d);
        jSONObject.put("type", d());
        jSONObject.put("value", this.b);
        if (this.e != null) {
            jSONObject.put("params", new JSONObject(this.e));
        }
        return jSONObject;
    }

    public StatEventPojo c() {
        StatEventPojo statEventPojo = new StatEventPojo();
        statEventPojo.f12065a = this.c;
        statEventPojo.c = this.d;
        statEventPojo.b = this.f12063a;
        statEventPojo.d = d();
        statEventPojo.e = String.valueOf(this.b);
        statEventPojo.f = a(this.e);
        return statEventPojo;
    }

    private String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            if (map.isEmpty()) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            for (String next : map.keySet()) {
                jSONObject.put(next, map.get(next));
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            h.a("json error", (Throwable) e2);
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (!TextUtils.equals(this.c, fVar.c) || !TextUtils.equals(this.d, fVar.d) || !TextUtils.equals(d(), fVar.d()) || this.b != fVar.b || this.e == null) {
            return true;
        }
        return this.e.equals(fVar.e);
    }
}
