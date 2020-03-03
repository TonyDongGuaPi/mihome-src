package org.aspectj.lang.reflect;

public interface DeclareSoft {
    AjType a();

    AjType b() throws ClassNotFoundException;

    PointcutExpression c();
}
