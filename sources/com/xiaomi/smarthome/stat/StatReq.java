package com.xiaomi.smarthome.stat;

import com.xiaomi.smarthome.stat.report.StatReporter;

public abstract class StatReq {

    /* renamed from: a  reason: collision with root package name */
    private StatReporter f22757a = new StatReporter("req");

    public void a() {
        this.f22757a.a("startup_ad_request", new Object[0]);
    }

    public void b() {
        this.f22757a.a("startup_ad_request_success", new Object[0]);
    }

    public void c() {
        this.f22757a.a("startup_ad_request_fail", new Object[0]);
    }
}
