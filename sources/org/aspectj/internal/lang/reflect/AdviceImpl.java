package org.aspectj.internal.lang.reflect;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.aspectj.lang.annotation.AdviceName;
import org.aspectj.lang.reflect.Advice;
import org.aspectj.lang.reflect.AdviceKind;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.AjTypeSystem;
import org.aspectj.lang.reflect.PointcutExpression;

public class AdviceImpl implements Advice {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3434a = "org.aspectj.runtime.internal";
    private final AdviceKind b;
    private final Method c;
    private PointcutExpression d;
    private boolean e = false;
    private Type[] f;
    private AjType[] g;
    private AjType[] h;

    protected AdviceImpl(Method method, String str, AdviceKind adviceKind) {
        this.b = adviceKind;
        this.c = method;
        this.d = new PointcutExpressionImpl(str);
    }

    protected AdviceImpl(Method method, String str, AdviceKind adviceKind, String str2) {
        this(method, str, adviceKind);
    }

    public AjType a() {
        return AjTypeSystem.a(this.c.getDeclaringClass());
    }

    public Type[] b() {
        if (this.f == null) {
            Type[] genericParameterTypes = this.c.getGenericParameterTypes();
            int i = 0;
            for (Type type : genericParameterTypes) {
                if ((type instanceof Class) && ((Class) type).getPackage().getName().equals(f3434a)) {
                    i++;
                }
            }
            this.f = new Type[(genericParameterTypes.length - i)];
            for (int i2 = 0; i2 < this.f.length; i2++) {
                if (genericParameterTypes[i2] instanceof Class) {
                    this.f[i2] = AjTypeSystem.a((Class) genericParameterTypes[i2]);
                } else {
                    this.f[i2] = genericParameterTypes[i2];
                }
            }
        }
        return this.f;
    }

    public AjType<?>[] c() {
        if (this.g == null) {
            Class[] parameterTypes = this.c.getParameterTypes();
            int i = 0;
            for (Class cls : parameterTypes) {
                if (cls.getPackage().getName().equals(f3434a)) {
                    i++;
                }
            }
            this.g = new AjType[(parameterTypes.length - i)];
            for (int i2 = 0; i2 < this.g.length; i2++) {
                this.g[i2] = AjTypeSystem.a(parameterTypes[i2]);
            }
        }
        return this.g;
    }

    public AjType<?>[] d() {
        if (this.h == null) {
            Class[] exceptionTypes = this.c.getExceptionTypes();
            this.h = new AjType[exceptionTypes.length];
            for (int i = 0; i < exceptionTypes.length; i++) {
                this.h[i] = AjTypeSystem.a(exceptionTypes[i]);
            }
        }
        return this.h;
    }

    public AdviceKind e() {
        return this.b;
    }

    public String f() {
        String name = this.c.getName();
        if (!name.startsWith("ajc$")) {
            return name;
        }
        AdviceName adviceName = (AdviceName) this.c.getAnnotation(AdviceName.class);
        if (adviceName != null) {
            return adviceName.value();
        }
        return "";
    }

    public PointcutExpression g() {
        return this.d;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (f().length() > 0) {
            stringBuffer.append("@AdviceName(\"");
            stringBuffer.append(f());
            stringBuffer.append("\") ");
        }
        if (e() == AdviceKind.AROUND) {
            stringBuffer.append(this.c.getGenericReturnType().toString());
            stringBuffer.append(" ");
        }
        switch (e()) {
            case AFTER:
                stringBuffer.append("after(");
                break;
            case AFTER_RETURNING:
                stringBuffer.append("after(");
                break;
            case AFTER_THROWING:
                stringBuffer.append("after(");
                break;
            case AROUND:
                stringBuffer.append("around(");
                break;
            case BEFORE:
                stringBuffer.append("before(");
                break;
        }
        AjType[] c2 = c();
        int length = c2.length;
        if (this.e) {
            length--;
        }
        int i = 0;
        int i2 = 0;
        while (i2 < length) {
            stringBuffer.append(c2[i2].a());
            i2++;
            if (i2 < length) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(") ");
        switch (e()) {
            case AFTER_RETURNING:
                stringBuffer.append("returning");
                if (this.e) {
                    stringBuffer.append(Operators.BRACKET_START_STR);
                    stringBuffer.append(c2[length - 1].a());
                    stringBuffer.append(") ");
                    break;
                }
                break;
            case AFTER_THROWING:
                break;
        }
        stringBuffer.append("throwing");
        if (this.e) {
            stringBuffer.append(Operators.BRACKET_START_STR);
            stringBuffer.append(c2[length - 1].a());
            stringBuffer.append(") ");
        }
        AjType[] d2 = d();
        if (d2.length > 0) {
            stringBuffer.append("throws ");
            while (i < d2.length) {
                stringBuffer.append(d2[i].a());
                i++;
                if (i < d2.length) {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append(" ");
        }
        stringBuffer.append(": ");
        stringBuffer.append(g().a());
        return stringBuffer.toString();
    }
}
