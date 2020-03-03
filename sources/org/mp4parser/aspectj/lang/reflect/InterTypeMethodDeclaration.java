package org.mp4parser.aspectj.lang.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public interface InterTypeMethodDeclaration extends InterTypeDeclaration {
    String a();

    AjType<?> b();

    Type c();

    AjType<?>[] g();

    Type[] h();

    TypeVariable<Method>[] i();

    AjType<?>[] j();
}
