package com.tmall.wireless.vaf.expr.engine.executor;

import android.text.TextUtils;
import android.util.Log;
import com.tmall.wireless.vaf.expr.engine.data.Data;

public class TerExecutor extends ArithExecutor {
    private static final String j = "TerExecutor_TMTEST";

    public int a(Object obj) {
        int a2 = super.a(obj);
        short e = this.r.e();
        short s = e & 7;
        int i = (e >> 3) & 7;
        int i2 = (e >> 6) & 7;
        Data a3 = a((int) s);
        Data a4 = a(i);
        Data a5 = a(i2);
        if (!(4 == s || 4 == i || 4 == i2)) {
            this.h = this.r.d();
        }
        Data a6 = this.s.a(this.h);
        if (a6 == null) {
            return a2;
        }
        switch (a3.g) {
            case 1:
                if (a3.b() != 0) {
                    a6.a(a4);
                    return 1;
                }
                a6.a(a5);
                return 1;
            case 2:
                double c = (double) a3.c();
                if (c > 1.0E-7d || c < -1.0E-7d) {
                    a6.a(a4);
                    return 1;
                }
                a6.a(a5);
                return 1;
            case 3:
                if (!TextUtils.isEmpty(a3.d())) {
                    a6.a(a4);
                    return 1;
                }
                a6.a(a5);
                return 1;
            default:
                Log.e(j, "type error:" + s);
                return 2;
        }
    }
}
