package com.tmall.wireless.vaf.expr.engine.executor;

import com.libra.expr.common.StringSupport;
import com.tmall.wireless.vaf.expr.engine.CodeReader;
import com.tmall.wireless.vaf.expr.engine.DataManager;
import com.tmall.wireless.vaf.expr.engine.EngineContext;
import com.tmall.wireless.vaf.expr.engine.NativeObjectManager;
import com.tmall.wireless.vaf.expr.engine.RegisterManager;

public abstract class Executor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9358a = "Executor_TMTEST";
    public static final int l = 1;
    public static final int m = 2;
    protected Object n;
    protected EngineContext o;
    protected StringSupport p;
    protected NativeObjectManager q;
    protected CodeReader r;
    protected RegisterManager s;
    protected DataManager t;

    public void a() {
    }

    public void a(EngineContext engineContext) {
        this.o = engineContext;
        this.p = this.o.c();
        this.q = this.o.g();
        this.r = this.o.d();
        this.s = this.o.e();
        this.t = this.o.f();
    }

    public void d() {
        this.n = null;
    }

    public int a(Object obj) {
        this.n = obj;
        return 2;
    }
}
