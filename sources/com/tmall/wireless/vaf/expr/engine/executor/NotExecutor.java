package com.tmall.wireless.vaf.expr.engine.executor;

import android.text.TextUtils;
import android.util.Log;
import com.tmall.wireless.vaf.expr.engine.data.Data;

public class NotExecutor extends ArithExecutor {
    private static final String j = "NotExecutor_TMTEST";

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
        int i = 0;
        switch (a3.g) {
            case 1:
                if (a3.b() == 0) {
                    i = 1;
                }
                a4.a(i);
                break;
            case 2:
                if (0.0f == a3.c()) {
                    i = 1;
                }
                a4.a(i);
                break;
            case 3:
                a4.a(TextUtils.isEmpty(a3.d()) ? 1 : 0);
                break;
            default:
                Log.e(j, "invalidate type:" + a3.g);
                return 2;
        }
        return 1;
    }
}
