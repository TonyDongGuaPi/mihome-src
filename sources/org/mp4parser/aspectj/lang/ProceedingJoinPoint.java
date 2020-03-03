package org.mp4parser.aspectj.lang;

import org.mp4parser.aspectj.runtime.internal.AroundClosure;

public interface ProceedingJoinPoint extends JoinPoint {
    Object a(Object[] objArr) throws Throwable;

    void a(AroundClosure aroundClosure);

    Object j() throws Throwable;
}
