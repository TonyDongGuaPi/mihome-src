package com.tencent.wxop.stat.a;

import android.content.Context;
import com.alipay.mobile.security.bio.api.BioDetector;
import com.mobikwik.sdk.lib.Constants;
import com.tencent.a.a.a.a.h;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.au;
import com.tencent.wxop.stat.common.a;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.q;
import com.xiaomi.stat.d;
import org.json.JSONObject;

public abstract class e {
    protected static String j;

    /* renamed from: a  reason: collision with root package name */
    private StatSpecifyReportedInfo f9275a = null;
    protected String b = null;
    protected long c;
    protected int d;
    protected a e = null;
    protected int f;
    protected String g = null;
    protected String h = null;
    protected String i = null;
    protected boolean k = false;
    protected Context l;

    e(Context context, int i2, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.l = context;
        this.c = System.currentTimeMillis() / 1000;
        this.d = i2;
        this.h = StatConfig.c(context);
        this.i = k.j(context);
        this.b = StatConfig.b(context);
        if (statSpecifyReportedInfo != null) {
            this.f9275a = statSpecifyReportedInfo;
            if (k.c(statSpecifyReportedInfo.c())) {
                this.b = statSpecifyReportedInfo.c();
            }
            if (k.c(statSpecifyReportedInfo.d())) {
                this.h = statSpecifyReportedInfo.d();
            }
            if (k.c(statSpecifyReportedInfo.b())) {
                this.i = statSpecifyReportedInfo.b();
            }
            this.k = statSpecifyReportedInfo.e();
        }
        this.g = StatConfig.e(context);
        this.e = au.a(context).b(context);
        this.f = a() != f.NETWORK_DETECTOR ? k.s(context).intValue() : -f.NETWORK_DETECTOR.a();
        if (!h.b(j)) {
            String g2 = StatConfig.g(context);
            j = g2;
            if (!k.c(g2)) {
                j = "0";
            }
        }
    }

    public abstract f a();

    public abstract boolean a(JSONObject jSONObject);

    public boolean b(JSONObject jSONObject) {
        try {
            q.a(jSONObject, "ky", this.b);
            jSONObject.put("et", a().a());
            if (this.e != null) {
                jSONObject.put(BioDetector.EXT_KEY_UI, this.e.b());
                q.a(jSONObject, "mc", this.e.c());
                int d2 = this.e.d();
                jSONObject.put("ut", d2);
                if (d2 == 0 && k.w(this.l) == 1) {
                    jSONObject.put(d.C, 1);
                }
            }
            q.a(jSONObject, "cui", this.g);
            if (a() != f.SESSION_ENV) {
                q.a(jSONObject, "av", this.i);
                q.a(jSONObject, "ch", this.h);
            }
            if (this.k) {
                jSONObject.put("impt", 1);
            }
            q.a(jSONObject, Constants.MID, j);
            jSONObject.put("idx", this.f);
            jSONObject.put("si", this.d);
            jSONObject.put("ts", this.c);
            jSONObject.put("dts", k.a(this.l, false));
            return a(jSONObject);
        } catch (Throwable unused) {
            return false;
        }
    }

    public long c() {
        return this.c;
    }

    public StatSpecifyReportedInfo d() {
        return this.f9275a;
    }

    public Context e() {
        return this.l;
    }

    public boolean f() {
        return this.k;
    }

    public String g() {
        try {
            JSONObject jSONObject = new JSONObject();
            b(jSONObject);
            return jSONObject.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}
