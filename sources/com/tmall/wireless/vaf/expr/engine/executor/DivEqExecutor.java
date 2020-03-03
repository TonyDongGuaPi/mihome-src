package com.tmall.wireless.vaf.expr.engine.executor;

import android.util.Log;
import com.tmall.wireless.vaf.expr.engine.data.Data;

public class DivEqExecutor extends CompositeEqExecutor {
    private static final String u = "DivEqExecutor";

    /* access modifiers changed from: protected */
    public void a(Data data, int i, int i2) {
        if (i2 == 0) {
            Log.e(u, "div zero");
        }
        data.a(i / i2);
    }

    /* access modifiers changed from: protected */
    public void a(Data data, int i, float f) {
        data.a(((float) i) / f);
    }

    /* access modifiers changed from: protected */
    public void a(Data data, float f, int i) {
        if (i == 0) {
            Log.e(u, "div zero");
        }
        data.a(f / ((float) i));
    }

    /* access modifiers changed from: protected */
    public void a(Data data, float f, float f2) {
        data.a(f / f2);
    }
}
