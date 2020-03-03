package org.aspectj.internal.lang.reflect;

import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.AjTypeSystem;
import org.aspectj.lang.reflect.DeclareSoft;
import org.aspectj.lang.reflect.PointcutExpression;

public class DeclareSoftImpl implements DeclareSoft {

    /* renamed from: a  reason: collision with root package name */
    private AjType<?> f3442a;
    private PointcutExpression b;
    private AjType<?> c;
    private String d;

    public DeclareSoftImpl(AjType<?> ajType, String str, String str2) {
        this.f3442a = ajType;
        this.b = new PointcutExpressionImpl(str);
        try {
            this.c = AjTypeSystem.a(Class.forName(str2, false, ajType.e().getClassLoader()));
        } catch (ClassNotFoundException unused) {
            this.d = str2;
        }
    }

    public AjType a() {
        return this.f3442a;
    }

    public AjType b() throws ClassNotFoundException {
        if (this.d == null) {
            return this.c;
        }
        throw new ClassNotFoundException(this.d);
    }

    public PointcutExpression c() {
        return this.b;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("declare soft : ");
        if (this.d != null) {
            stringBuffer.append(this.c.a());
        } else {
            stringBuffer.append(this.d);
        }
        stringBuffer.append(" : ");
        stringBuffer.append(c().a());
        return stringBuffer.toString();
    }
}
