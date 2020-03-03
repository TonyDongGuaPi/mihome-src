package org.apache.commons.lang.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import org.apache.commons.lang.ArrayUtils;

public class EqualsBuilder {

    /* renamed from: a  reason: collision with root package name */
    private boolean f3376a = true;

    public static boolean a(Object obj, Object obj2) {
        return a(obj, obj2, false, (Class) null, (String[]) null);
    }

    public static boolean a(Object obj, Object obj2, Collection collection) {
        return a(obj, obj2, ReflectionToStringBuilder.a(collection));
    }

    public static boolean a(Object obj, Object obj2, String[] strArr) {
        return a(obj, obj2, false, (Class) null, strArr);
    }

    public static boolean a(Object obj, Object obj2, boolean z) {
        return a(obj, obj2, z, (Class) null, (String[]) null);
    }

    public static boolean a(Object obj, Object obj2, boolean z, Class cls) {
        return a(obj, obj2, z, cls, (String[]) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        if (r1.isInstance(r12) == false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (r2.isInstance(r11) == false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.Object r11, java.lang.Object r12, boolean r13, java.lang.Class r14, java.lang.String[] r15) {
        /*
            if (r11 != r12) goto L_0x0004
            r11 = 1
            return r11
        L_0x0004:
            r0 = 0
            if (r11 == 0) goto L_0x0058
            if (r12 != 0) goto L_0x000a
            goto L_0x0058
        L_0x000a:
            java.lang.Class r1 = r11.getClass()
            java.lang.Class r2 = r12.getClass()
            boolean r3 = r1.isInstance(r12)
            if (r3 == 0) goto L_0x001f
            boolean r3 = r2.isInstance(r11)
            if (r3 != 0) goto L_0x002d
            goto L_0x002c
        L_0x001f:
            boolean r3 = r2.isInstance(r11)
            if (r3 == 0) goto L_0x0057
            boolean r3 = r1.isInstance(r12)
            if (r3 != 0) goto L_0x002c
            goto L_0x002d
        L_0x002c:
            r1 = r2
        L_0x002d:
            org.apache.commons.lang.builder.EqualsBuilder r10 = new org.apache.commons.lang.builder.EqualsBuilder
            r10.<init>()
            r4 = r11
            r5 = r12
            r6 = r1
            r7 = r10
            r8 = r13
            r9 = r15
            a(r4, r5, r6, r7, r8, r9)     // Catch:{ IllegalArgumentException -> 0x0056 }
        L_0x003b:
            java.lang.Class r2 = r1.getSuperclass()     // Catch:{ IllegalArgumentException -> 0x0056 }
            if (r2 == 0) goto L_0x0051
            if (r1 == r14) goto L_0x0051
            java.lang.Class r1 = r1.getSuperclass()     // Catch:{ IllegalArgumentException -> 0x0056 }
            r2 = r11
            r3 = r12
            r4 = r1
            r5 = r10
            r6 = r13
            r7 = r15
            a(r2, r3, r4, r5, r6, r7)     // Catch:{ IllegalArgumentException -> 0x0056 }
            goto L_0x003b
        L_0x0051:
            boolean r11 = r10.a()
            return r11
        L_0x0056:
            return r0
        L_0x0057:
            return r0
        L_0x0058:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.builder.EqualsBuilder.a(java.lang.Object, java.lang.Object, boolean, java.lang.Class, java.lang.String[]):boolean");
    }

    private static void a(Object obj, Object obj2, Class cls, EqualsBuilder equalsBuilder, boolean z, String[] strArr) {
        Field[] declaredFields = cls.getDeclaredFields();
        AccessibleObject.setAccessible(declaredFields, true);
        for (int i = 0; i < declaredFields.length && equalsBuilder.f3376a; i++) {
            Field field = declaredFields[i];
            if (!ArrayUtils.c((Object[]) strArr, (Object) field.getName()) && field.getName().indexOf(36) == -1 && ((z || !Modifier.isTransient(field.getModifiers())) && !Modifier.isStatic(field.getModifiers()))) {
                try {
                    equalsBuilder.b(field.get(obj), field.get(obj2));
                } catch (IllegalAccessException unused) {
                    throw new InternalError("Unexpected IllegalAccessException");
                }
            }
        }
    }

    public EqualsBuilder a(boolean z) {
        if (!this.f3376a) {
            return this;
        }
        this.f3376a = z;
        return this;
    }

    public EqualsBuilder b(Object obj, Object obj2) {
        if (!this.f3376a || obj == obj2) {
            return this;
        }
        if (obj == null || obj2 == null) {
            b(false);
            return this;
        }
        if (!obj.getClass().isArray()) {
            this.f3376a = obj.equals(obj2);
        } else if (obj.getClass() != obj2.getClass()) {
            b(false);
        } else if (obj instanceof long[]) {
            a((long[]) obj, (long[]) obj2);
        } else if (obj instanceof int[]) {
            a((int[]) obj, (int[]) obj2);
        } else if (obj instanceof short[]) {
            a((short[]) obj, (short[]) obj2);
        } else if (obj instanceof char[]) {
            a((char[]) obj, (char[]) obj2);
        } else if (obj instanceof byte[]) {
            a((byte[]) obj, (byte[]) obj2);
        } else if (obj instanceof double[]) {
            a((double[]) obj, (double[]) obj2);
        } else if (obj instanceof float[]) {
            a((float[]) obj, (float[]) obj2);
        } else if (obj instanceof boolean[]) {
            a((boolean[]) obj, (boolean[]) obj2);
        } else {
            a((Object[]) obj, (Object[]) obj2);
        }
        return this;
    }

    public EqualsBuilder a(long j, long j2) {
        if (!this.f3376a) {
            return this;
        }
        this.f3376a = j == j2;
        return this;
    }

    public EqualsBuilder a(int i, int i2) {
        if (!this.f3376a) {
            return this;
        }
        this.f3376a = i == i2;
        return this;
    }

    public EqualsBuilder a(short s, short s2) {
        if (!this.f3376a) {
            return this;
        }
        this.f3376a = s == s2;
        return this;
    }

    public EqualsBuilder a(char c, char c2) {
        if (!this.f3376a) {
            return this;
        }
        this.f3376a = c == c2;
        return this;
    }

    public EqualsBuilder a(byte b, byte b2) {
        if (!this.f3376a) {
            return this;
        }
        this.f3376a = b == b2;
        return this;
    }

    public EqualsBuilder a(double d, double d2) {
        if (!this.f3376a) {
            return this;
        }
        return a(Double.doubleToLongBits(d), Double.doubleToLongBits(d2));
    }

    public EqualsBuilder a(float f, float f2) {
        if (!this.f3376a) {
            return this;
        }
        return a(Float.floatToIntBits(f), Float.floatToIntBits(f2));
    }

    public EqualsBuilder a(boolean z, boolean z2) {
        if (!this.f3376a) {
            return this;
        }
        this.f3376a = z == z2;
        return this;
    }

    public EqualsBuilder a(Object[] objArr, Object[] objArr2) {
        if (!this.f3376a || objArr == objArr2) {
            return this;
        }
        if (objArr == null || objArr2 == null) {
            b(false);
            return this;
        } else if (objArr.length != objArr2.length) {
            b(false);
            return this;
        } else {
            for (int i = 0; i < objArr.length && this.f3376a; i++) {
                b(objArr[i], objArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder a(long[] jArr, long[] jArr2) {
        if (!this.f3376a || jArr == jArr2) {
            return this;
        }
        if (jArr == null || jArr2 == null) {
            b(false);
            return this;
        } else if (jArr.length != jArr2.length) {
            b(false);
            return this;
        } else {
            for (int i = 0; i < jArr.length && this.f3376a; i++) {
                a(jArr[i], jArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder a(int[] iArr, int[] iArr2) {
        if (!this.f3376a || iArr == iArr2) {
            return this;
        }
        if (iArr == null || iArr2 == null) {
            b(false);
            return this;
        } else if (iArr.length != iArr2.length) {
            b(false);
            return this;
        } else {
            for (int i = 0; i < iArr.length && this.f3376a; i++) {
                a(iArr[i], iArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder a(short[] sArr, short[] sArr2) {
        if (!this.f3376a || sArr == sArr2) {
            return this;
        }
        if (sArr == null || sArr2 == null) {
            b(false);
            return this;
        } else if (sArr.length != sArr2.length) {
            b(false);
            return this;
        } else {
            for (int i = 0; i < sArr.length && this.f3376a; i++) {
                a(sArr[i], sArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder a(char[] cArr, char[] cArr2) {
        if (!this.f3376a || cArr == cArr2) {
            return this;
        }
        if (cArr == null || cArr2 == null) {
            b(false);
            return this;
        } else if (cArr.length != cArr2.length) {
            b(false);
            return this;
        } else {
            for (int i = 0; i < cArr.length && this.f3376a; i++) {
                a(cArr[i], cArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder a(byte[] bArr, byte[] bArr2) {
        if (!this.f3376a || bArr == bArr2) {
            return this;
        }
        if (bArr == null || bArr2 == null) {
            b(false);
            return this;
        } else if (bArr.length != bArr2.length) {
            b(false);
            return this;
        } else {
            for (int i = 0; i < bArr.length && this.f3376a; i++) {
                a(bArr[i], bArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder a(double[] dArr, double[] dArr2) {
        if (!this.f3376a || dArr == dArr2) {
            return this;
        }
        if (dArr == null || dArr2 == null) {
            b(false);
            return this;
        } else if (dArr.length != dArr2.length) {
            b(false);
            return this;
        } else {
            for (int i = 0; i < dArr.length && this.f3376a; i++) {
                a(dArr[i], dArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder a(float[] fArr, float[] fArr2) {
        if (!this.f3376a || fArr == fArr2) {
            return this;
        }
        if (fArr == null || fArr2 == null) {
            b(false);
            return this;
        } else if (fArr.length != fArr2.length) {
            b(false);
            return this;
        } else {
            for (int i = 0; i < fArr.length && this.f3376a; i++) {
                a(fArr[i], fArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder a(boolean[] zArr, boolean[] zArr2) {
        if (!this.f3376a || zArr == zArr2) {
            return this;
        }
        if (zArr == null || zArr2 == null) {
            b(false);
            return this;
        } else if (zArr.length != zArr2.length) {
            b(false);
            return this;
        } else {
            for (int i = 0; i < zArr.length && this.f3376a; i++) {
                a(zArr[i], zArr2[i]);
            }
            return this;
        }
    }

    public boolean a() {
        return this.f3376a;
    }

    /* access modifiers changed from: protected */
    public void b(boolean z) {
        this.f3376a = z;
    }

    public void b() {
        this.f3376a = true;
    }
}
