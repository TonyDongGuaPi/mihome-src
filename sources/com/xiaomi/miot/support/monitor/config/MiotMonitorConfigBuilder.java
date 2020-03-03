package com.xiaomi.miot.support.monitor.config;

import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.report.IReport;
import org.json.JSONException;
import org.json.JSONObject;

public class MiotMonitorConfigBuilder {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11453a = "MiotMonitorConfigBuilder";
    private MiotMonitorConfig b;

    private MiotMonitorConfigBuilder() {
        this.b = new MiotMonitorConfig();
    }

    public static MiotMonitorConfigBuilder a() {
        return Holder.f11454a;
    }

    static final class Holder {

        /* renamed from: a  reason: collision with root package name */
        static MiotMonitorConfigBuilder f11454a = new MiotMonitorConfigBuilder();

        Holder() {
        }
    }

    public MiotMonitorConfigBuilder a(IReport iReport) {
        this.b.f1475a = iReport;
        return this;
    }

    public MiotMonitorConfigBuilder a(boolean z) {
        this.b.i = z;
        return this;
    }

    public MiotMonitorConfigBuilder a(String str) {
        c();
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("memory_leak_monitor_config");
            if (optJSONObject != null) {
                this.b.b.switchFlag = TextUtils.equals(optJSONObject.optString("switch"), "1");
                this.b.b.report_type = optJSONObject.optString("report_type");
                this.b.b.min_check_interval_sec = optJSONObject.optLong("min_check_interval_sec");
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("ui_block_monitor_config");
            if (optJSONObject2 != null) {
                this.b.c.switchFlag = TextUtils.equals(optJSONObject2.optString("switch"), "1");
                this.b.c.report_type = optJSONObject2.optString("report_type");
                this.b.c.threshold_ms = optJSONObject2.optLong("threshold_ms");
            }
            JSONObject optJSONObject3 = jSONObject.optJSONObject("activity_monitor_config");
            if (optJSONObject3 != null) {
                this.b.d.switchFlag = TextUtils.equals(optJSONObject3.optString("switch"), "1");
                this.b.d.report_type = optJSONObject3.optString("report_type");
            }
            JSONObject optJSONObject4 = jSONObject.optJSONObject("fps_monitor_config");
            if (optJSONObject4 != null) {
                this.b.e.switchFlag = TextUtils.equals(optJSONObject4.optString("switch"), "1");
                this.b.e.report_type = optJSONObject4.optString("report_type");
            }
            JSONObject optJSONObject5 = jSONObject.optJSONObject("appstart_monitor_config");
            if (optJSONObject5 != null) {
                this.b.f.switchFlag = TextUtils.equals(optJSONObject5.optString("switch"), "1");
                this.b.f.report_type = optJSONObject5.optString("report_type");
            }
            JSONObject optJSONObject6 = jSONObject.optJSONObject("memory_monitor_config");
            if (optJSONObject6 != null) {
                this.b.g.switchFlag = TextUtils.equals(optJSONObject6.optString("switch"), "1");
                this.b.g.report_type = optJSONObject6.optString("report_type");
                this.b.g.min_check_interval_sec = optJSONObject6.optLong("min_check_interval_sec");
            }
            JSONObject optJSONObject7 = jSONObject.optJSONObject("net_monitor_config");
            if (optJSONObject7 != null) {
                this.b.h.switchFlag = TextUtils.equals(optJSONObject7.optString("switch"), "1");
                this.b.h.report_type = optJSONObject7.optString("report_type");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    private void c() {
        this.b.b.switchFlag = false;
        this.b.c.switchFlag = false;
        this.b.d.switchFlag = false;
        this.b.e.switchFlag = false;
        this.b.f.switchFlag = false;
        this.b.g.switchFlag = false;
        this.b.h.switchFlag = false;
    }

    public MiotMonitorConfig b() {
        return this.b;
    }
}
