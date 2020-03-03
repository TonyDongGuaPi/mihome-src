package com.xiaomi.clientreport.data;

import com.xiaomi.channel.commonutils.logger.b;
import org.json.JSONException;
import org.json.JSONObject;

public class EventClientReport extends a {

    /* renamed from: a  reason: collision with root package name */
    public String f10077a;
    public int b;
    public long c;
    public String d;

    public static EventClientReport a() {
        return new EventClientReport();
    }

    public JSONObject b() {
        try {
            JSONObject b2 = super.b();
            if (b2 == null) {
                return null;
            }
            b2.put("eventId", this.f10077a);
            b2.put("eventType", this.b);
            b2.put("eventTime", this.c);
            b2.put("eventContent", this.d);
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
