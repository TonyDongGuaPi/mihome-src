package org.mp4parser.aspectj.runtime.reflect;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import org.mp4parser.aspectj.lang.reflect.MethodSignature;

class MethodSignatureImpl extends CodeSignatureImpl implements MethodSignature {

    /* renamed from: a  reason: collision with root package name */
    Class f3776a;
    private Method o;

    MethodSignatureImpl(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2, Class cls2) {
        super(i, str, cls, clsArr, strArr, clsArr2);
        this.f3776a = cls2;
    }

    MethodSignatureImpl(String str) {
        super(str);
    }

    public Class g() {
        if (this.f3776a == null) {
            this.f3776a = c(6);
        }
        return this.f3776a;
    }

    /* access modifiers changed from: protected */
    public String a(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.a(d()));
        if (stringMaker.b) {
            stringBuffer.append(stringMaker.a(g()));
        }
        if (stringMaker.b) {
            stringBuffer.append(" ");
        }
        stringBuffer.append(stringMaker.a(e(), f()));
        stringBuffer.append(".");
        stringBuffer.append(c());
        stringMaker.b(stringBuffer, i());
        stringMaker.c(stringBuffer, k());
        return stringBuffer.toString();
    }

    public Method h() {
        if (this.o == null) {
            Class e = e();
            try {
                this.o = e.getDeclaredMethod(c(), i());
            } catch (NoSuchMethodException unused) {
                HashSet hashSet = new HashSet();
                hashSet.add(e);
                this.o = a(e, c(), i(), hashSet);
            }
        }
        return this.o;
    }

    private Method a(Class cls, String str, Class[] clsArr, Set set) {
        if (cls == null) {
            return null;
        }
        if (!set.contains(cls)) {
            set.add(cls);
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
            }
        }
        Method a2 = a(cls.getSuperclass(), str, clsArr, set);
        if (a2 != null) {
            return a2;
        }
        Class[] interfaces = cls.getInterfaces();
        if (interfaces != null) {
            for (Class a3 : interfaces) {
                Method a4 = a(a3, str, clsArr, set);
                if (a4 != null) {
                    return a4;
                }
            }
        }
        return null;
    }
}
