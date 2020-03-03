package org.mp4parser.aspectj.internal.lang.reflect;

import org.mp4parser.aspectj.lang.reflect.AjType;
import org.mp4parser.aspectj.lang.reflect.DeclareErrorOrWarning;
import org.mp4parser.aspectj.lang.reflect.PointcutExpression;

public class DeclareErrorOrWarningImpl implements DeclareErrorOrWarning {

    /* renamed from: a  reason: collision with root package name */
    private PointcutExpression f3740a;
    private String b;
    private boolean c;
    private AjType d;

    public DeclareErrorOrWarningImpl(String str, String str2, boolean z, AjType ajType) {
        this.f3740a = new PointcutExpressionImpl(str);
        this.b = str2;
        this.c = z;
        this.d = ajType;
    }

    public AjType a() {
        return this.d;
    }

    public PointcutExpression b() {
        return this.f3740a;
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
