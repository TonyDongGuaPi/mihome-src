package org.aspectj.internal.lang.reflect;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Method;
import java.util.StringTokenizer;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.AjTypeSystem;
import org.aspectj.lang.reflect.Pointcut;
import org.aspectj.lang.reflect.PointcutExpression;

public class PointcutImpl implements Pointcut {

    /* renamed from: a  reason: collision with root package name */
    private final String f3448a;
    private final PointcutExpression b;
    private final Method c;
    private final AjType d;
    private String[] e = new String[0];

    protected PointcutImpl(String str, String str2, Method method, AjType ajType, String str3) {
        this.f3448a = str;
        this.b = new PointcutExpressionImpl(str2);
        this.c = method;
        this.d = ajType;
        this.e = a(str3);
    }

    public PointcutExpression a() {
        return this.b;
    }

    public String b() {
        return this.f3448a;
    }

    public int c() {
        return this.c.getModifiers();
    }

    public AjType<?>[] d() {
        Class[] parameterTypes = this.c.getParameterTypes();
        AjType<?>[] ajTypeArr = new AjType[parameterTypes.length];
        for (int i = 0; i < ajTypeArr.length; i++) {
            ajTypeArr[i] = AjTypeSystem.a(parameterTypes[i]);
        }
        return ajTypeArr;
    }

    public AjType e() {
        return this.d;
    }

    public String[] f() {
        return this.e;
    }

    private String[] a(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        String[] strArr = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = stringTokenizer.nextToken().trim();
        }
        return strArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(b());
        stringBuffer.append(Operators.BRACKET_START_STR);
        AjType[] d2 = d();
        int i = 0;
        while (i < d2.length) {
            stringBuffer.append(d2[i].a());
            if (!(this.e == null || this.e[i] == null)) {
                stringBuffer.append(" ");
                stringBuffer.append(this.e[i]);
            }
            i++;
            if (i < d2.length) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(") : ");
        stringBuffer.append(a().a());
        return stringBuffer.toString();
    }
}
