package com.tmall.wireless.vaf.expr.engine.executor;

import android.util.Log;
import com.tmall.wireless.vaf.expr.engine.data.Data;

public class MinusExecutor extends ArithExecutor {
    private static final String j = "MinusExecutor_TMTEST";

    public int a(Object obj) {
        int a2 = super.a(obj);
        byte d = this.r.d();
        Data a3 = a((int) d);
        if (d == 0) {
            this.h = this.r.d();
        }
        Data a4 = this.s.a(this.h);
        if (a3 == null || a4 == null) {
            Log.e(j, "read data failed");
            return a2;
        }
        switch (a3.g) {
            case 1:
                a4.a(-a3.b());
                return 1;
            case 2:
                a4.a(-a3.c());
                return 1;
            default:
                Log.e(j, "invalidate type:" + a3.g);
                return 2;
        }
    }
}
