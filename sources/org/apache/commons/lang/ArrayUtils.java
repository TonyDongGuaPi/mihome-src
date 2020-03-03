package org.apache.commons.lang;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ArrayUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final Object[] f3354a = new Object[0];
    public static final Class[] b = new Class[0];
    public static final String[] c = new String[0];
    public static final long[] d = new long[0];
    public static final Long[] e = new Long[0];
    public static final int[] f = new int[0];
    public static final Integer[] g = new Integer[0];
    public static final short[] h = new short[0];
    public static final Short[] i = new Short[0];
    public static final byte[] j = new byte[0];
    public static final Byte[] k = new Byte[0];
    public static final double[] l = new double[0];
    public static final Double[] m = new Double[0];
    public static final float[] n = new float[0];
    public static final Float[] o = new Float[0];
    public static final boolean[] p = new boolean[0];
    public static final Boolean[] q = new Boolean[0];
    public static final char[] r = new char[0];
    public static final Character[] s = new Character[0];
    public static final int t = -1;
    static Class u;

    public static String a(Object obj) {
        return a(obj, "{}");
    }

    public static String a(Object obj, String str) {
        return obj == null ? str : new ToStringBuilder(obj, ToStringStyle.SIMPLE_STYLE).d(obj).toString();
    }

    public static int b(Object obj) {
        return new HashCodeBuilder().e(obj).b();
    }

    public static boolean a(Object obj, Object obj2) {
        return new EqualsBuilder().b(obj, obj2).a();
    }

    public static Map a(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        double length = (double) objArr.length;
        Double.isNaN(length);
        HashMap hashMap = new HashMap((int) (length * 1.5d));
        for (int i2 = 0; i2 < objArr.length; i2++) {
            Map.Entry entry = objArr[i2];
            if (entry instanceof Map.Entry) {
                Map.Entry entry2 = entry;
                hashMap.put(entry2.getKey(), entry2.getValue());
            } else if (entry instanceof Object[]) {
                Object[] objArr2 = (Object[]) entry;
                if (objArr2.length >= 2) {
                    hashMap.put(objArr2[0], objArr2[1]);
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Array element ");
                    stringBuffer.append(i2);
                    stringBuffer.append(", '");
                    stringBuffer.append(entry);
                    stringBuffer.append("', has a length less than 2");
                    throw new IllegalArgumentException(stringBuffer.toString());
                }
            } else {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Array element ");
                stringBuffer2.append(i2);
                stringBuffer2.append(", '");
                stringBuffer2.append(entry);
                stringBuffer2.append("', is neither of type Map.Entry nor an Array");
                throw new IllegalArgumentException(stringBuffer2.toString());
            }
        }
        return hashMap;
    }

    public static Object[] b(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        return (Object[]) objArr.clone();
    }

    public static long[] a(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        return (long[]) jArr.clone();
    }

    public static int[] a(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        return (int[]) iArr.clone();
    }

    public static short[] a(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        return (short[]) sArr.clone();
    }

    public static char[] a(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        return (char[]) cArr.clone();
    }

    public static byte[] a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public static double[] a(double[] dArr) {
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    public static float[] a(float[] fArr) {
        if (fArr == null) {
            return null;
        }
        return (float[]) fArr.clone();
    }

    public static boolean[] a(boolean[] zArr) {
        if (zArr == null) {
            return null;
        }
        return (boolean[]) zArr.clone();
    }

    public static Object[] c(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return f3354a;
        }
        return objArr;
    }

    public static String[] a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return c;
        }
        return strArr;
    }

    public static long[] b(long[] jArr) {
        if (jArr == null || jArr.length == 0) {
            return d;
        }
        return jArr;
    }

    public static int[] b(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return f;
        }
        return iArr;
    }

    public static short[] b(short[] sArr) {
        if (sArr == null || sArr.length == 0) {
            return h;
        }
        return sArr;
    }

    public static char[] b(char[] cArr) {
        if (cArr == null || cArr.length == 0) {
            return r;
        }
        return cArr;
    }

    public static byte[] b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return j;
        }
        return bArr;
    }

    public static double[] b(double[] dArr) {
        if (dArr == null || dArr.length == 0) {
            return l;
        }
        return dArr;
    }

    public static float[] b(float[] fArr) {
        if (fArr == null || fArr.length == 0) {
            return n;
        }
        return fArr;
    }

    public static boolean[] b(boolean[] zArr) {
        if (zArr == null || zArr.length == 0) {
            return p;
        }
        return zArr;
    }

    public static Long[] a(Long[] lArr) {
        if (lArr == null || lArr.length == 0) {
            return e;
        }
        return lArr;
    }

    public static Integer[] a(Integer[] numArr) {
        if (numArr == null || numArr.length == 0) {
            return g;
        }
        return numArr;
    }

    public static Short[] a(Short[] shArr) {
        if (shArr == null || shArr.length == 0) {
            return i;
        }
        return shArr;
    }

    public static Character[] a(Character[] chArr) {
        if (chArr == null || chArr.length == 0) {
            return s;
        }
        return chArr;
    }

    public static Byte[] a(Byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return k;
        }
        return bArr;
    }

    public static Double[] a(Double[] dArr) {
        if (dArr == null || dArr.length == 0) {
            return m;
        }
        return dArr;
    }

    public static Float[] a(Float[] fArr) {
        if (fArr == null || fArr.length == 0) {
            return o;
        }
        return fArr;
    }

    public static Boolean[] a(Boolean[] boolArr) {
        if (boolArr == null || boolArr.length == 0) {
            return q;
        }
        return boolArr;
    }

    public static Object[] a(Object[] objArr, int i2, int i3) {
        if (objArr == null) {
            return null;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 > objArr.length) {
            i3 = objArr.length;
        }
        int i4 = i3 - i2;
        Class<?> componentType = objArr.getClass().getComponentType();
        if (i4 <= 0) {
            return (Object[]) Array.newInstance(componentType, 0);
        }
        Object[] objArr2 = (Object[]) Array.newInstance(componentType, i4);
        System.arraycopy(objArr, i2, objArr2, 0, i4);
        return objArr2;
    }

    public static long[] a(long[] jArr, int i2, int i3) {
        if (jArr == null) {
            return null;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 > jArr.length) {
            i3 = jArr.length;
        }
        int i4 = i3 - i2;
        if (i4 <= 0) {
            return d;
        }
        long[] jArr2 = new long[i4];
        System.arraycopy(jArr, i2, jArr2, 0, i4);
        return jArr2;
    }

    public static int[] a(int[] iArr, int i2, int i3) {
        if (iArr == null) {
            return null;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 > iArr.length) {
            i3 = iArr.length;
        }
        int i4 = i3 - i2;
        if (i4 <= 0) {
            return f;
        }
        int[] iArr2 = new int[i4];
        System.arraycopy(iArr, i2, iArr2, 0, i4);
        return iArr2;
    }

    public static short[] a(short[] sArr, int i2, int i3) {
        if (sArr == null) {
            return null;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 > sArr.length) {
            i3 = sArr.length;
        }
        int i4 = i3 - i2;
        if (i4 <= 0) {
            return h;
        }
        short[] sArr2 = new short[i4];
        System.arraycopy(sArr, i2, sArr2, 0, i4);
        return sArr2;
    }

    public static char[] a(char[] cArr, int i2, int i3) {
        if (cArr == null) {
            return null;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 > cArr.length) {
            i3 = cArr.length;
        }
        int i4 = i3 - i2;
        if (i4 <= 0) {
            return r;
        }
        char[] cArr2 = new char[i4];
        System.arraycopy(cArr, i2, cArr2, 0, i4);
        return cArr2;
    }

    public static byte[] a(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            return null;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 > bArr.length) {
            i3 = bArr.length;
        }
        int i4 = i3 - i2;
        if (i4 <= 0) {
            return j;
        }
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i2, bArr2, 0, i4);
        return bArr2;
    }

    public static double[] a(double[] dArr, int i2, int i3) {
        if (dArr == null) {
            return null;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 > dArr.length) {
            i3 = dArr.length;
        }
        int i4 = i3 - i2;
        if (i4 <= 0) {
            return l;
        }
        double[] dArr2 = new double[i4];
        System.arraycopy(dArr, i2, dArr2, 0, i4);
        return dArr2;
    }

    public static float[] a(float[] fArr, int i2, int i3) {
        if (fArr == null) {
            return null;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 > fArr.length) {
            i3 = fArr.length;
        }
        int i4 = i3 - i2;
        if (i4 <= 0) {
            return n;
        }
        float[] fArr2 = new float[i4];
        System.arraycopy(fArr, i2, fArr2, 0, i4);
        return fArr2;
    }

    public static boolean[] a(boolean[] zArr, int i2, int i3) {
        if (zArr == null) {
            return null;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 > zArr.length) {
            i3 = zArr.length;
        }
        int i4 = i3 - i2;
        if (i4 <= 0) {
            return p;
        }
        boolean[] zArr2 = new boolean[i4];
        System.arraycopy(zArr, i2, zArr2, 0, i4);
        return zArr2;
    }

    public static boolean a(Object[] objArr, Object[] objArr2) {
        if (objArr == null && objArr2 != null && objArr2.length > 0) {
            return false;
        }
        if (objArr2 != null || objArr == null || objArr.length <= 0) {
            return objArr == null || objArr2 == null || objArr.length == objArr2.length;
        }
        return false;
    }

    public static boolean a(long[] jArr, long[] jArr2) {
        if (jArr == null && jArr2 != null && jArr2.length > 0) {
            return false;
        }
        if (jArr2 != null || jArr == null || jArr.length <= 0) {
            return jArr == null || jArr2 == null || jArr.length == jArr2.length;
        }
        return false;
    }

    public static boolean a(int[] iArr, int[] iArr2) {
        if (iArr == null && iArr2 != null && iArr2.length > 0) {
            return false;
        }
        if (iArr2 != null || iArr == null || iArr.length <= 0) {
            return iArr == null || iArr2 == null || iArr.length == iArr2.length;
        }
        return false;
    }

    public static boolean a(short[] sArr, short[] sArr2) {
        if (sArr == null && sArr2 != null && sArr2.length > 0) {
            return false;
        }
        if (sArr2 != null || sArr == null || sArr.length <= 0) {
            return sArr == null || sArr2 == null || sArr.length == sArr2.length;
        }
        return false;
    }

    public static boolean a(char[] cArr, char[] cArr2) {
        if (cArr == null && cArr2 != null && cArr2.length > 0) {
            return false;
        }
        if (cArr2 != null || cArr == null || cArr.length <= 0) {
            return cArr == null || cArr2 == null || cArr.length == cArr2.length;
        }
        return false;
    }

    public static boolean a(byte[] bArr, byte[] bArr2) {
        if (bArr == null && bArr2 != null && bArr2.length > 0) {
            return false;
        }
        if (bArr2 != null || bArr == null || bArr.length <= 0) {
            return bArr == null || bArr2 == null || bArr.length == bArr2.length;
        }
        return false;
    }

    public static boolean a(double[] dArr, double[] dArr2) {
        if (dArr == null && dArr2 != null && dArr2.length > 0) {
            return false;
        }
        if (dArr2 != null || dArr == null || dArr.length <= 0) {
            return dArr == null || dArr2 == null || dArr.length == dArr2.length;
        }
        return false;
    }

    public static boolean a(float[] fArr, float[] fArr2) {
        if (fArr == null && fArr2 != null && fArr2.length > 0) {
            return false;
        }
        if (fArr2 != null || fArr == null || fArr.length <= 0) {
            return fArr == null || fArr2 == null || fArr.length == fArr2.length;
        }
        return false;
    }

    public static boolean a(boolean[] zArr, boolean[] zArr2) {
        if (zArr == null && zArr2 != null && zArr2.length > 0) {
            return false;
        }
        if (zArr2 != null || zArr == null || zArr.length <= 0) {
            return zArr == null || zArr2 == null || zArr.length == zArr2.length;
        }
        return false;
    }

    public static int c(Object obj) {
        if (obj == null) {
            return 0;
        }
        return Array.getLength(obj);
    }

    public static boolean b(Object obj, Object obj2) {
        if (obj != null && obj2 != null) {
            return obj.getClass().getName().equals(obj2.getClass().getName());
        }
        throw new IllegalArgumentException("The Array must not be null");
    }

    public static void d(Object[] objArr) {
        if (objArr != null) {
            int length = objArr.length - 1;
            for (int i2 = 0; length > i2; i2++) {
                Object obj = objArr[length];
                objArr[length] = objArr[i2];
                objArr[i2] = obj;
                length--;
            }
        }
    }

    public static void c(long[] jArr) {
        if (jArr != null) {
            int length = jArr.length - 1;
            for (int i2 = 0; length > i2; i2++) {
                long j2 = jArr[length];
                jArr[length] = jArr[i2];
                jArr[i2] = j2;
                length--;
            }
        }
    }

    public static void c(int[] iArr) {
        if (iArr != null) {
            int length = iArr.length - 1;
            for (int i2 = 0; length > i2; i2++) {
                int i3 = iArr[length];
                iArr[length] = iArr[i2];
                iArr[i2] = i3;
                length--;
            }
        }
    }

    public static void c(short[] sArr) {
        if (sArr != null) {
            int length = sArr.length - 1;
            for (int i2 = 0; length > i2; i2++) {
                short s2 = sArr[length];
                sArr[length] = sArr[i2];
                sArr[i2] = s2;
                length--;
            }
        }
    }

    public static void c(char[] cArr) {
        if (cArr != null) {
            int length = cArr.length - 1;
            for (int i2 = 0; length > i2; i2++) {
                char c2 = cArr[length];
                cArr[length] = cArr[i2];
                cArr[i2] = c2;
                length--;
            }
        }
    }

    public static void c(byte[] bArr) {
        if (bArr != null) {
            int length = bArr.length - 1;
            for (int i2 = 0; length > i2; i2++) {
                byte b2 = bArr[length];
                bArr[length] = bArr[i2];
                bArr[i2] = b2;
                length--;
            }
        }
    }

    public static void c(double[] dArr) {
        if (dArr != null) {
            int length = dArr.length - 1;
            for (int i2 = 0; length > i2; i2++) {
                double d2 = dArr[length];
                dArr[length] = dArr[i2];
                dArr[i2] = d2;
                length--;
            }
        }
    }

    public static void c(float[] fArr) {
        if (fArr != null) {
            int length = fArr.length - 1;
            for (int i2 = 0; length > i2; i2++) {
                float f2 = fArr[length];
                fArr[length] = fArr[i2];
                fArr[i2] = f2;
                length--;
            }
        }
    }

    public static void c(boolean[] zArr) {
        if (zArr != null) {
            int length = zArr.length - 1;
            for (int i2 = 0; length > i2; i2++) {
                boolean z = zArr[length];
                zArr[length] = zArr[i2];
                zArr[i2] = z;
                length--;
            }
        }
    }

    public static int a(Object[] objArr, Object obj) {
        return a(objArr, obj, 0);
    }

    public static int a(Object[] objArr, Object obj, int i2) {
        if (objArr == null) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (obj == null) {
            while (i2 < objArr.length) {
                if (objArr[i2] == null) {
                    return i2;
                }
                i2++;
            }
        } else if (objArr.getClass().getComponentType().isInstance(obj)) {
            while (i2 < objArr.length) {
                if (obj.equals(objArr[i2])) {
                    return i2;
                }
                i2++;
            }
        }
        return -1;
    }

    public static int b(Object[] objArr, Object obj) {
        return b(objArr, obj, Integer.MAX_VALUE);
    }

    public static int b(Object[] objArr, Object obj, int i2) {
        if (objArr == null || i2 < 0) {
            return -1;
        }
        if (i2 >= objArr.length) {
            i2 = objArr.length - 1;
        }
        if (obj == null) {
            while (i2 >= 0) {
                if (objArr[i2] == null) {
                    return i2;
                }
                i2--;
            }
        } else if (objArr.getClass().getComponentType().isInstance(obj)) {
            while (i2 >= 0) {
                if (obj.equals(objArr[i2])) {
                    return i2;
                }
                i2--;
            }
        }
        return -1;
    }

    public static boolean c(Object[] objArr, Object obj) {
        return a(objArr, obj) != -1;
    }

    public static int a(long[] jArr, long j2) {
        return a(jArr, j2, 0);
    }

    public static int a(long[] jArr, long j2, int i2) {
        if (jArr == null) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < jArr.length) {
            if (j2 == jArr[i2]) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int b(long[] jArr, long j2) {
        return b(jArr, j2, Integer.MAX_VALUE);
    }

    public static int b(long[] jArr, long j2, int i2) {
        if (jArr == null || i2 < 0) {
            return -1;
        }
        if (i2 >= jArr.length) {
            i2 = jArr.length - 1;
        }
        while (i2 >= 0) {
            if (j2 == jArr[i2]) {
                return i2;
            }
            i2--;
        }
        return -1;
    }

    public static boolean c(long[] jArr, long j2) {
        return a(jArr, j2) != -1;
    }

    public static int a(int[] iArr, int i2) {
        return b(iArr, i2, 0);
    }

    public static int b(int[] iArr, int i2, int i3) {
        if (iArr == null) {
            return -1;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        while (i3 < iArr.length) {
            if (i2 == iArr[i3]) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public static int b(int[] iArr, int i2) {
        return c(iArr, i2, Integer.MAX_VALUE);
    }

    public static int c(int[] iArr, int i2, int i3) {
        if (iArr == null || i3 < 0) {
            return -1;
        }
        if (i3 >= iArr.length) {
            i3 = iArr.length - 1;
        }
        while (i3 >= 0) {
            if (i2 == iArr[i3]) {
                return i3;
            }
            i3--;
        }
        return -1;
    }

    public static boolean c(int[] iArr, int i2) {
        return a(iArr, i2) != -1;
    }

    public static int a(short[] sArr, short s2) {
        return a(sArr, s2, 0);
    }

    public static int a(short[] sArr, short s2, int i2) {
        if (sArr == null) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < sArr.length) {
            if (s2 == sArr[i2]) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int b(short[] sArr, short s2) {
        return b(sArr, s2, Integer.MAX_VALUE);
    }

    public static int b(short[] sArr, short s2, int i2) {
        if (sArr == null || i2 < 0) {
            return -1;
        }
        if (i2 >= sArr.length) {
            i2 = sArr.length - 1;
        }
        while (i2 >= 0) {
            if (s2 == sArr[i2]) {
                return i2;
            }
            i2--;
        }
        return -1;
    }

    public static boolean c(short[] sArr, short s2) {
        return a(sArr, s2) != -1;
    }

    public static int a(char[] cArr, char c2) {
        return a(cArr, c2, 0);
    }

    public static int a(char[] cArr, char c2, int i2) {
        if (cArr == null) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < cArr.length) {
            if (c2 == cArr[i2]) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int b(char[] cArr, char c2) {
        return b(cArr, c2, Integer.MAX_VALUE);
    }

    public static int b(char[] cArr, char c2, int i2) {
        if (cArr == null || i2 < 0) {
            return -1;
        }
        if (i2 >= cArr.length) {
            i2 = cArr.length - 1;
        }
        while (i2 >= 0) {
            if (c2 == cArr[i2]) {
                return i2;
            }
            i2--;
        }
        return -1;
    }

    public static boolean c(char[] cArr, char c2) {
        return a(cArr, c2) != -1;
    }

    public static int a(byte[] bArr, byte b2) {
        return a(bArr, b2, 0);
    }

    public static int a(byte[] bArr, byte b2, int i2) {
        if (bArr == null) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < bArr.length) {
            if (b2 == bArr[i2]) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int b(byte[] bArr, byte b2) {
        return b(bArr, b2, Integer.MAX_VALUE);
    }

    public static int b(byte[] bArr, byte b2, int i2) {
        if (bArr == null || i2 < 0) {
            return -1;
        }
        if (i2 >= bArr.length) {
            i2 = bArr.length - 1;
        }
        while (i2 >= 0) {
            if (b2 == bArr[i2]) {
                return i2;
            }
            i2--;
        }
        return -1;
    }

    public static boolean c(byte[] bArr, byte b2) {
        return a(bArr, b2) != -1;
    }

    public static int a(double[] dArr, double d2) {
        return a(dArr, d2, 0);
    }

    public static int a(double[] dArr, double d2, double d3) {
        return a(dArr, d2, 0, d3);
    }

    public static int a(double[] dArr, double d2, int i2) {
        if (e(dArr)) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < dArr.length) {
            if (d2 == dArr[i2]) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int a(double[] dArr, double d2, int i2, double d3) {
        if (e(dArr)) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        double d4 = d2 - d3;
        double d5 = d2 + d3;
        while (i2 < dArr.length) {
            if (dArr[i2] >= d4 && dArr[i2] <= d5) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int b(double[] dArr, double d2) {
        return b(dArr, d2, Integer.MAX_VALUE);
    }

    public static int b(double[] dArr, double d2, double d3) {
        return b(dArr, d2, Integer.MAX_VALUE, d3);
    }

    public static int b(double[] dArr, double d2, int i2) {
        if (e(dArr) || i2 < 0) {
            return -1;
        }
        if (i2 >= dArr.length) {
            i2 = dArr.length - 1;
        }
        while (i2 >= 0) {
            if (d2 == dArr[i2]) {
                return i2;
            }
            i2--;
        }
        return -1;
    }

    public static int b(double[] dArr, double d2, int i2, double d3) {
        if (e(dArr) || i2 < 0) {
            return -1;
        }
        if (i2 >= dArr.length) {
            i2 = dArr.length - 1;
        }
        double d4 = d2 - d3;
        double d5 = d2 + d3;
        while (i2 >= 0) {
            if (dArr[i2] >= d4 && dArr[i2] <= d5) {
                return i2;
            }
            i2--;
        }
        return -1;
    }

    public static boolean c(double[] dArr, double d2) {
        return a(dArr, d2) != -1;
    }

    public static boolean c(double[] dArr, double d2, double d3) {
        return a(dArr, d2, 0, d3) != -1;
    }

    public static int a(float[] fArr, float f2) {
        return a(fArr, f2, 0);
    }

    public static int a(float[] fArr, float f2, int i2) {
        if (e(fArr)) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < fArr.length) {
            if (f2 == fArr[i2]) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int b(float[] fArr, float f2) {
        return b(fArr, f2, Integer.MAX_VALUE);
    }

    public static int b(float[] fArr, float f2, int i2) {
        if (e(fArr) || i2 < 0) {
            return -1;
        }
        if (i2 >= fArr.length) {
            i2 = fArr.length - 1;
        }
        while (i2 >= 0) {
            if (f2 == fArr[i2]) {
                return i2;
            }
            i2--;
        }
        return -1;
    }

    public static boolean c(float[] fArr, float f2) {
        return a(fArr, f2) != -1;
    }

    public static int a(boolean[] zArr, boolean z) {
        return a(zArr, z, 0);
    }

    public static int a(boolean[] zArr, boolean z, int i2) {
        if (e(zArr)) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < zArr.length) {
            if (z == zArr[i2]) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int b(boolean[] zArr, boolean z) {
        return b(zArr, z, Integer.MAX_VALUE);
    }

    public static int b(boolean[] zArr, boolean z, int i2) {
        if (e(zArr) || i2 < 0) {
            return -1;
        }
        if (i2 >= zArr.length) {
            i2 = zArr.length - 1;
        }
        while (i2 >= 0) {
            if (z == zArr[i2]) {
                return i2;
            }
            i2--;
        }
        return -1;
    }

    public static boolean c(boolean[] zArr, boolean z) {
        return a(zArr, z) != -1;
    }

    public static char[] b(Character[] chArr) {
        if (chArr == null) {
            return null;
        }
        if (chArr.length == 0) {
            return r;
        }
        char[] cArr = new char[chArr.length];
        for (int i2 = 0; i2 < chArr.length; i2++) {
            cArr[i2] = chArr[i2].charValue();
        }
        return cArr;
    }

    public static char[] a(Character[] chArr, char c2) {
        char c3;
        if (chArr == null) {
            return null;
        }
        if (chArr.length == 0) {
            return r;
        }
        char[] cArr = new char[chArr.length];
        for (int i2 = 0; i2 < chArr.length; i2++) {
            Character ch = chArr[i2];
            if (ch == null) {
                c3 = c2;
            } else {
                c3 = ch.charValue();
            }
            cArr[i2] = c3;
        }
        return cArr;
    }

    public static Character[] d(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        if (cArr.length == 0) {
            return s;
        }
        Character[] chArr = new Character[cArr.length];
        for (int i2 = 0; i2 < cArr.length; i2++) {
            chArr[i2] = new Character(cArr[i2]);
        }
        return chArr;
    }

    public static long[] b(Long[] lArr) {
        if (lArr == null) {
            return null;
        }
        if (lArr.length == 0) {
            return d;
        }
        long[] jArr = new long[lArr.length];
        for (int i2 = 0; i2 < lArr.length; i2++) {
            jArr[i2] = lArr[i2].longValue();
        }
        return jArr;
    }

    public static long[] a(Long[] lArr, long j2) {
        long j3;
        if (lArr == null) {
            return null;
        }
        if (lArr.length == 0) {
            return d;
        }
        long[] jArr = new long[lArr.length];
        for (int i2 = 0; i2 < lArr.length; i2++) {
            Long l2 = lArr[i2];
            if (l2 == null) {
                j3 = j2;
            } else {
                j3 = l2.longValue();
            }
            jArr[i2] = j3;
        }
        return jArr;
    }

    public static Long[] d(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        if (jArr.length == 0) {
            return e;
        }
        Long[] lArr = new Long[jArr.length];
        for (int i2 = 0; i2 < jArr.length; i2++) {
            lArr[i2] = new Long(jArr[i2]);
        }
        return lArr;
    }

    public static int[] b(Integer[] numArr) {
        if (numArr == null) {
            return null;
        }
        if (numArr.length == 0) {
            return f;
        }
        int[] iArr = new int[numArr.length];
        for (int i2 = 0; i2 < numArr.length; i2++) {
            iArr[i2] = numArr[i2].intValue();
        }
        return iArr;
    }

    public static int[] a(Integer[] numArr, int i2) {
        int i3;
        if (numArr == null) {
            return null;
        }
        if (numArr.length == 0) {
            return f;
        }
        int[] iArr = new int[numArr.length];
        for (int i4 = 0; i4 < numArr.length; i4++) {
            Integer num = numArr[i4];
            if (num == null) {
                i3 = i2;
            } else {
                i3 = num.intValue();
            }
            iArr[i4] = i3;
        }
        return iArr;
    }

    public static Integer[] d(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        if (iArr.length == 0) {
            return g;
        }
        Integer[] numArr = new Integer[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            numArr[i2] = new Integer(iArr[i2]);
        }
        return numArr;
    }

    public static short[] b(Short[] shArr) {
        if (shArr == null) {
            return null;
        }
        if (shArr.length == 0) {
            return h;
        }
        short[] sArr = new short[shArr.length];
        for (int i2 = 0; i2 < shArr.length; i2++) {
            sArr[i2] = shArr[i2].shortValue();
        }
        return sArr;
    }

    public static short[] a(Short[] shArr, short s2) {
        short s3;
        if (shArr == null) {
            return null;
        }
        if (shArr.length == 0) {
            return h;
        }
        short[] sArr = new short[shArr.length];
        for (int i2 = 0; i2 < shArr.length; i2++) {
            Short sh = shArr[i2];
            if (sh == null) {
                s3 = s2;
            } else {
                s3 = sh.shortValue();
            }
            sArr[i2] = s3;
        }
        return sArr;
    }

    public static Short[] d(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        if (sArr.length == 0) {
            return i;
        }
        Short[] shArr = new Short[sArr.length];
        for (int i2 = 0; i2 < sArr.length; i2++) {
            shArr[i2] = new Short(sArr[i2]);
        }
        return shArr;
    }

    public static byte[] b(Byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return j;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr2[i2] = bArr[i2].byteValue();
        }
        return bArr2;
    }

    public static byte[] a(Byte[] bArr, byte b2) {
        byte b3;
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return j;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            Byte b4 = bArr[i2];
            if (b4 == null) {
                b3 = b2;
            } else {
                b3 = b4.byteValue();
            }
            bArr2[i2] = b3;
        }
        return bArr2;
    }

    public static Byte[] d(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return k;
        }
        Byte[] bArr2 = new Byte[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr2[i2] = new Byte(bArr[i2]);
        }
        return bArr2;
    }

    public static double[] b(Double[] dArr) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return l;
        }
        double[] dArr2 = new double[dArr.length];
        for (int i2 = 0; i2 < dArr.length; i2++) {
            dArr2[i2] = dArr[i2].doubleValue();
        }
        return dArr2;
    }

    public static double[] a(Double[] dArr, double d2) {
        double d3;
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return l;
        }
        double[] dArr2 = new double[dArr.length];
        for (int i2 = 0; i2 < dArr.length; i2++) {
            Double d4 = dArr[i2];
            if (d4 == null) {
                d3 = d2;
            } else {
                d3 = d4.doubleValue();
            }
            dArr2[i2] = d3;
        }
        return dArr2;
    }

    public static Double[] d(double[] dArr) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return m;
        }
        Double[] dArr2 = new Double[dArr.length];
        for (int i2 = 0; i2 < dArr.length; i2++) {
            dArr2[i2] = new Double(dArr[i2]);
        }
        return dArr2;
    }

    public static float[] b(Float[] fArr) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return n;
        }
        float[] fArr2 = new float[fArr.length];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr2[i2] = fArr[i2].floatValue();
        }
        return fArr2;
    }

    public static float[] a(Float[] fArr, float f2) {
        float f3;
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return n;
        }
        float[] fArr2 = new float[fArr.length];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            Float f4 = fArr[i2];
            if (f4 == null) {
                f3 = f2;
            } else {
                f3 = f4.floatValue();
            }
            fArr2[i2] = f3;
        }
        return fArr2;
    }

    public static Float[] d(float[] fArr) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return o;
        }
        Float[] fArr2 = new Float[fArr.length];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr2[i2] = new Float(fArr[i2]);
        }
        return fArr2;
    }

    public static boolean[] b(Boolean[] boolArr) {
        if (boolArr == null) {
            return null;
        }
        if (boolArr.length == 0) {
            return p;
        }
        boolean[] zArr = new boolean[boolArr.length];
        for (int i2 = 0; i2 < boolArr.length; i2++) {
            zArr[i2] = boolArr[i2].booleanValue();
        }
        return zArr;
    }

    public static boolean[] a(Boolean[] boolArr, boolean z) {
        boolean z2;
        if (boolArr == null) {
            return null;
        }
        if (boolArr.length == 0) {
            return p;
        }
        boolean[] zArr = new boolean[boolArr.length];
        for (int i2 = 0; i2 < boolArr.length; i2++) {
            Boolean bool = boolArr[i2];
            if (bool == null) {
                z2 = z;
            } else {
                z2 = bool.booleanValue();
            }
            zArr[i2] = z2;
        }
        return zArr;
    }

    public static Boolean[] d(boolean[] zArr) {
        if (zArr == null) {
            return null;
        }
        if (zArr.length == 0) {
            return q;
        }
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int i2 = 0; i2 < zArr.length; i2++) {
            boolArr[i2] = zArr[i2] ? Boolean.TRUE : Boolean.FALSE;
        }
        return boolArr;
    }

    public static boolean e(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    public static boolean e(long[] jArr) {
        return jArr == null || jArr.length == 0;
    }

    public static boolean e(int[] iArr) {
        return iArr == null || iArr.length == 0;
    }

    public static boolean e(short[] sArr) {
        return sArr == null || sArr.length == 0;
    }

    public static boolean e(char[] cArr) {
        return cArr == null || cArr.length == 0;
    }

    public static boolean e(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static boolean e(double[] dArr) {
        return dArr == null || dArr.length == 0;
    }

    public static boolean e(float[] fArr) {
        return fArr == null || fArr.length == 0;
    }

    public static boolean e(boolean[] zArr) {
        return zArr == null || zArr.length == 0;
    }

    public static boolean f(Object[] objArr) {
        return (objArr == null || objArr.length == 0) ? false : true;
    }

    public static boolean f(long[] jArr) {
        return (jArr == null || jArr.length == 0) ? false : true;
    }

    public static boolean f(int[] iArr) {
        return (iArr == null || iArr.length == 0) ? false : true;
    }

    public static boolean f(short[] sArr) {
        return (sArr == null || sArr.length == 0) ? false : true;
    }

    public static boolean f(char[] cArr) {
        return (cArr == null || cArr.length == 0) ? false : true;
    }

    public static boolean f(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? false : true;
    }

    public static boolean f(double[] dArr) {
        return (dArr == null || dArr.length == 0) ? false : true;
    }

    public static boolean f(float[] fArr) {
        return (fArr == null || fArr.length == 0) ? false : true;
    }

    public static boolean f(boolean[] zArr) {
        return (zArr == null || zArr.length == 0) ? false : true;
    }

    public static Object[] b(Object[] objArr, Object[] objArr2) {
        if (objArr == null) {
            return b(objArr2);
        }
        if (objArr2 == null) {
            return b(objArr);
        }
        Object[] objArr3 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), objArr.length + objArr2.length);
        System.arraycopy(objArr, 0, objArr3, 0, objArr.length);
        try {
            System.arraycopy(objArr2, 0, objArr3, objArr.length, objArr2.length);
            return objArr3;
        } catch (ArrayStoreException e2) {
            Class<?> componentType = objArr.getClass().getComponentType();
            Class<?> componentType2 = objArr2.getClass().getComponentType();
            if (!componentType.isAssignableFrom(componentType2)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Cannot store ");
                stringBuffer.append(componentType2.getName());
                stringBuffer.append(" in an array of ");
                stringBuffer.append(componentType.getName());
                throw new IllegalArgumentException(stringBuffer.toString());
            }
            throw e2;
        }
    }

    public static boolean[] b(boolean[] zArr, boolean[] zArr2) {
        if (zArr == null) {
            return a(zArr2);
        }
        if (zArr2 == null) {
            return a(zArr);
        }
        boolean[] zArr3 = new boolean[(zArr.length + zArr2.length)];
        System.arraycopy(zArr, 0, zArr3, 0, zArr.length);
        System.arraycopy(zArr2, 0, zArr3, zArr.length, zArr2.length);
        return zArr3;
    }

    public static char[] b(char[] cArr, char[] cArr2) {
        if (cArr == null) {
            return a(cArr2);
        }
        if (cArr2 == null) {
            return a(cArr);
        }
        char[] cArr3 = new char[(cArr.length + cArr2.length)];
        System.arraycopy(cArr, 0, cArr3, 0, cArr.length);
        System.arraycopy(cArr2, 0, cArr3, cArr.length, cArr2.length);
        return cArr3;
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return a(bArr2);
        }
        if (bArr2 == null) {
            return a(bArr);
        }
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static short[] b(short[] sArr, short[] sArr2) {
        if (sArr == null) {
            return a(sArr2);
        }
        if (sArr2 == null) {
            return a(sArr);
        }
        short[] sArr3 = new short[(sArr.length + sArr2.length)];
        System.arraycopy(sArr, 0, sArr3, 0, sArr.length);
        System.arraycopy(sArr2, 0, sArr3, sArr.length, sArr2.length);
        return sArr3;
    }

    public static int[] b(int[] iArr, int[] iArr2) {
        if (iArr == null) {
            return a(iArr2);
        }
        if (iArr2 == null) {
            return a(iArr);
        }
        int[] iArr3 = new int[(iArr.length + iArr2.length)];
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
        return iArr3;
    }

    public static long[] b(long[] jArr, long[] jArr2) {
        if (jArr == null) {
            return a(jArr2);
        }
        if (jArr2 == null) {
            return a(jArr);
        }
        long[] jArr3 = new long[(jArr.length + jArr2.length)];
        System.arraycopy(jArr, 0, jArr3, 0, jArr.length);
        System.arraycopy(jArr2, 0, jArr3, jArr.length, jArr2.length);
        return jArr3;
    }

    public static float[] b(float[] fArr, float[] fArr2) {
        if (fArr == null) {
            return a(fArr2);
        }
        if (fArr2 == null) {
            return a(fArr);
        }
        float[] fArr3 = new float[(fArr.length + fArr2.length)];
        System.arraycopy(fArr, 0, fArr3, 0, fArr.length);
        System.arraycopy(fArr2, 0, fArr3, fArr.length, fArr2.length);
        return fArr3;
    }

    public static double[] b(double[] dArr, double[] dArr2) {
        if (dArr == null) {
            return a(dArr2);
        }
        if (dArr2 == null) {
            return a(dArr);
        }
        double[] dArr3 = new double[(dArr.length + dArr2.length)];
        System.arraycopy(dArr, 0, dArr3, 0, dArr.length);
        System.arraycopy(dArr2, 0, dArr3, dArr.length, dArr2.length);
        return dArr3;
    }

    public static Object[] d(Object[] objArr, Object obj) {
        Class<?> cls;
        if (objArr != null) {
            cls = objArr.getClass();
        } else if (obj != null) {
            cls = obj.getClass();
        } else if (u == null) {
            cls = a("java.lang.Object");
            u = cls;
        } else {
            cls = u;
        }
        Object[] objArr2 = (Object[]) a((Object) objArr, (Class) cls);
        objArr2[objArr2.length - 1] = obj;
        return objArr2;
    }

    static Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    public static boolean[] d(boolean[] zArr, boolean z) {
        boolean[] zArr2 = (boolean[]) a((Object) zArr, Boolean.TYPE);
        zArr2[zArr2.length - 1] = z;
        return zArr2;
    }

    public static byte[] d(byte[] bArr, byte b2) {
        byte[] bArr2 = (byte[]) a((Object) bArr, Byte.TYPE);
        bArr2[bArr2.length - 1] = b2;
        return bArr2;
    }

    public static char[] d(char[] cArr, char c2) {
        char[] cArr2 = (char[]) a((Object) cArr, Character.TYPE);
        cArr2[cArr2.length - 1] = c2;
        return cArr2;
    }

    public static double[] d(double[] dArr, double d2) {
        double[] dArr2 = (double[]) a((Object) dArr, Double.TYPE);
        dArr2[dArr2.length - 1] = d2;
        return dArr2;
    }

    public static float[] d(float[] fArr, float f2) {
        float[] fArr2 = (float[]) a((Object) fArr, Float.TYPE);
        fArr2[fArr2.length - 1] = f2;
        return fArr2;
    }

    public static int[] d(int[] iArr, int i2) {
        int[] iArr2 = (int[]) a((Object) iArr, Integer.TYPE);
        iArr2[iArr2.length - 1] = i2;
        return iArr2;
    }

    public static long[] d(long[] jArr, long j2) {
        long[] jArr2 = (long[]) a((Object) jArr, Long.TYPE);
        jArr2[jArr2.length - 1] = j2;
        return jArr2;
    }

    public static short[] d(short[] sArr, short s2) {
        short[] sArr2 = (short[]) a((Object) sArr, Short.TYPE);
        sArr2[sArr2.length - 1] = s2;
        return sArr2;
    }

    private static Object a(Object obj, Class cls) {
        if (obj == null) {
            return Array.newInstance(cls, 1);
        }
        int length = Array.getLength(obj);
        Object newInstance = Array.newInstance(obj.getClass().getComponentType(), length + 1);
        System.arraycopy(obj, 0, newInstance, 0, length);
        return newInstance;
    }

    public static Object[] a(Object[] objArr, int i2, Object obj) {
        Class<?> cls;
        if (objArr != null) {
            cls = objArr.getClass().getComponentType();
        } else if (obj != null) {
            cls = obj.getClass();
        } else {
            return new Object[]{null};
        }
        return (Object[]) a((Object) objArr, i2, obj, (Class) cls);
    }

    public static boolean[] a(boolean[] zArr, int i2, boolean z) {
        return (boolean[]) a((Object) zArr, i2, (Object) BooleanUtils.a(z), Boolean.TYPE);
    }

    public static char[] a(char[] cArr, int i2, char c2) {
        return (char[]) a((Object) cArr, i2, (Object) new Character(c2), Character.TYPE);
    }

    public static byte[] a(byte[] bArr, int i2, byte b2) {
        return (byte[]) a((Object) bArr, i2, (Object) new Byte(b2), Byte.TYPE);
    }

    public static short[] a(short[] sArr, int i2, short s2) {
        return (short[]) a((Object) sArr, i2, (Object) new Short(s2), Short.TYPE);
    }

    public static int[] d(int[] iArr, int i2, int i3) {
        return (int[]) a((Object) iArr, i2, (Object) new Integer(i3), Integer.TYPE);
    }

    public static long[] a(long[] jArr, int i2, long j2) {
        return (long[]) a((Object) jArr, i2, (Object) new Long(j2), Long.TYPE);
    }

    public static float[] a(float[] fArr, int i2, float f2) {
        return (float[]) a((Object) fArr, i2, (Object) new Float(f2), Float.TYPE);
    }

    public static double[] a(double[] dArr, int i2, double d2) {
        return (double[]) a((Object) dArr, i2, (Object) new Double(d2), Double.TYPE);
    }

    private static Object a(Object obj, int i2, Object obj2, Class cls) {
        if (obj != null) {
            int length = Array.getLength(obj);
            if (i2 > length || i2 < 0) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Index: ");
                stringBuffer.append(i2);
                stringBuffer.append(", Length: ");
                stringBuffer.append(length);
                throw new IndexOutOfBoundsException(stringBuffer.toString());
            }
            Object newInstance = Array.newInstance(cls, length + 1);
            System.arraycopy(obj, 0, newInstance, 0, i2);
            Array.set(newInstance, i2, obj2);
            if (i2 < length) {
                System.arraycopy(obj, i2, newInstance, i2 + 1, length - i2);
            }
            return newInstance;
        } else if (i2 == 0) {
            Object newInstance2 = Array.newInstance(cls, 1);
            Array.set(newInstance2, 0, obj2);
            return newInstance2;
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Index: ");
            stringBuffer2.append(i2);
            stringBuffer2.append(", Length: 0");
            throw new IndexOutOfBoundsException(stringBuffer2.toString());
        }
    }

    public static Object[] a(Object[] objArr, int i2) {
        return (Object[]) a((Object) objArr, i2);
    }

    public static Object[] e(Object[] objArr, Object obj) {
        int a2 = a(objArr, obj);
        if (a2 == -1) {
            return b(objArr);
        }
        return a(objArr, a2);
    }

    public static boolean[] a(boolean[] zArr, int i2) {
        return (boolean[]) a((Object) zArr, i2);
    }

    public static boolean[] e(boolean[] zArr, boolean z) {
        int a2 = a(zArr, z);
        if (a2 == -1) {
            return a(zArr);
        }
        return a(zArr, a2);
    }

    public static byte[] a(byte[] bArr, int i2) {
        return (byte[]) a((Object) bArr, i2);
    }

    public static byte[] e(byte[] bArr, byte b2) {
        int a2 = a(bArr, b2);
        if (a2 == -1) {
            return a(bArr);
        }
        return a(bArr, a2);
    }

    public static char[] a(char[] cArr, int i2) {
        return (char[]) a((Object) cArr, i2);
    }

    public static char[] e(char[] cArr, char c2) {
        int a2 = a(cArr, c2);
        if (a2 == -1) {
            return a(cArr);
        }
        return a(cArr, a2);
    }

    public static double[] a(double[] dArr, int i2) {
        return (double[]) a((Object) dArr, i2);
    }

    public static double[] e(double[] dArr, double d2) {
        int a2 = a(dArr, d2);
        if (a2 == -1) {
            return a(dArr);
        }
        return a(dArr, a2);
    }

    public static float[] a(float[] fArr, int i2) {
        return (float[]) a((Object) fArr, i2);
    }

    public static float[] e(float[] fArr, float f2) {
        int a2 = a(fArr, f2);
        if (a2 == -1) {
            return a(fArr);
        }
        return a(fArr, a2);
    }

    public static int[] e(int[] iArr, int i2) {
        return (int[]) a((Object) iArr, i2);
    }

    public static int[] f(int[] iArr, int i2) {
        int a2 = a(iArr, i2);
        if (a2 == -1) {
            return a(iArr);
        }
        return e(iArr, a2);
    }

    public static long[] a(long[] jArr, int i2) {
        return (long[]) a((Object) jArr, i2);
    }

    public static long[] e(long[] jArr, long j2) {
        int a2 = a(jArr, j2);
        if (a2 == -1) {
            return a(jArr);
        }
        return a(jArr, a2);
    }

    public static short[] a(short[] sArr, int i2) {
        return (short[]) a((Object) sArr, i2);
    }

    public static short[] e(short[] sArr, short s2) {
        int a2 = a(sArr, s2);
        if (a2 == -1) {
            return a(sArr);
        }
        return a(sArr, a2);
    }

    private static Object a(Object obj, int i2) {
        int c2 = c(obj);
        if (i2 < 0 || i2 >= c2) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Index: ");
            stringBuffer.append(i2);
            stringBuffer.append(", Length: ");
            stringBuffer.append(c2);
            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }
        int i3 = c2 - 1;
        Object newInstance = Array.newInstance(obj.getClass().getComponentType(), i3);
        System.arraycopy(obj, 0, newInstance, 0, i2);
        if (i2 < i3) {
            System.arraycopy(obj, i2 + 1, newInstance, i2, (c2 - i2) - 1);
        }
        return newInstance;
    }
}
