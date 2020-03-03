package org.aspectj.internal.lang.reflect;

import java.lang.annotation.Annotation;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.DeclareAnnotation;
import org.aspectj.lang.reflect.SignaturePattern;
import org.aspectj.lang.reflect.TypePattern;

public class DeclareAnnotationImpl implements DeclareAnnotation {

    /* renamed from: a  reason: collision with root package name */
    private Annotation f3437a;
    private String b;
    private AjType<?> c;
    private DeclareAnnotation.Kind d;
    private TypePattern e;
    private SignaturePattern f;

    public DeclareAnnotationImpl(AjType<?> ajType, String str, String str2, Annotation annotation, String str3) {
        this.c = ajType;
        if (str.equals("at_type")) {
            this.d = DeclareAnnotation.Kind.Type;
        } else if (str.equals("at_field")) {
            this.d = DeclareAnnotation.Kind.Field;
        } else if (str.equals("at_method")) {
            this.d = DeclareAnnotation.Kind.Method;
        } else if (str.equals("at_constructor")) {
            this.d = DeclareAnnotation.Kind.Constructor;
        } else {
            throw new IllegalStateException("Unknown declare annotation kind: " + str);
        }
        if (this.d == DeclareAnnotation.Kind.Type) {
            this.e = new TypePatternImpl(str2);
        } else {
            this.f = new SignaturePatternImpl(str2);
        }
        this.f3437a = annotation;
        this.b = str3;
    }

    public AjType<?> a() {
        return this.c;
    }

    public DeclareAnnotation.Kind b() {
        return this.d;
    }

    public SignaturePattern c() {
        return this.f;
    }

    public TypePattern d() {
        return this.e;
    }

    public Annotation e() {
        return this.f3437a;
    }

    public String f() {
        return this.b;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("declare @");
        switch (b()) {
            case Type:
                stringBuffer.append("type : ");
                stringBuffer.append(d().a());
                break;
            case Method:
                stringBuffer.append("method : ");
                stringBuffer.append(c().a());
                break;
            case Field:
                stringBuffer.append("field : ");
                stringBuffer.append(c().a());
                break;
            case Constructor:
                stringBuffer.append("constructor : ");
                stringBuffer.append(c().a());
                break;
        }
        stringBuffer.append(" : ");
        stringBuffer.append(f());
        return stringBuffer.toString();
    }
}
