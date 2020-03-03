package com.xiaomi.youpin.common.util;

import java.lang.reflect.Method;

public class Reflector {

    public static final class TypedObject {

        /* renamed from: a  reason: collision with root package name */
        private final Object f23266a;
        private final Class b;

        public TypedObject(Object obj, Class cls) {
            this.f23266a = obj;
            this.b = cls;
        }

        /* access modifiers changed from: package-private */
        public Object a() {
            return this.f23266a;
        }

        /* access modifiers changed from: package-private */
        public Class b() {
            return this.b;
        }
    }

    public static void a(Object obj, String str, TypedObject... typedObjectArr) {
        Class[] clsArr;
        if (obj != null) {
            if (typedObjectArr == null) {
                try {
                    clsArr = new Class[0];
                } catch (Throwable unused) {
                    return;
                }
            } else {
                clsArr = new Class[typedObjectArr.length];
            }
            Object[] objArr = typedObjectArr == null ? new Object[0] : new Object[typedObjectArr.length];
            if (typedObjectArr != null) {
                int length = clsArr.length;
                for (int i = 0; i < length; i++) {
                    clsArr[i] = typedObjectArr[i].b();
                    objArr[i] = typedObjectArr[i].a();
                }
            }
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, objArr);
        }
    }
}
