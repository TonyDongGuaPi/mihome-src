package com.huawei.hms.support.log;

import android.content.Context;
import com.huawei.hms.support.log.a.a;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final c f5899a = new a.C0056a(new a());
    private int b = 4;
    private String c;

    public void a(Context context, int i, String str) {
        this.b = i;
        this.c = str;
        f5899a.a(context, "HMSCore");
    }

    public boolean a(int i) {
        return i >= this.b;
    }

    public void a(int i, String str, String str2) {
        if (a(i)) {
            d a2 = a(i, str, str2, (Throwable) null);
            f5899a.a(a2.a() + a2.b(), i, str, str2);
        }
    }

    public void a(String str, String str2) {
        d a2 = a(4, str, str2, (Throwable) null);
        f5899a.a(a2.a() + 10 + a2.b(), 4, str, str2);
    }

    private d a(int i, String str, String str2, Throwable th) {
        d dVar = new d(8, this.c, i, str);
        dVar.a(str2);
        dVar.a(th);
        return dVar;
    }
}
