package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.q;
import org.json.JSONObject;

public class k extends e {

    /* renamed from: a  reason: collision with root package name */
    Long f9281a = null;
    String m;
    String n;

    public k(Context context, String str, String str2, int i, Long l, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.n = str;
        this.m = str2;
        this.f9281a = l;
    }

    public f a() {
        return f.PAGE_VIEW;
    }

    public boolean a(JSONObject jSONObject) {
        q.a(jSONObject, "pi", this.m);
        q.a(jSONObject, "rf", this.n);
        if (this.f9281a == null) {
            return true;
        }
        jSONObject.put("du", this.f9281a);
        return true;
    }
}
