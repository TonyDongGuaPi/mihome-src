package org.mp4parser.aspectj.runtime.internal;

import org.mp4parser.aspectj.lang.ProceedingJoinPoint;

public abstract class AroundClosure {

    /* renamed from: a  reason: collision with root package name */
    protected Object[] f3760a;
    protected int b = 1048576;
    protected Object[] c;

    public abstract Object a(Object[] objArr) throws Throwable;

    public AroundClosure() {
    }

    public AroundClosure(Object[] objArr) {
        this.f3760a = objArr;
    }

    public int a() {
        return this.b;
    }

    public Object[] b() {
        return this.f3760a;
    }

    public Object[] c() {
        return this.c;
    }

    public ProceedingJoinPoint d() {
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) this.f3760a[this.f3760a.length - 1];
        proceedingJoinPoint.a(this);
        return proceedingJoinPoint;
    }

    public ProceedingJoinPoint a(int i) {
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) this.f3760a[this.f3760a.length - 1];
        proceedingJoinPoint.a(this);
        this.b = i;
        return proceedingJoinPoint;
    }
}
