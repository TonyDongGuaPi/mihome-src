package org.apache.commons.lang.builder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class HashCodeBuilder {

    /* renamed from: a  reason: collision with root package name */
    static Class f3377a;
    private static final ThreadLocal b = new ThreadLocal();
    private final int c;
    private int d;

    static Set a() {
        return (Set) b.get();
    }

    static boolean a(Object obj) {
        Set a2 = a();
        return a2 != null && a2.contains(new IDKey(obj));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:16|17|18|19|20) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x004d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.lang.Object r4, java.lang.Class r5, org.apache.commons.lang.builder.HashCodeBuilder r6, boolean r7, java.lang.String[] r8) {
        /*
            boolean r0 = a((java.lang.Object) r4)
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            c(r4)     // Catch:{ all -> 0x005c }
            java.lang.reflect.Field[] r5 = r5.getDeclaredFields()     // Catch:{ all -> 0x005c }
            r0 = 1
            java.lang.reflect.AccessibleObject.setAccessible(r5, r0)     // Catch:{ all -> 0x005c }
            r0 = 0
        L_0x0013:
            int r1 = r5.length     // Catch:{ all -> 0x005c }
            if (r0 >= r1) goto L_0x0058
            r1 = r5[r0]     // Catch:{ all -> 0x005c }
            java.lang.String r2 = r1.getName()     // Catch:{ all -> 0x005c }
            boolean r2 = org.apache.commons.lang.ArrayUtils.c((java.lang.Object[]) r8, (java.lang.Object) r2)     // Catch:{ all -> 0x005c }
            if (r2 != 0) goto L_0x0055
            java.lang.String r2 = r1.getName()     // Catch:{ all -> 0x005c }
            r3 = 36
            int r2 = r2.indexOf(r3)     // Catch:{ all -> 0x005c }
            r3 = -1
            if (r2 != r3) goto L_0x0055
            if (r7 != 0) goto L_0x003b
            int r2 = r1.getModifiers()     // Catch:{ all -> 0x005c }
            boolean r2 = java.lang.reflect.Modifier.isTransient(r2)     // Catch:{ all -> 0x005c }
            if (r2 != 0) goto L_0x0055
        L_0x003b:
            int r2 = r1.getModifiers()     // Catch:{ all -> 0x005c }
            boolean r2 = java.lang.reflect.Modifier.isStatic(r2)     // Catch:{ all -> 0x005c }
            if (r2 != 0) goto L_0x0055
            java.lang.Object r1 = r1.get(r4)     // Catch:{ IllegalAccessException -> 0x004d }
            r6.e(r1)     // Catch:{ IllegalAccessException -> 0x004d }
            goto L_0x0055
        L_0x004d:
            java.lang.InternalError r5 = new java.lang.InternalError     // Catch:{ all -> 0x005c }
            java.lang.String r6 = "Unexpected IllegalAccessException"
            r5.<init>(r6)     // Catch:{ all -> 0x005c }
            throw r5     // Catch:{ all -> 0x005c }
        L_0x0055:
            int r0 = r0 + 1
            goto L_0x0013
        L_0x0058:
            d(r4)
            return
        L_0x005c:
            r5 = move-exception
            d(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.builder.HashCodeBuilder.a(java.lang.Object, java.lang.Class, org.apache.commons.lang.builder.HashCodeBuilder, boolean, java.lang.String[]):void");
    }

    public static int a(int i, int i2, Object obj) {
        return a(i, i2, obj, false, (Class) null, (String[]) null);
    }

    public static int a(int i, int i2, Object obj, boolean z) {
        return a(i, i2, obj, z, (Class) null, (String[]) null);
    }

    public static int a(int i, int i2, Object obj, boolean z, Class cls) {
        return a(i, i2, obj, z, cls, (String[]) null);
    }

    public static int a(int i, int i2, Object obj, boolean z, Class cls, String[] strArr) {
        if (obj != null) {
            HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(i, i2);
            Class cls2 = obj.getClass();
            a(obj, cls2, hashCodeBuilder, z, strArr);
            while (cls2.getSuperclass() != null && cls2 != cls) {
                cls2 = cls2.getSuperclass();
                a(obj, cls2, hashCodeBuilder, z, strArr);
            }
            return hashCodeBuilder.b();
        }
        throw new IllegalArgumentException("The object to build a hash code for must not be null");
    }

    public static int b(Object obj) {
        return a(17, 37, obj, false, (Class) null, (String[]) null);
    }

    public static int a(Object obj, boolean z) {
        return a(17, 37, obj, z, (Class) null, (String[]) null);
    }

    public static int a(Object obj, Collection collection) {
        return a(obj, ReflectionToStringBuilder.a(collection));
    }

    public static int a(Object obj, String[] strArr) {
        return a(17, 37, obj, false, (Class) null, strArr);
    }

    static Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static void c(Object obj) {
        Class cls;
        if (f3377a == null) {
            cls = a("org.apache.commons.lang.builder.HashCodeBuilder");
            f3377a = cls;
        } else {
            cls = f3377a;
        }
        synchronized (cls) {
            if (a() == null) {
                b.set(new HashSet());
            }
        }
        a().add(new IDKey(obj));
    }

    static void d(Object obj) {
        Class cls;
        Set a2 = a();
        if (a2 != null) {
            a2.remove(new IDKey(obj));
            if (f3377a == null) {
                cls = a("org.apache.commons.lang.builder.HashCodeBuilder");
                f3377a = cls;
            } else {
                cls = f3377a;
            }
            synchronized (cls) {
                Set a3 = a();
                if (a3 != null && a3.isEmpty()) {
                    b.set((Object) null);
                }
            }
        }
    }

    public HashCodeBuilder() {
        this.d = 0;
        this.c = 37;
        this.d = 17;
    }

    public HashCodeBuilder(int i, int i2) {
        this.d = 0;
        if (i == 0) {
            throw new IllegalArgumentException("HashCodeBuilder requires a non zero initial value");
        } else if (i % 2 == 0) {
            throw new IllegalArgumentException("HashCodeBuilder requires an odd initial value");
        } else if (i2 == 0) {
            throw new IllegalArgumentException("HashCodeBuilder requires a non zero multiplier");
        } else if (i2 % 2 != 0) {
            this.c = i2;
            this.d = i;
        } else {
            throw new IllegalArgumentException("HashCodeBuilder requires an odd multiplier");
        }
    }

    public HashCodeBuilder a(boolean z) {
        this.d = (this.d * this.c) + (z ^ true ? 1 : 0);
        return this;
    }

    public HashCodeBuilder a(boolean[] zArr) {
        if (zArr == null) {
            this.d *= this.c;
        } else {
            for (boolean a2 : zArr) {
                a(a2);
            }
        }
        return this;
    }

    public HashCodeBuilder a(byte b2) {
        this.d = (this.d * this.c) + b2;
        return this;
    }

    public HashCodeBuilder a(byte[] bArr) {
        if (bArr == null) {
            this.d *= this.c;
        } else {
            for (byte a2 : bArr) {
                a(a2);
            }
        }
        return this;
    }

    public HashCodeBuilder a(char c2) {
        this.d = (this.d * this.c) + c2;
        return this;
    }

    public HashCodeBuilder a(char[] cArr) {
        if (cArr == null) {
            this.d *= this.c;
        } else {
            for (char a2 : cArr) {
                a(a2);
            }
        }
        return this;
    }

    public HashCodeBuilder a(double d2) {
        return a(Double.doubleToLongBits(d2));
    }

    public HashCodeBuilder a(double[] dArr) {
        if (dArr == null) {
            this.d *= this.c;
        } else {
            for (double a2 : dArr) {
                a(a2);
            }
        }
        return this;
    }

    public HashCodeBuilder a(float f) {
        this.d = (this.d * this.c) + Float.floatToIntBits(f);
        return this;
    }

    public HashCodeBuilder a(float[] fArr) {
        if (fArr == null) {
            this.d *= this.c;
        } else {
            for (float a2 : fArr) {
                a(a2);
            }
        }
        return this;
    }

    public HashCodeBuilder a(int i) {
        this.d = (this.d * this.c) + i;
        return this;
    }

    public HashCodeBuilder a(int[] iArr) {
        if (iArr == null) {
            this.d *= this.c;
        } else {
            for (int a2 : iArr) {
                a(a2);
            }
        }
        return this;
    }

    public HashCodeBuilder a(long j) {
        this.d = (this.d * this.c) + ((int) (j ^ (j >> 32)));
        return this;
    }

    public HashCodeBuilder a(long[] jArr) {
        if (jArr == null) {
            this.d *= this.c;
        } else {
            for (long a2 : jArr) {
                a(a2);
            }
        }
        return this;
    }

    public HashCodeBuilder e(Object obj) {
        if (obj == null) {
            this.d *= this.c;
        } else if (!obj.getClass().isArray()) {
            this.d = (this.d * this.c) + obj.hashCode();
        } else if (obj instanceof long[]) {
            a((long[]) obj);
        } else if (obj instanceof int[]) {
            a((int[]) obj);
        } else if (obj instanceof short[]) {
            a((short[]) obj);
        } else if (obj instanceof char[]) {
            a((char[]) obj);
        } else if (obj instanceof byte[]) {
            a((byte[]) obj);
        } else if (obj instanceof double[]) {
            a((double[]) obj);
        } else if (obj instanceof float[]) {
            a((float[]) obj);
        } else if (obj instanceof boolean[]) {
            a((boolean[]) obj);
        } else {
            a((Object[]) obj);
        }
        return this;
    }

    public HashCodeBuilder a(Object[] objArr) {
        if (objArr == null) {
            this.d *= this.c;
        } else {
            for (Object e : objArr) {
                e(e);
            }
        }
        return this;
    }

    public HashCodeBuilder a(short s) {
        this.d = (this.d * this.c) + s;
        return this;
    }

    public HashCodeBuilder a(short[] sArr) {
        if (sArr == null) {
            this.d *= this.c;
        } else {
            for (short a2 : sArr) {
                a(a2);
            }
        }
        return this;
    }

    public HashCodeBuilder b(int i) {
        this.d = (this.d * this.c) + i;
        return this;
    }

    public int b() {
        return this.d;
    }

    public int hashCode() {
        return b();
    }
}
