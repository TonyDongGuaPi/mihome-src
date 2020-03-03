package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.q;
import org.json.JSONObject;

public class i extends e {

    /* renamed from: a  reason: collision with root package name */
    public static final StatSpecifyReportedInfo f9279a;

    static {
        StatSpecifyReportedInfo statSpecifyReportedInfo = new StatSpecifyReportedInfo();
        f9279a = statSpecifyReportedInfo;
        statSpecifyReportedInfo.b("A9VH9B8L4GX4");
    }

    public i(Context context) {
        super(context, 0, f9279a);
    }

    public f a() {
        return f.NETWORK_DETECTOR;
    }

    public boolean a(JSONObject jSONObject) {
        q.a(jSONObject, "actky", StatConfig.b(this.l));
        return true;
    }
}
