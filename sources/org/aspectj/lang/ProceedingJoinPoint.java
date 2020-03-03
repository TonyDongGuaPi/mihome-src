package org.aspectj.lang;

import org.aspectj.runtime.internal.AroundClosure;

public interface ProceedingJoinPoint extends JoinPoint {
    Object a(Object[] objArr) throws Throwable;

    void a(AroundClosure aroundClosure);

    Object j() throws Throwable;
}
