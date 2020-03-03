package com.xiaomi.youpin.common.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class ReflectUtils {

    /* renamed from: a  reason: collision with root package name */
    private final Class<?> f23262a;
    /* access modifiers changed from: private */
    public final Object b;

    private ReflectUtils(Class<?> cls) {
        this(cls, cls);
    }

    private ReflectUtils(Class<?> cls, Object obj) {
        this.f23262a = cls;
        this.b = obj;
    }

    public static ReflectUtils a(String str) throws ReflectException {
        return a(e(str));
    }

    public static ReflectUtils a(String str, ClassLoader classLoader) throws ReflectException {
        return a(b(str, classLoader));
    }

    public static ReflectUtils a(Class<?> cls) throws ReflectException {
        return new ReflectUtils(cls);
    }

    public static ReflectUtils a(Object obj) throws ReflectException {
        return new ReflectUtils(obj == null ? Object.class : obj.getClass(), obj);
    }

    private static Class<?> e(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new ReflectException((Throwable) e);
        }
    }

    private static Class<?> b(String str, ClassLoader classLoader) {
        try {
            return Class.forName(str, true, classLoader);
        } catch (ClassNotFoundException e) {
            throw new ReflectException((Throwable) e);
        }
    }

    public ReflectUtils a() {
        return a(new Object[0]);
    }

    public ReflectUtils a(Object... objArr) {
        Class[] b2 = b(objArr);
        try {
            return a(c().getDeclaredConstructor(b2), objArr);
        } catch (NoSuchMethodException e) {
            ArrayList arrayList = new ArrayList();
            for (Constructor constructor : c().getDeclaredConstructors()) {
                if (a((Class<?>[]) constructor.getParameterTypes(), (Class<?>[]) b2)) {
                    arrayList.add(constructor);
                }
            }
            if (!arrayList.isEmpty()) {
                a((List<Constructor<?>>) arrayList);
                return a((Constructor<?>) (Constructor) arrayList.get(0), objArr);
            }
            throw new ReflectException((Throwable) e);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Class<?>[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Class<?>[] b(java.lang.Object... r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L_0x0006
            java.lang.Class[] r4 = new java.lang.Class[r0]
            return r4
        L_0x0006:
            int r1 = r4.length
            java.lang.Class[] r1 = new java.lang.Class[r1]
        L_0x0009:
            int r2 = r4.length
            if (r0 >= r2) goto L_0x001c
            r2 = r4[r0]
            if (r2 != 0) goto L_0x0013
            java.lang.Class<com.xiaomi.youpin.common.util.ReflectUtils$NULL> r2 = com.xiaomi.youpin.common.util.ReflectUtils.NULL.class
            goto L_0x0017
        L_0x0013:
            java.lang.Class r2 = r2.getClass()
        L_0x0017:
            r1[r0] = r2
            int r0 = r0 + 1
            goto L_0x0009
        L_0x001c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ReflectUtils.b(java.lang.Object[]):java.lang.Class[]");
    }

    private void a(List<Constructor<?>> list) {
        Collections.sort(list, new Comparator<Constructor<?>>() {
            /* renamed from: a */
            public int compare(Constructor<?> constructor, Constructor<?> constructor2) {
                Class[] parameterTypes = constructor.getParameterTypes();
                Class[] parameterTypes2 = constructor2.getParameterTypes();
                int length = parameterTypes.length;
                for (int i = 0; i < length; i++) {
                    if (!parameterTypes[i].equals(parameterTypes2[i])) {
                        return ReflectUtils.this.c((Class<?>) parameterTypes[i]).isAssignableFrom(ReflectUtils.this.c((Class<?>) parameterTypes2[i])) ? 1 : -1;
                    }
                }
                return 0;
            }
        });
    }

    private ReflectUtils a(Constructor<?> constructor, Object... objArr) {
        try {
            return new ReflectUtils(constructor.getDeclaringClass(), ((Constructor) a(constructor)).newInstance(objArr));
        } catch (Exception e) {
            throw new ReflectException((Throwable) e);
        }
    }

    public ReflectUtils b(String str) {
        try {
            Field f = f(str);
            return new ReflectUtils(f.getType(), f.get(this.b));
        } catch (IllegalAccessException e) {
            throw new ReflectException((Throwable) e);
        }
    }

    public ReflectUtils a(String str, Object obj) {
        try {
            f(str).set(this.b, b(obj));
            return this;
        } catch (Exception e) {
            throw new ReflectException((Throwable) e);
        }
    }

    private Field f(String str) throws IllegalAccessException {
        Field g = g(str);
        if ((g.getModifiers() & 16) == 16) {
            try {
                Field declaredField = Field.class.getDeclaredField("modifiers");
                declaredField.setAccessible(true);
                declaredField.setInt(g, g.getModifiers() & -17);
            } catch (NoSuchFieldException unused) {
            }
        }
        return g;
    }

    private Field g(String str) {
        Class c = c();
        try {
            return (Field) a(c.getField(str));
        } catch (NoSuchFieldException e) {
            do {
                try {
                    return (Field) a(c.getDeclaredField(str));
                } catch (NoSuchFieldException unused) {
                    c = c.getSuperclass();
                    if (c != null) {
                        throw new ReflectException((Throwable) e);
                    }
                }
            } while (c != null);
            throw new ReflectException((Throwable) e);
        }
    }

    private Object b(Object obj) {
        return obj instanceof ReflectUtils ? ((ReflectUtils) obj).b() : obj;
    }

    public ReflectUtils c(String str) throws ReflectException {
        return a(str, new Object[0]);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0019, code lost:
        return a(b(r4, (java.lang.Class<?>[]) r0), r3.b, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        throw new com.xiaomi.youpin.common.util.ReflectUtils.ReflectException((java.lang.Throwable) r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.youpin.common.util.ReflectUtils a(java.lang.String r4, java.lang.Object... r5) throws com.xiaomi.youpin.common.util.ReflectUtils.ReflectException {
        /*
            r3 = this;
            java.lang.Class[] r0 = r3.b((java.lang.Object[]) r5)
            java.lang.reflect.Method r1 = r3.a((java.lang.String) r4, (java.lang.Class<?>[]) r0)     // Catch:{ NoSuchMethodException -> 0x000f }
            java.lang.Object r2 = r3.b     // Catch:{ NoSuchMethodException -> 0x000f }
            com.xiaomi.youpin.common.util.ReflectUtils r1 = r3.a((java.lang.reflect.Method) r1, (java.lang.Object) r2, (java.lang.Object[]) r5)     // Catch:{ NoSuchMethodException -> 0x000f }
            return r1
        L_0x000f:
            java.lang.reflect.Method r4 = r3.b((java.lang.String) r4, (java.lang.Class<?>[]) r0)     // Catch:{ NoSuchMethodException -> 0x001a }
            java.lang.Object r0 = r3.b     // Catch:{ NoSuchMethodException -> 0x001a }
            com.xiaomi.youpin.common.util.ReflectUtils r4 = r3.a((java.lang.reflect.Method) r4, (java.lang.Object) r0, (java.lang.Object[]) r5)     // Catch:{ NoSuchMethodException -> 0x001a }
            return r4
        L_0x001a:
            r4 = move-exception
            com.xiaomi.youpin.common.util.ReflectUtils$ReflectException r5 = new com.xiaomi.youpin.common.util.ReflectUtils$ReflectException
            r5.<init>((java.lang.Throwable) r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ReflectUtils.a(java.lang.String, java.lang.Object[]):com.xiaomi.youpin.common.util.ReflectUtils");
    }

    private ReflectUtils a(Method method, Object obj, Object... objArr) {
        try {
            a(method);
            if (method.getReturnType() != Void.TYPE) {
                return a(method.invoke(obj, objArr));
            }
            method.invoke(obj, objArr);
            return a(obj);
        } catch (Exception e) {
            throw new ReflectException((Throwable) e);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        throw new java.lang.NoSuchMethodException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        return r0.getDeclaredMethod(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
        r0 = r0.getSuperclass();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r0 != null) goto L_0x0009;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.reflect.Method a(java.lang.String r3, java.lang.Class<?>[] r4) throws java.lang.NoSuchMethodException {
        /*
            r2 = this;
            java.lang.Class r0 = r2.c()
            java.lang.reflect.Method r1 = r0.getMethod(r3, r4)     // Catch:{ NoSuchMethodException -> 0x0009 }
            return r1
        L_0x0009:
            java.lang.reflect.Method r1 = r0.getDeclaredMethod(r3, r4)     // Catch:{ NoSuchMethodException -> 0x000e }
            return r1
        L_0x000e:
            java.lang.Class r0 = r0.getSuperclass()
            if (r0 == 0) goto L_0x0015
            goto L_0x0009
        L_0x0015:
            java.lang.NoSuchMethodException r3 = new java.lang.NoSuchMethodException
            r3.<init>()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ReflectUtils.a(java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    private Method b(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Class c = c();
        ArrayList arrayList = new ArrayList();
        for (Method method : c.getMethods()) {
            if (a(method, str, clsArr)) {
                arrayList.add(method);
            }
        }
        if (!arrayList.isEmpty()) {
            b((List<Method>) arrayList);
            return (Method) arrayList.get(0);
        }
        do {
            for (Method method2 : c.getDeclaredMethods()) {
                if (a(method2, str, clsArr)) {
                    arrayList.add(method2);
                }
            }
            if (!arrayList.isEmpty()) {
                b((List<Method>) arrayList);
                return (Method) arrayList.get(0);
            }
            c = c.getSuperclass();
        } while (c != null);
        throw new NoSuchMethodException("No similar method " + str + " with params " + Arrays.toString(clsArr) + " could be found on type " + c() + ".");
    }

    private void b(List<Method> list) {
        Collections.sort(list, new Comparator<Method>() {
            /* renamed from: a */
            public int compare(Method method, Method method2) {
                Class[] parameterTypes = method.getParameterTypes();
                Class[] parameterTypes2 = method2.getParameterTypes();
                int length = parameterTypes.length;
                for (int i = 0; i < length; i++) {
                    if (!parameterTypes[i].equals(parameterTypes2[i])) {
                        return ReflectUtils.this.c((Class<?>) parameterTypes[i]).isAssignableFrom(ReflectUtils.this.c((Class<?>) parameterTypes2[i])) ? 1 : -1;
                    }
                }
                return 0;
            }
        });
    }

    private boolean a(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && a((Class<?>[]) method.getParameterTypes(), clsArr);
    }

    private boolean a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr2.length; i++) {
            if (clsArr2[i] != NULL.class && !c(clsArr[i]).isAssignableFrom(c(clsArr2[i]))) {
                return false;
            }
        }
        return true;
    }

    private <T extends AccessibleObject> T a(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Member) {
            Member member = (Member) t;
            if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                return t;
            }
        }
        if (!t.isAccessible()) {
            t.setAccessible(true);
        }
        return t;
    }

    public <P> P b(Class<P> cls) {
        final boolean z = this.b instanceof Map;
        AnonymousClass3 r1 = new InvocationHandler() {
            public Object invoke(Object obj, Method method, Object[] objArr) {
                int i;
                String name = method.getName();
                try {
                    return ReflectUtils.a(ReflectUtils.this.b).a(name, objArr).b();
                } catch (ReflectException e) {
                    if (z) {
                        Map map = (Map) ReflectUtils.this.b;
                        if (objArr == null) {
                            i = 0;
                        } else {
                            i = objArr.length;
                        }
                        if (i == 0 && name.startsWith("get")) {
                            return map.get(ReflectUtils.h(name.substring(3)));
                        }
                        if (i == 0 && name.startsWith("is")) {
                            return map.get(ReflectUtils.h(name.substring(2)));
                        }
                        if (i == 1 && name.startsWith("set")) {
                            map.put(ReflectUtils.h(name.substring(3)), objArr[0]);
                            return null;
                        }
                    }
                    throw e;
                }
            }
        };
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, r1);
    }

    /* access modifiers changed from: private */
    public static String h(String str) {
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return str.toLowerCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private Class<?> c() {
        return this.f23262a;
    }

    /* access modifiers changed from: private */
    public Class<?> c(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        if (cls.isPrimitive()) {
            if (Boolean.TYPE == cls) {
                return Boolean.class;
            }
            if (Integer.TYPE == cls) {
                return Integer.class;
            }
            if (Long.TYPE == cls) {
                return Long.class;
            }
            if (Short.TYPE == cls) {
                return Short.class;
            }
            if (Byte.TYPE == cls) {
                return Byte.class;
            }
            if (Double.TYPE == cls) {
                return Double.class;
            }
            if (Float.TYPE == cls) {
                return Float.class;
            }
            if (Character.TYPE == cls) {
                return Character.class;
            }
            if (Void.TYPE == cls) {
                return Void.class;
            }
        }
        return cls;
    }

    public <T> T b() {
        return this.b;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ReflectUtils) && this.b.equals(((ReflectUtils) obj).b());
    }

    public String toString() {
        return this.b.toString();
    }

    private static class NULL {
        private NULL() {
        }
    }

    public static class ReflectException extends RuntimeException {
        private static final long serialVersionUID = 858774075258496016L;

        public ReflectException(String str) {
            super(str);
        }

        public ReflectException(String str, Throwable th) {
            super(str, th);
        }

        public ReflectException(Throwable th) {
            super(th);
        }
    }
}
