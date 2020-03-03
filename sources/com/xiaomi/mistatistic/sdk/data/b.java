package com.xiaomi.mistatistic.sdk.data;

import com.xiaomi.mistatistic.sdk.controller.h;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends AbstractEvent {
    protected String b;
    private String c;
    private String d;
    private String e;
    private Map<String, String> f;

    public b(String str, String str2, String str3, long j, Map<String, String> map) {
        this.c = str;
        this.d = str3;
        this.e = str2;
        this.b = String.valueOf(j);
        if (map == null) {
            this.f = null;
        } else {
            this.f = new HashMap(map);
        }
    }

    public b(String str, String str2, String str3, String str4, Map<String, String> map) {
        this.c = str;
        this.d = str3;
        this.e = str2;
        this.b = str4;
        if (map == null) {
            this.f = null;
        } else {
            this.f = new HashMap(map);
        }
    }

    public String a() {
        return this.c;
    }

    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("category", this.c);
        jSONObject.put("key", this.d);
        jSONObject.put("type", this.e);
        jSONObject.put("value", this.b);
        if (this.f != null) {
            jSONObject.put("params", new JSONObject(this.f));
        }
        return jSONObject;
    }

    public StatEventPojo c() {
        StatEventPojo statEventPojo = new StatEventPojo();
        statEventPojo.f12065a = this.c;
        statEventPojo.c = this.d;
        statEventPojo.b = this.f12063a;
        statEventPojo.d = this.e;
        statEventPojo.e = this.b;
        statEventPojo.f = a(this.f);
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
}
