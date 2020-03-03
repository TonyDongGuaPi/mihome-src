package com.xiaomi.smarthome.stat;

import com.xiaomi.smarthome.stat.report.StatReporter;
import org.json.JSONObject;

public class PluginCustomReporter {

    /* renamed from: a  reason: collision with root package name */
    private String f22746a;
    private StatReporter b;

    public PluginCustomReporter(String str, StatReporter statReporter) {
        this.f22746a = str;
        this.b = statReporter;
    }

    public void a(String str, String str2) {
        this.b.a(str, this.f22746a, new JSONObject(), str2);
    }
}
