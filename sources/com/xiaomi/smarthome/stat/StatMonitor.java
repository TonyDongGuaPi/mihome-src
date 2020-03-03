package com.xiaomi.smarthome.stat;

import com.xiaomi.smarthome.stat.report.StatReporter;
import org.json.JSONObject;

public abstract class StatMonitor {

    /* renamed from: a  reason: collision with root package name */
    private StatReporter f22753a = new StatReporter("monitor");

    public void a(JSONObject jSONObject) {
        this.f22753a.a("lifecycle_time", (String) null, jSONObject, "");
    }

    public void b(JSONObject jSONObject) {
        this.f22753a.a("appstart_time", (String) null, jSONObject, "");
    }

    public void c(JSONObject jSONObject) {
        this.f22753a.a("http_time", (String) null, jSONObject, "");
    }

    public void d(JSONObject jSONObject) {
        this.f22753a.a("rpc_time", (String) null, jSONObject, "");
    }

    public void e(JSONObject jSONObject) {
        this.f22753a.a("ram_statistics", (String) null, jSONObject, "");
    }

    public void f(JSONObject jSONObject) {
        this.f22753a.a("fps", (String) null, jSONObject, "");
    }
}
