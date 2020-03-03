package org.mp4parser.aspectj.lang.reflect;

import java.lang.annotation.Annotation;

public interface DeclareAnnotation {

    public enum Kind {
        Field,
        Method,
        Constructor,
        Type
    }

    AjType<?> a();

    Kind b();

    SignaturePattern c();

    TypePattern d();

    Annotation e();

    String f();
}
