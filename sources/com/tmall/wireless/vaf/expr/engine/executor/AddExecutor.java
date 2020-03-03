package com.tmall.wireless.vaf.expr.engine.executor;

import com.tmall.wireless.vaf.expr.engine.data.Data;

public class AddExecutor extends BinExecutor {
    /* access modifiers changed from: protected */
    public int a(Data data, int i, int i2) {
        data.a(i + i2);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, int i, float f) {
        data.a(((float) i) + f);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, int i, String str) {
        data.a(i + str);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, float f, int i) {
        data.a(f + ((float) i));
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, float f, float f2) {
        data.a(f + f2);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, float f, String str) {
        data.a(f + str);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, String str, int i) {
        data.a(str + i);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, String str, float f) {
        data.a(str + f);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, String str, String str2) {
        data.a(str + str2);
        return 1;
    }
}
