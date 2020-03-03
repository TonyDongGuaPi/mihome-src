package org.mp4parser.aspectj.lang;

import org.mp4parser.aspectj.lang.reflect.SourceLocation;

public interface JoinPoint {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3756a = "method-execution";
    public static final String b = "method-call";
    public static final String c = "constructor-execution";
    public static final String d = "constructor-call";
    public static final String e = "field-get";
    public static final String f = "field-set";
    public static final String g = "staticinitialization";
    public static final String h = "preinitialization";
    public static final String i = "initialization";
    public static final String j = "exception-handler";
    public static final String k = "lock";
    public static final String l = "unlock";
    public static final String m = "adviceexecution";

    public interface EnclosingStaticPart extends StaticPart {
    }

    public interface StaticPart {
        Signature a();

        SourceLocation b();

        String c();

        int d();

        String e();

        String f();

        String toString();
    }

    String a();

    String b();

    Object c();

    Object d();

    Object[] e();

    Signature f();

    SourceLocation g();

    String h();

    StaticPart i();

    String toString();
}
