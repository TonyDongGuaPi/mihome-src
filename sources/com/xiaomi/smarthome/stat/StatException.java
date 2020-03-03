package com.xiaomi.smarthome.stat;

import com.alipay.mobile.common.logging.api.LogCategory;
import com.xiaomi.smarthome.stat.report.StatReporter;
import org.json.JSONObject;

public abstract class StatException {

    /* renamed from: a  reason: collision with root package name */
    private StatReporter f22752a = new StatReporter(LogCategory.CATEGORY_EXCEPTION);

    public void a(JSONObject jSONObject) {
        this.f22752a.a("login_fail", "data", jSONObject);
    }
}
