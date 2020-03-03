package com.tmall.wireless.vaf.expr.engine.executor;

import android.util.Log;
import com.tmall.wireless.vaf.expr.engine.data.Data;

public class DivExecutor extends BinExecutor {
    private static final String j = "DivExecutor_TMTEST";

    /* access modifiers changed from: protected */
    public int a(Data data, int i, int i2) {
        if (i2 == 0) {
            Log.e(j, "div zero");
            return 2;
        }
        data.a(i / i2);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, int i, float f) {
        data.a(((float) i) / f);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, float f, int i) {
        if (i == 0) {
            Log.e(j, "div zero");
            return 2;
        }
        data.a(f / ((float) i));
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, float f, float f2) {
        data.a(f / f2);
        return 1;
    }
}
