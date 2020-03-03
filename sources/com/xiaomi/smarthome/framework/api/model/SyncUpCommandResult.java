package com.xiaomi.smarthome.framework.api.model;

import java.util.HashMap;
import org.json.JSONObject;

public class SyncUpCommandResult {

    /* renamed from: a  reason: collision with root package name */
    public int f16459a;
    public int b;
    public long c;
    public HashMap<String, Object> d;

    public static SyncUpCommandResult a(JSONObject jSONObject) {
        SyncUpCommandResult syncUpCommandResult = new SyncUpCommandResult();
        syncUpCommandResult.f16459a = jSONObject.optInt("id");
        syncUpCommandResult.b = jSONObject.optInt("ret");
        return syncUpCommandResult;
    }

    public boolean a() {
        return this.b == 1;
    }
}
