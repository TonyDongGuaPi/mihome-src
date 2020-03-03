package org.aspectj.internal.lang.reflect;

import java.lang.reflect.Type;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.DeclareParents;
import org.aspectj.lang.reflect.TypePattern;

public class DeclareParentsImpl implements DeclareParents {

    /* renamed from: a  reason: collision with root package name */
    private AjType<?> f3440a;
    private TypePattern b;
    private Type[] c;
    private String d;
    private String e;
    private boolean f;
    private boolean g = false;

    public DeclareParentsImpl(String str, String str2, boolean z, AjType<?> ajType) {
        this.b = new TypePatternImpl(str);
        this.f = z;
        this.f3440a = ajType;
        this.d = str2;
        try {
            this.c = StringToType.a(str2, ajType.e());
        } catch (ClassNotFoundException e2) {
            this.g = true;
            this.e = e2.getMessage();
        }
    }

    public AjType a() {
        return this.f3440a;
    }

    public TypePattern b() {
        return this.b;
    }

    public boolean c() {
        return this.f;
    }

    public boolean d() {
        return !this.f;
    }

    public Type[] e() throws ClassNotFoundException {
        if (!this.g) {
            return this.c;
        }
        throw new ClassNotFoundException(this.e);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("declare parents : ");
        stringBuffer.append(b().a());
        stringBuffer.append(c() ? " extends " : " implements ");
        stringBuffer.append(this.d);
        return stringBuffer.toString();
    }
}
