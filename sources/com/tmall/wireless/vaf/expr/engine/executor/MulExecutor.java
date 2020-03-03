package com.tmall.wireless.vaf.expr.engine.executor;

import com.tmall.wireless.vaf.expr.engine.data.Data;

public class MulExecutor extends BinExecutor {
    /* access modifiers changed from: protected */
    public int a(Data data, int i, int i2) {
        data.a(i * i2);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, int i, float f) {
        data.a(((float) i) * f);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, float f, int i) {
        data.a(f * ((float) i));
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, float f, float f2) {
        data.a(f * f2);
        return 1;
    }
}
