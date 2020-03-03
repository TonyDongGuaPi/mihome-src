package org.apache.commons.lang.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Comparator;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;

public class CompareToBuilder {

    /* renamed from: a  reason: collision with root package name */
    private int f3375a = 0;

    public static int a(Object obj, Object obj2) {
        return a(obj, obj2, false, (Class) null, (String[]) null);
    }

    public static int a(Object obj, Object obj2, boolean z) {
        return a(obj, obj2, z, (Class) null, (String[]) null);
    }

    public static int a(Object obj, Object obj2, Collection collection) {
        return a(obj, obj2, ReflectionToStringBuilder.a(collection));
    }

    public static int a(Object obj, Object obj2, String[] strArr) {
        return a(obj, obj2, false, (Class) null, strArr);
    }

    public static int a(Object obj, Object obj2, boolean z, Class cls) {
        return a(obj, obj2, z, cls, (String[]) null);
    }

    public static int a(Object obj, Object obj2, boolean z, Class cls, String[] strArr) {
        if (obj == obj2) {
            return 0;
        }
        if (obj == null || obj2 == null) {
            throw new NullPointerException();
        }
        Class cls2 = obj.getClass();
        if (cls2.isInstance(obj2)) {
            CompareToBuilder compareToBuilder = new CompareToBuilder();
            a(obj, obj2, cls2, compareToBuilder, z, strArr);
            while (cls2.getSuperclass() != null && cls2 != cls) {
                cls2 = cls2.getSuperclass();
                a(obj, obj2, cls2, compareToBuilder, z, strArr);
            }
            return compareToBuilder.a();
        }
        throw new ClassCastException();
    }

    private static void a(Object obj, Object obj2, Class cls, CompareToBuilder compareToBuilder, boolean z, String[] strArr) {
        Field[] declaredFields = cls.getDeclaredFields();
        AccessibleObject.setAccessible(declaredFields, true);
        for (int i = 0; i < declaredFields.length && compareToBuilder.f3375a == 0; i++) {
            Field field = declaredFields[i];
            if (!ArrayUtils.c((Object[]) strArr, (Object) field.getName()) && field.getName().indexOf(36) == -1 && ((z || !Modifier.isTransient(field.getModifiers())) && !Modifier.isStatic(field.getModifiers()))) {
                try {
                    compareToBuilder.b(field.get(obj), field.get(obj2));
                } catch (IllegalAccessException unused) {
                    throw new InternalError("Unexpected IllegalAccessException");
                }
            }
        }
    }

    public CompareToBuilder a(int i) {
        if (this.f3375a != 0) {
            return this;
        }
        this.f3375a = i;
        return this;
    }

    public CompareToBuilder b(Object obj, Object obj2) {
        return a(obj, obj2, (Comparator) null);
    }

    public CompareToBuilder a(Object obj, Object obj2, Comparator comparator) {
        if (this.f3375a != 0 || obj == obj2) {
            return this;
        }
        if (obj == null) {
            this.f3375a = -1;
            return this;
        } else if (obj2 == null) {
            this.f3375a = 1;
            return this;
        } else {
            if (obj.getClass().isArray()) {
                if (obj instanceof long[]) {
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
                    a((Object[]) obj, (Object[]) obj2, comparator);
                }
            } else if (comparator == null) {
                this.f3375a = ((Comparable) obj).compareTo(obj2);
            } else {
                this.f3375a = comparator.compare(obj, obj2);
            }
            return this;
        }
    }

    public CompareToBuilder a(long j, long j2) {
        if (this.f3375a != 0) {
            return this;
        }
        this.f3375a = j < j2 ? -1 : j > j2 ? 1 : 0;
        return this;
    }

    public CompareToBuilder a(int i, int i2) {
        if (this.f3375a != 0) {
            return this;
        }
        this.f3375a = i < i2 ? -1 : i > i2 ? 1 : 0;
        return this;
    }

    public CompareToBuilder a(short s, short s2) {
        if (this.f3375a != 0) {
            return this;
        }
        this.f3375a = s < s2 ? -1 : s > s2 ? 1 : 0;
        return this;
    }

    public CompareToBuilder a(char c, char c2) {
        if (this.f3375a != 0) {
            return this;
        }
        this.f3375a = c < c2 ? -1 : c > c2 ? 1 : 0;
        return this;
    }

    public CompareToBuilder a(byte b, byte b2) {
        if (this.f3375a != 0) {
            return this;
        }
        this.f3375a = b < b2 ? -1 : b > b2 ? 1 : 0;
        return this;
    }

    public CompareToBuilder a(double d, double d2) {
        if (this.f3375a != 0) {
            return this;
        }
        this.f3375a = NumberUtils.a(d, d2);
        return this;
    }

    public CompareToBuilder a(float f, float f2) {
        if (this.f3375a != 0) {
            return this;
        }
        this.f3375a = NumberUtils.a(f, f2);
        return this;
    }

    public CompareToBuilder a(boolean z, boolean z2) {
        if (this.f3375a != 0 || z == z2) {
            return this;
        }
        if (!z) {
            this.f3375a = -1;
        } else {
            this.f3375a = 1;
        }
        return this;
    }

    public CompareToBuilder a(Object[] objArr, Object[] objArr2) {
        return a(objArr, objArr2, (Comparator) null);
    }

    public CompareToBuilder a(Object[] objArr, Object[] objArr2, Comparator comparator) {
        if (this.f3375a != 0 || objArr == objArr2) {
            return this;
        }
        int i = -1;
        if (objArr == null) {
            this.f3375a = -1;
            return this;
        } else if (objArr2 == null) {
            this.f3375a = 1;
            return this;
        } else if (objArr.length != objArr2.length) {
            if (objArr.length >= objArr2.length) {
                i = 1;
            }
            this.f3375a = i;
            return this;
        } else {
            for (int i2 = 0; i2 < objArr.length && this.f3375a == 0; i2++) {
                a(objArr[i2], objArr2[i2], comparator);
            }
            return this;
        }
    }

