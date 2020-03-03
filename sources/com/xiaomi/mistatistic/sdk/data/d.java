package com.xiaomi.mistatistic.sdk.data;

import org.json.JSONException;
import org.json.JSONObject;

public class d extends AbstractEvent {
    private String b = null;

    public String a() {
        return "mistat_extra";
    }

    public JSONObject b() throws JSONException {
        return null;
    }

    public d(String str) {
        this.b = str;
    }

    public StatEventPojo c() {
        StatEventPojo statEventPojo = new StatEventPojo();
        statEventPojo.f12065a = a();
        statEventPojo.b = this.f12063a;
        statEventPojo.e = this.b;
        return statEventPojo;
    }
}
