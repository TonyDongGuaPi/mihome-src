package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.StatServiceImpl;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import java.util.Map;
import java.util.Properties;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends e {

    /* renamed from: a  reason: collision with root package name */
    protected c f9272a = new c();
    private long m = -1;

    public b(Context context, int i, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f9272a.f9273a = str;
    }

    private void h() {
        Properties b;
        if (this.f9272a.f9273a != null && (b = StatServiceImpl.b(this.f9272a.f9273a)) != null && b.size() > 0) {
            if (this.f9272a.c == null || this.f9272a.c.length() == 0) {
                this.f9272a.c = new JSONObject(b);
                return;
            }
            for (Map.Entry entry : b.entrySet()) {
                try {
                    this.f9272a.c.put(entry.getKey().toString(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public f a() {
        return f.CUSTOM;
    }

    public void a(long j) {
        this.m = j;
    }

    public boolean a(JSONObject jSONObject) {
        String str;
        Object obj;
        jSONObject.put("ei", this.f9272a.f9273a);
        if (this.m > 0) {
            jSONObject.put("du", this.m);
        }
        if (this.f9272a.b == null) {
            h();
            str = "kv";
            obj = this.f9272a.c;
        } else {
            str = "ar";
            obj = this.f9272a.b;
        }
        jSONObject.put(str, obj);
        return true;
    }

    public c b() {
        return this.f9272a;
    }
}