    public CompareToBuilder a(long[] jArr, long[] jArr2) {
        if (this.f3375a != 0 || jArr == jArr2) {
            return this;
        }
        int i = -1;
        if (jArr == null) {
            this.f3375a = -1;
            return this;
        } else if (jArr2 == null) {
            this.f3375a = 1;
            return this;
        } else if (jArr.length != jArr2.length) {
            if (jArr.length >= jArr2.length) {
                i = 1;
            }
            this.f3375a = i;
            return this;
        } else {
            for (int i2 = 0; i2 < jArr.length && this.f3375a == 0; i2++) {
                a(jArr[i2], jArr2[i2]);
            }
            return this;
        }
    }

    public CompareToBuilder a(int[] iArr, int[] iArr2) {
        if (this.f3375a != 0 || iArr == iArr2) {
            return this;
        }
        int i = -1;
        if (iArr == null) {
            this.f3375a = -1;
            return this;
        } else if (iArr2 == null) {
            this.f3375a = 1;
            return this;
        } else if (iArr.length != iArr2.length) {
            if (iArr.length >= iArr2.length) {
                i = 1;
            }
            this.f3375a = i;
            return this;
        } else {
            for (int i2 = 0; i2 < iArr.length && this.f3375a == 0; i2++) {
                a(iArr[i2], iArr2[i2]);
            }
            return this;
        }
    }

    public CompareToBuilder a(short[] sArr, short[] sArr2) {
        if (this.f3375a != 0 || sArr == sArr2) {
            return this;
        }
        int i = -1;
        if (sArr == null) {
            this.f3375a = -1;
            return this;
        } else if (sArr2 == null) {
            this.f3375a = 1;
            return this;
        } else if (sArr.length != sArr2.length) {
            if (sArr.length >= sArr2.length) {
                i = 1;
            }
            this.f3375a = i;
            return this;
        } else {
            for (int i2 = 0; i2 < sArr.length && this.f3375a == 0; i2++) {
                a(sArr[i2], sArr2[i2]);
            }
            return this;
        }
    }

    public CompareToBuilder a(char[] cArr, char[] cArr2) {
        if (this.f3375a != 0 || cArr == cArr2) {
            return this;
        }
        int i = -1;
        if (cArr == null) {
            this.f3375a = -1;
            return this;
        } else if (cArr2 == null) {
            this.f3375a = 1;
            return this;
        } else if (cArr.length != cArr2.length) {
            if (cArr.length >= cArr2.length) {
                i = 1;
            }
            this.f3375a = i;
            return this;
        } else {
            for (int i2 = 0; i2 < cArr.length && this.f3375a == 0; i2++) {
                a(cArr[i2], cArr2[i2]);
            }
            return this;
        }
    }

    public CompareToBuilder a(byte[] bArr, byte[] bArr2) {
        if (this.f3375a != 0 || bArr == bArr2) {
            return this;
        }
        int i = -1;
        if (bArr == null) {
            this.f3375a = -1;
            return this;
        } else if (bArr2 == null) {
            this.f3375a = 1;
            return this;
        } else if (bArr.length != bArr2.length) {
            if (bArr.length >= bArr2.length) {
                i = 1;
            }
            this.f3375a = i;
            return this;
        } else {
            for (int i2 = 0; i2 < bArr.length && this.f3375a == 0; i2++) {
                a(bArr[i2], bArr2[i2]);
            }
            return this;
        }
    }

    public CompareToBuilder a(double[] dArr, double[] dArr2) {
        if (this.f3375a != 0 || dArr == dArr2) {
            return this;
        }
        int i = -1;
        if (dArr == null) {
            this.f3375a = -1;
            return this;
        } else if (dArr2 == null) {
            this.f3375a = 1;
            return this;
        } else if (dArr.length != dArr2.length) {
            if (dArr.length >= dArr2.length) {
                i = 1;
            }
            this.f3375a = i;
            return this;
        } else {
            for (int i2 = 0; i2 < dArr.length && this.f3375a == 0; i2++) {
                a(dArr[i2], dArr2[i2]);
            }
            return this;
        }
    }

    public CompareToBuilder a(float[] fArr, float[] fArr2) {
        if (this.f3375a != 0 || fArr == fArr2) {
            return this;
        }
        int i = -1;
        if (fArr == null) {
            this.f3375a = -1;
            return this;
        } else if (fArr2 == null) {
            this.f3375a = 1;
            return this;
        } else if (fArr.length != fArr2.length) {
            if (fArr.length >= fArr2.length) {
                i = 1;
            }
            this.f3375a = i;
            return this;
        } else {
            for (int i2 = 0; i2 < fArr.length && this.f3375a == 0; i2++) {
                a(fArr[i2], fArr2[i2]);
            }
            return this;
        }
    }

    public CompareToBuilder a(boolean[] zArr, boolean[] zArr2) {
        if (this.f3375a != 0 || zArr == zArr2) {
            return this;
        }
        int i = -1;
        if (zArr == null) {
            this.f3375a = -1;
            return this;
        } else if (zArr2 == null) {
            this.f3375a = 1;
            return this;
        } else if (zArr.length != zArr2.length) {
            if (zArr.length >= zArr2.length) {
                i = 1;
            }
            this.f3375a = i;
            return this;
        } else {
            for (int i2 = 0; i2 < zArr.length && this.f3375a == 0; i2++) {
                a(zArr[i2], zArr2[i2]);
            }
            return this;
        }
    }

    public int a() {
        return this.f3375a;
    }
}
