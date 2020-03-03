package com.xiaomi.clientreport.data;

import com.xiaomi.channel.commonutils.logger.b;
import org.json.JSONException;
import org.json.JSONObject;

public class PerfClientReport extends a {
    private static final long d = -1;

    /* renamed from: a  reason: collision with root package name */
    public int f10078a;
    public long b = -1;
    public long c = -1;

    public static PerfClientReport a() {
        return new PerfClientReport();
    }

    public JSONObject b() {
        try {
            JSONObject b2 = super.b();
            if (b2 == null) {
                return null;
            }
            b2.put("code", this.f10078a);
            b2.put("perfCounts", this.b);
            b2.put("perfLatencies", this.c);
            return b2;
        } catch (JSONException e) {
            b.a((Throwable) e);
            return null;
        }
    }

    public String c() {
        return super.c();
    }
}
