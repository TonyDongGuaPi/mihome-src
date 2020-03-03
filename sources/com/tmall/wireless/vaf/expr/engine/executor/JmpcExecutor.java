package com.tmall.wireless.vaf.expr.engine.executor;

import android.text.TextUtils;
import android.util.Log;
import com.tmall.wireless.vaf.expr.engine.data.Data;

public class JmpcExecutor extends ArithExecutor {
    private static final String j = "JmpcExecutor_TMTEST";

    public int a(Object obj) {
        super.a(obj);
        int f = this.r.f();
        Data a2 = a((int) this.r.d());
        switch (a2.g) {
            case 1:
                if (a2.b() <= 0) {
                    this.r.a(f);
                    break;
                }
                break;
            case 2:
                if (a2.c() <= 0.0f) {
                    this.r.a(f);
                    break;
                }
                break;
            case 3:
                if (TextUtils.isEmpty(a2.d())) {
                    this.r.a(f);
                    break;
                }
                break;
            case 4:
                if (a2.e() == null) {
                    this.r.a(f);
                    break;
                }
                break;
            default:
                Log.e(j, "type invalidate:" + a2);
                return 2;
        }
        return 1;
    }
}
