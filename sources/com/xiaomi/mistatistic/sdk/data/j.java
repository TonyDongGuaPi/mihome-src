package com.xiaomi.mistatistic.sdk.data;

import org.json.JSONException;
import org.json.JSONObject;

public class j extends AbstractEvent {
    private String b;
    private String c;

    public String a() {
        return "mistat_pv";
    }

    public j(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("path", this.b);
        jSONObject.put("source", this.c);
        return jSONObject;
    }

    public StatEventPojo c() {
        StatEventPojo statEventPojo = new StatEventPojo();
        statEventPojo.f12065a = a();
        statEventPojo.b = this.f12063a;
        statEventPojo.e = this.b;
        statEventPojo.f = this.c;
        return statEventPojo;
    }
}
