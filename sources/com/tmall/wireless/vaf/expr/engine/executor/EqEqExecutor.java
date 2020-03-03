package com.tmall.wireless.vaf.expr.engine.executor;

import android.text.TextUtils;
import com.tmall.wireless.vaf.expr.engine.data.Data;

public class EqEqExecutor extends BinExecutor {
    /* access modifiers changed from: protected */
    public int a(Data data, String str, String str2) {
        data.a(TextUtils.equals(str, str2) ? 1 : 0);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, int i, int i2) {
        data.a(i == i2 ? 1 : 0);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, int i, float f) {
        data.a(((float) i) == f ? 1 : 0);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, float f, int i) {
        data.a(f == ((float) i) ? 1 : 0);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, float f, float f2) {
        data.a(f == f2 ? 1 : 0);
        return 1;
    }
}
