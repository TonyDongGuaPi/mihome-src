package org.mp4parser.aspectj.internal.lang.reflect;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.mp4parser.aspectj.lang.reflect.AjType;
import org.mp4parser.aspectj.lang.reflect.AjTypeSystem;
import org.mp4parser.aspectj.lang.reflect.InterTypeConstructorDeclaration;

public class InterTypeConstructorDeclarationImpl extends InterTypeDeclarationImpl implements InterTypeConstructorDeclaration {
    private Method b;

    public InterTypeConstructorDeclarationImpl(AjType<?> ajType, String str, int i, Method method) {
        super(ajType, str, i);
        this.b = method;
    }

    public AjType<?>[] a() {
        Class[] parameterTypes = this.b.getParameterTypes();
        AjType<?>[] ajTypeArr = new AjType[(parameterTypes.length - 1)];
        for (int i = 1; i < parameterTypes.length; i++) {
            ajTypeArr[i - 1] = AjTypeSystem.a(parameterTypes[i]);
        }
        return ajTypeArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: org.mp4parser.aspectj.lang.reflect.AjType[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.reflect.Type[] b() {
        /*
            r5 = this;
            java.lang.reflect.Method r0 = r5.b
            java.lang.reflect.Type[] r0 = r0.getGenericParameterTypes()
            int r1 = r0.length
            r2 = 1
            int r1 = r1 - r2
            org.mp4parser.aspectj.lang.reflect.AjType[] r1 = new org.mp4parser.aspectj.lang.reflect.AjType[r1]
        L_0x000b:
            int r3 = r0.length
            if (r2 >= r3) goto L_0x002a
            r3 = r0[r2]
            boolean r3 = r3 instanceof java.lang.Class
            if (r3 == 0) goto L_0x0021
            int r3 = r2 + -1
            r4 = r0[r2]
            java.lang.Class r4 = (java.lang.Class) r4
            org.mp4parser.aspectj.lang.reflect.AjType r4 = org.mp4parser.aspectj.lang.reflect.AjTypeSystem.a(r4)
            r1[r3] = r4
            goto L_0x0027
        L_0x0021:
            int r3 = r2 + -1
            r4 = r0[r2]
            r1[r3] = r4
        L_0x0027:
            int r2 = r2 + 1
            goto L_0x000b
        L_0x002a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.aspectj.internal.lang.reflect.InterTypeConstructorDeclarationImpl.b():java.lang.reflect.Type[]");
    }

    public AjType<?>[] c() {
        Class[] exceptionTypes = this.b.getExceptionTypes();
        AjType<?>[] ajTypeArr = new AjType[exceptionTypes.length];
        for (int i = 0; i < exceptionTypes.length; i++) {
            ajTypeArr[i] = AjTypeSystem.a(exceptionTypes[i]);
        }
        return ajTypeArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Modifier.toString(f()));
        stringBuffer.append(" ");
        stringBuffer.append(this.f3744a);
        stringBuffer.append(".new");
        stringBuffer.append(Operators.BRACKET_START_STR);
        AjType[] a2 = a();
        for (int i = 0; i < a2.length - 1; i++) {
            stringBuffer.append(a2[i].toString());
            stringBuffer.append(", ");
        }
        if (a2.length > 0) {
            stringBuffer.append(a2[a2.length - 1].toString());
        }
        stringBuffer.append(Operators.BRACKET_END_STR);
        return stringBuffer.toString();
    }
}
