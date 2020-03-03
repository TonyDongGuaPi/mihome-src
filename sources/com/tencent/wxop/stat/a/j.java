package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.a;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.q;
import org.json.JSONObject;

public class j extends e {

    /* renamed from: a  reason: collision with root package name */
    private static String f9280a;
    private String m = null;
    private String n = null;

    public j(Context context, int i, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.m = a.a(context).b();
        if (f9280a == null) {
            f9280a = k.i(context);
        }
    }

    public f a() {
        return f.NETWORK_MONITOR;
    }

    public void a(String str) {
        this.n = str;
    }

    public boolean a(JSONObject jSONObject) {
        q.a(jSONObject, "op", f9280a);
        q.a(jSONObject, "cn", this.m);
        jSONObject.put("sp", this.n);
        return true;
    }
}
