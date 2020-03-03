package com.tmall.wireless.vaf.expr.engine.executor;

import com.tmall.wireless.vaf.expr.engine.data.Data;

public class AddEqExecutor extends CompositeEqExecutor {
    /* access modifiers changed from: protected */
    public void a(Data data, int i, int i2) {
        data.a(i + i2);
    }

    /* access modifiers changed from: protected */
    public void a(Data data, int i, float f) {
        data.a(((float) i) + f);
    }

    /* access modifiers changed from: protected */
    public void a(Data data, int i, String str) {
        data.a(i + str);
    }

    /* access modifiers changed from: protected */
    public void a(Data data, float f, int i) {
        data.a(f + ((float) i));
    }

    /* access modifiers changed from: protected */
    public void a(Data data, float f, float f2) {
        data.a(f + f2);
    }

    /* access modifiers changed from: protected */
    public void a(Data data, float f, String str) {
        data.a(f + str);
    }

    /* access modifiers changed from: protected */
    public void a(Data data, String str, int i) {
        data.a(str + i);
    }

    /* access modifiers changed from: protected */
    public void a(Data data, String str, float f) {
        data.a(str + f);
    }

    /* access modifiers changed from: protected */
    public void a(Data data, String str, String str2) {
        data.a(str + str2);
    }
}
