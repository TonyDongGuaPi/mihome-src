package com.xiaomi.mistatistic.sdk.data;

import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import com.xiaomi.verificationsdk.internal.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class k extends AbstractEvent {
    private long b;
    private long c;
    private String d;

    public String a() {
        return RemoteDataUploadManager.f;
    }

    public k(long j, long j2, String str) {
        this.b = j;
        this.c = j2;
        this.d = str;
    }

    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("start", this.b);
        jSONObject.put("end", this.c);
        jSONObject.put(Constants.d, this.d);
        return jSONObject;
    }

    public StatEventPojo c() {
        StatEventPojo statEventPojo = new StatEventPojo();
        statEventPojo.f12065a = a();
        statEventPojo.b = this.f12063a;
        statEventPojo.e = this.b + "," + this.c;
        statEventPojo.f = this.d;
        return statEventPojo;
    }
}
