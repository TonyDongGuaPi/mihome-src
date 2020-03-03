package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.StatAppMonitor;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.a;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.q;
import org.json.JSONObject;

public class h extends e {
    private static String m;
    private static String n;

    /* renamed from: a  reason: collision with root package name */
    private StatAppMonitor f9278a = null;

    public h(Context context, int i, StatAppMonitor statAppMonitor, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f9278a = statAppMonitor.clone();
    }

    public f a() {
        return f.MONITOR_STAT;
    }

    public boolean a(JSONObject jSONObject) {
        if (this.f9278a == null) {
            return false;
        }
        jSONObject.put("na", this.f9278a.a());
        jSONObject.put("rq", this.f9278a.b());
        jSONObject.put("rp", this.f9278a.c());
        jSONObject.put("rt", this.f9278a.d());
        jSONObject.put("tm", this.f9278a.e());
        jSONObject.put("rc", this.f9278a.f());
        jSONObject.put("sp", this.f9278a.g());
        if (n == null) {
            n = k.n(this.l);
        }
        q.a(jSONObject, "av", n);
        if (m == null) {
            m = k.i(this.l);
        }
        q.a(jSONObject, "op", m);
        jSONObject.put("cn", a.a(this.l).b());
        return true;
    }
}
