package org.aspectj.internal.lang.reflect;

import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.DeclareErrorOrWarning;
import org.aspectj.lang.reflect.PointcutExpression;

public class DeclareErrorOrWarningImpl implements DeclareErrorOrWarning {

    /* renamed from: a  reason: collision with root package name */
    private PointcutExpression f3439a;
    private String b;
    private boolean c;
    private AjType d;

    public DeclareErrorOrWarningImpl(String str, String str2, boolean z, AjType ajType) {
        this.f3439a = new PointcutExpressionImpl(str);
        this.b = str2;
        this.c = z;
        this.d = ajType;
    }

    public AjType a() {
        return this.d;
    }

    public PointcutExpression b() {
        return this.f3439a;
    }

    public String c() {
        return this.b;
    }

    public boolean d() {
        return this.c;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("declare ");
        stringBuffer.append(d() ? "error : " : "warning : ");
        stringBuffer.append(b().a());
        stringBuffer.append(" : ");
        stringBuffer.append("\"");
        stringBuffer.append(c());
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }
}
