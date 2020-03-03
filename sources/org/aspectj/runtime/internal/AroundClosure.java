package org.aspectj.runtime.internal;

import org.aspectj.lang.ProceedingJoinPoint;

public abstract class AroundClosure {
    protected int bitflags = 1048576;
    protected Object[] preInitializationState;
    protected Object[] state;

    public abstract Object run(Object[] objArr) throws Throwable;

    public AroundClosure() {
    }

    public AroundClosure(Object[] objArr) {
        this.state = objArr;
    }

    public int getFlags() {
        return this.bitflags;
    }

    public Object[] getState() {
        return this.state;
    }

    public Object[] getPreInitializationState() {
        return this.preInitializationState;
    }

    public ProceedingJoinPoint linkClosureAndJoinPoint() {
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) this.state[this.state.length - 1];
        proceedingJoinPoint.a(this);
        return proceedingJoinPoint;
    }

    public ProceedingJoinPoint linkClosureAndJoinPoint(int i) {
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) this.state[this.state.length - 1];
        proceedingJoinPoint.a(this);
        this.bitflags = i;
        return proceedingJoinPoint;
    }
}
