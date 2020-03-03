package com.tmall.wireless.vaf.expr.engine;

import com.libra.expr.common.StringSupport;
import com.tmall.wireless.vaf.expr.engine.finder.ObjectFinderManager;

public class EngineContext {

    /* renamed from: a  reason: collision with root package name */
    private CodeReader f9347a = new CodeReader();
    private RegisterManager b = new RegisterManager();
    private DataManager c = new DataManager();
    private ObjectFinderManager d = new ObjectFinderManager();
    private NativeObjectManager e;
    private StringSupport f;

    public void a() {
        this.f9347a = null;
        this.b.a();
        this.b = null;
        this.c = null;
        this.e = null;
        this.f = null;
        this.d = null;
    }

    public ObjectFinderManager b() {
        return this.d;
    }

    public StringSupport c() {
        return this.f;
    }

    public void a(StringSupport stringSupport) {
        this.f = stringSupport;
    }

    public void a(NativeObjectManager nativeObjectManager) {
        this.e = nativeObjectManager;
    }

    public CodeReader d() {
        return this.f9347a;
    }

    public RegisterManager e() {
        return this.b;
    }

    public DataManager f() {
        return this.c;
    }

    public NativeObjectManager g() {
        return this.e;
    }
}
