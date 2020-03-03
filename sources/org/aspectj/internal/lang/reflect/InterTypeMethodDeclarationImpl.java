package org.aspectj.internal.lang.reflect;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.AjTypeSystem;
import org.aspectj.lang.reflect.InterTypeMethodDeclaration;

public class InterTypeMethodDeclarationImpl extends InterTypeDeclarationImpl implements InterTypeMethodDeclaration {
    private String b;
    private Method c;
    private int d;
    private AjType<?>[] e;
    private Type[] f;
    private AjType<?> g;
    private Type h;
    private AjType<?>[] i;

    public InterTypeMethodDeclarationImpl(AjType<?> ajType, String str, int i2, String str2, Method method) {
        super(ajType, str, i2);
        this.d = 1;
        this.b = str2;
        this.c = method;
    }

    public InterTypeMethodDeclarationImpl(AjType<?> ajType, AjType<?> ajType2, Method method, int i2) {
        super(ajType, ajType2, i2);
        this.d = 1;
        this.d = 0;
        this.b = method.getName();
        this.c = method;
    }

    public String a() {
        return this.b;
    }

    public AjType<?> b() {
        return AjTypeSystem.a(this.c.getReturnType());
    }

    public Type c() {
        Type genericReturnType = this.c.getGenericReturnType();
        return genericReturnType instanceof Class ? AjTypeSystem.a((Class) genericReturnType) : genericReturnType;
    }

    public AjType<?>[] g() {
        Class[] parameterTypes = this.c.getParameterTypes();
        AjType<?>[] ajTypeArr = new AjType[(parameterTypes.length - this.d)];
        for (int i2 = this.d; i2 < parameterTypes.length; i2++) {
            ajTypeArr[i2 - this.d] = AjTypeSystem.a(parameterTypes[i2]);
        }
        return ajTypeArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: org.aspectj.lang.reflect.AjType[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.reflect.Type[] h() {
        /*
            r5 = this;
            java.lang.reflect.Method r0 = r5.c
            java.lang.reflect.Type[] r0 = r0.getGenericParameterTypes()
            int r1 = r0.length
            int r2 = r5.d
            int r1 = r1 - r2
            org.aspectj.lang.reflect.AjType[] r1 = new org.aspectj.lang.reflect.AjType[r1]
            int r2 = r5.d
        L_0x000e:
            int r3 = r0.length
            if (r2 >= r3) goto L_0x0031
            r3 = r0[r2]
            boolean r3 = r3 instanceof java.lang.Class
            if (r3 == 0) goto L_0x0026
            int r3 = r5.d
            int r3 = r2 - r3
            r4 = r0[r2]
            java.lang.Class r4 = (java.lang.Class) r4
            org.aspectj.lang.reflect.AjType r4 = org.aspectj.lang.reflect.AjTypeSystem.a(r4)
            r1[r3] = r4
            goto L_0x002e
        L_0x0026:
            int r3 = r5.d
            int r3 = r2 - r3
            r4 = r0[r2]
            r1[r3] = r4
        L_0x002e:
            int r2 = r2 + 1
            goto L_0x000e
        L_0x0031:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.aspectj.internal.lang.reflect.InterTypeMethodDeclarationImpl.h():java.lang.reflect.Type[]");
    }

    public TypeVariable<Method>[] i() {
        return this.c.getTypeParameters();
    }

    public AjType<?>[] j() {
        Class[] exceptionTypes = this.c.getExceptionTypes();
        AjType<?>[] ajTypeArr = new AjType[exceptionTypes.length];
        for (int i2 = 0; i2 < exceptionTypes.length; i2++) {
            ajTypeArr[i2] = AjTypeSystem.a(exceptionTypes[i2]);
        }
        return ajTypeArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Modifier.toString(f()));
        stringBuffer.append(" ");
        stringBuffer.append(b().toString());
        stringBuffer.append(" ");
        stringBuffer.append(this.f3443a);
        stringBuffer.append(".");
        stringBuffer.append(a());
        stringBuffer.append(Operators.BRACKET_START_STR);
        AjType[] g2 = g();
        for (int i2 = 0; i2 < g2.length - 1; i2++) {
            stringBuffer.append(g2[i2].toString());
            stringBuffer.append(", ");
        }
        if (g2.length > 0) {
            stringBuffer.append(g2[g2.length - 1].toString());
        }
        stringBuffer.append(Operators.BRACKET_END_STR);
        return stringBuffer.toString();
    }
}
