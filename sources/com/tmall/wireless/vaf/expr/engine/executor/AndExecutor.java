package com.tmall.wireless.vaf.expr.engine.executor;

import com.tmall.wireless.vaf.expr.engine.data.Data;

public class AndExecutor extends BinExecutor {
    private static final String j = "AndExecutor_TMTEST";

    /* access modifiers changed from: protected */
    public int a(Data data, int i, int i2) {
        data.a((1 == i && 1 == i2) ? 1 : 0);
        return 1;
    }
}
