package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.b;
import com.tencent.wxop.stat.common.q;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.json.JSONObject;

public class d extends e {

    /* renamed from: a  reason: collision with root package name */
    private String f9274a;
    private int m;
    private int n = 100;
    private Thread o = null;

    public d(Context context, int i, int i2, Throwable th, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        a(i2, th);
    }

    public d(Context context, int i, int i2, Throwable th, Thread thread, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        a(i2, th);
        this.o = thread;
    }

    public d(Context context, int i, String str, int i2, int i3, Thread thread, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        if (str != null) {
            i3 = i3 <= 0 ? StatConfig.x() : i3;
            if (str.length() <= i3) {
                this.f9274a = str;
            } else {
                this.f9274a = str.substring(0, i3);
            }
        }
        this.o = thread;
        this.m = i2;
    }

    private void a(int i, Throwable th) {
        if (th != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            this.f9274a = stringWriter.toString();
            this.m = i;
            printWriter.close();
        }
    }

    public f a() {
        return f.ERROR;
    }

    public boolean a(JSONObject jSONObject) {
        q.a(jSONObject, "er", this.f9274a);
        jSONObject.put("ea", this.m);
        if (this.m != 2 && this.m != 3) {
            return true;
        }
        new b(this.l).a(jSONObject, this.o);
        return true;
    }
}
