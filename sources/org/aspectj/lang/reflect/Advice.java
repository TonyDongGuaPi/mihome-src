package org.aspectj.lang.reflect;

import java.lang.reflect.Type;

public interface Advice {
    AjType a();

    Type[] b();

    AjType<?>[] c();

    AjType<?>[] d();

    AdviceKind e();

    String f();

    PointcutExpression g();
}
