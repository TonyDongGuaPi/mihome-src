package com.tmall.wireless.vaf.expr.engine.executor;

import com.tmall.wireless.vaf.expr.engine.data.Data;

public class MulEqExecutor extends CompositeEqExecutor {
    /* access modifiers changed from: protected */
    public void a(Data data, int i, int i2) {
        data.a(i * i2);
    }

    /* access modifiers changed from: protected */
    public void a(Data data, int i, float f) {
        data.a(((float) i) * f);
    }

    /* access modifiers changed from: protected */
    public void a(Data data, float f, int i) {
        data.a(f * ((float) i));
    }

    /* access modifiers changed from: protected */
    public void a(Data data, float f, float f2) {
        data.a(f * f2);
    }
}
