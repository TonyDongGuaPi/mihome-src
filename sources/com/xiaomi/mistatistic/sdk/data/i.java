package com.xiaomi.mistatistic.sdk.data;

import org.json.JSONException;
import org.json.JSONObject;

public class i extends AbstractEvent {
    private String b;
    private Long c;
    private long d;

    public String a() {
        return "mistat_pt";
    }

    public i(String str, Long l) {
        this.b = str;
        this.d = l.longValue();
    }

    public void a(Long l) {
        this.c = l;
    }

    public long d() {
        return this.d;
    }

    public String e() {
        return this.b;
    }

    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", this.b);
        jSONObject.put("value", this.c);
        return jSONObject;
    }

    public StatEventPojo c() {
        StatEventPojo statEventPojo = new StatEventPojo();
        statEventPojo.f12065a = a();
        statEventPojo.b = this.f12063a;
        statEventPojo.c = this.b;
        statEventPojo.e = Long.toString(this.c.longValue());
        return statEventPojo;
    }
}
