package com.tmall.wireless.vaf.expr.engine.executor;

public class JmpExecutor extends ArithExecutor {
    private static final String j = "JmpExecutor_TMTEST";

    public int a(Object obj) {
        super.a(obj);
        this.r.a(this.r.f());
        return 1;
    }
}
