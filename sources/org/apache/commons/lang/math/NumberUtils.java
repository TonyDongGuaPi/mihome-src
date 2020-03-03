package org.apache.commons.lang.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.lang.StringUtils;

public class NumberUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final Long f3395a = new Long(0);
    public static final Long b = new Long(1);
    public static final Long c = new Long(-1);
    public static final Integer d = new Integer(0);
    public static final Integer e = new Integer(1);
    public static final Integer f = new Integer(-1);
    public static final Short g = new Short(0);
    public static final Short h = new Short(1);
    public static final Short i = new Short(-1);
    public static final Byte j = new Byte((byte) 0);
    public static final Byte k = new Byte((byte) 1);
    public static final Byte l = new Byte((byte) -1);
    public static final Double m = new Double(0.0d);
    public static final Double n = new Double(1.0d);
    public static final Double o = new Double(-1.0d);
    public static final Float p = new Float(0.0f);
    public static final Float q = new Float(1.0f);
    public static final Float r = new Float(-1.0f);

    public static byte a(byte b2, byte b3, byte b4) {
        if (b3 < b2) {
            b2 = b3;
        }
        return b4 < b2 ? b4 : b2;
    }

    public static int a(int i2, int i3, int i4) {
        if (i3 < i2) {
            i2 = i3;
        }
        return i4 < i2 ? i4 : i2;
    }

    public static long a(long j2, long j3, long j4) {
        if (j3 < j2) {
            j2 = j3;
        }
        return j4 < j2 ? j4 : j2;
    }

    public static short a(short s, short s2, short s3) {
        if (s2 < s) {
            s = s2;
        }
        return s3 < s ? s3 : s;
    }

    public static byte b(byte b2, byte b3, byte b4) {
        if (b3 > b2) {
            b2 = b3;
        }
        return b4 > b2 ? b4 : b2;
    }

    public static int b(int i2, int i3, int i4) {
        if (i3 > i2) {
            i2 = i3;
        }
        return i4 > i2 ? i4 : i2;
    }

    public static long b(long j2, long j3, long j4) {
        if (j3 > j2) {
            j2 = j3;
        }
        return j4 > j2 ? j4 : j2;
    }

    public static short b(short s, short s2, short s3) {
        if (s2 > s) {
            s = s2;
        }
        return s3 > s ? s3 : s;
    }

    public static int a(String str) {
        return b(str);
    }

    public static int b(String str) {
        return b(str, 0);
    }

    public static int a(String str, int i2) {
        return b(str, i2);
    }

    public static int b(String str, int i2) {
        if (str == null) {
            return i2;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i2;
        }
    }

    public static long c(String str) {
        return a(str, 0);
    }

    public static long a(String str, long j2) {
        if (str == null) {
            return j2;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j2;
        }
    }

    public static float d(String str) {
        return a(str, 0.0f);
    }

    public static float a(String str, float f2) {
        if (str == null) {
            return f2;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f2;
        }
    }

    public static double e(String str) {
        return a(str, 0.0d);
    }

    public static double a(String str, double d2) {
        if (str == null) {
            return d2;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return d2;
        }
    }

    public static byte f(String str) {
        return a(str, (byte) 0);
    }

    public static byte a(String str, byte b2) {
        if (str == null) {
            return b2;
        }
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException unused) {
            return b2;
        }
    }

    public static short g(String str) {
        return a(str, 0);
    }

    public static short a(String str, short s) {
        if (str == null) {
            return s;
        }
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException unused) {
            return s;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:(1:37)|38|(1:43)(1:42)|44|(5:46|(3:48|(2:50|(2:52|(1:54)))|(2:70|71)(3:64|65|66))|72|73|(1:79))|80|81|(1:87)|88|89|90) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:103|104|105) */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0191, code lost:
        return l(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0196, code lost:
        return m(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ec, code lost:
        if (r1 == 'l') goto L_0x00ee;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:103:0x018d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:122:0x01ba */
    /* JADX WARNING: Missing exception handler attribute for start block: B:80:0x0140 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:88:0x0156 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Number h(java.lang.String r13) throws java.lang.NumberFormatException {
        /*
            r0 = 0
            if (r13 != 0) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = org.apache.commons.lang.StringUtils.c((java.lang.String) r13)
            if (r1 != 0) goto L_0x01d9
            java.lang.String r1 = "--"
            boolean r1 = r13.startsWith(r1)
            if (r1 == 0) goto L_0x0013
            return r0
        L_0x0013:
            java.lang.String r1 = "0x"
            boolean r1 = r13.startsWith(r1)
            if (r1 != 0) goto L_0x01d4
            java.lang.String r1 = "-0x"
            boolean r1 = r13.startsWith(r1)
            if (r1 == 0) goto L_0x0025
            goto L_0x01d4
        L_0x0025:
            int r1 = r13.length()
            r2 = 1
            int r1 = r1 - r2
            char r1 = r13.charAt(r1)
            r3 = 46
            int r4 = r13.indexOf(r3)
            r5 = 101(0x65, float:1.42E-43)
            int r5 = r13.indexOf(r5)
            r6 = 69
            int r6 = r13.indexOf(r6)
            int r5 = r5 + r6
            int r5 = r5 + r2
            r6 = -1
            r7 = 0
            if (r4 <= r6) goto L_0x007a
            if (r5 <= r6) goto L_0x006f
            if (r5 < r4) goto L_0x0058
            int r8 = r13.length()
            if (r5 > r8) goto L_0x0058
            int r8 = r4 + 1
            java.lang.String r8 = r13.substring(r8, r5)
            goto L_0x0075
        L_0x0058:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r1.append(r13)
            java.lang.String r13 = " is not a valid number."
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.<init>(r13)
            throw r0
        L_0x006f:
            int r8 = r4 + 1
            java.lang.String r8 = r13.substring(r8)
        L_0x0075:
            java.lang.String r4 = r13.substring(r7, r4)
            goto L_0x00a0
        L_0x007a:
            if (r5 <= r6) goto L_0x009e
            int r4 = r13.length()
            if (r5 > r4) goto L_0x0087
            java.lang.String r4 = r13.substring(r7, r5)
            goto L_0x009f
        L_0x0087:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r1.append(r13)
            java.lang.String r13 = " is not a valid number."
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.<init>(r13)
            throw r0
        L_0x009e:
            r4 = r13
        L_0x009f:
            r8 = r0
        L_0x00a0:
            boolean r9 = java.lang.Character.isDigit(r1)
            r10 = 0
            r11 = 0
            if (r9 != 0) goto L_0x0172
            if (r1 == r3) goto L_0x0172
            if (r5 <= r6) goto L_0x00be
            int r3 = r13.length()
            int r3 = r3 - r2
            if (r5 >= r3) goto L_0x00be
            int r5 = r5 + r2
            int r0 = r13.length()
            int r0 = r0 - r2
            java.lang.String r0 = r13.substring(r5, r0)
        L_0x00be:
            int r3 = r13.length()
            int r3 = r3 - r2
            java.lang.String r3 = r13.substring(r7, r3)
            boolean r4 = q(r4)
            if (r4 == 0) goto L_0x00d5
            boolean r4 = q(r0)
            if (r4 == 0) goto L_0x00d5
            r4 = 1
            goto L_0x00d6
        L_0x00d5:
            r4 = 0
        L_0x00d6:
            r5 = 68
            if (r1 == r5) goto L_0x0140
            r5 = 70
            if (r1 == r5) goto L_0x012b
            r5 = 76
            if (r1 == r5) goto L_0x00ee
            r5 = 100
            if (r1 == r5) goto L_0x0140
            r5 = 102(0x66, float:1.43E-43)
            if (r1 == r5) goto L_0x012b
            r4 = 108(0x6c, float:1.51E-43)
            if (r1 != r4) goto L_0x015b
        L_0x00ee:
            if (r8 != 0) goto L_0x0114
            if (r0 != 0) goto L_0x0114
            char r0 = r3.charAt(r7)
            r1 = 45
            if (r0 != r1) goto L_0x0104
            java.lang.String r0 = r3.substring(r2)
            boolean r0 = o(r0)
            if (r0 != 0) goto L_0x010a
        L_0x0104:
            boolean r0 = o(r3)
            if (r0 == 0) goto L_0x0114
        L_0x010a:
            java.lang.Long r13 = l(r3)     // Catch:{ NumberFormatException -> 0x010f }
            return r13
        L_0x010f:
            java.math.BigInteger r13 = m(r3)
            return r13
        L_0x0114:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r1.append(r13)
            java.lang.String r13 = " is not a valid number."
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.<init>(r13)
            throw r0
        L_0x012b:
            java.lang.Float r0 = i(r3)     // Catch:{ NumberFormatException -> 0x0140 }
            boolean r1 = r0.isInfinite()     // Catch:{ NumberFormatException -> 0x0140 }
            if (r1 != 0) goto L_0x0140
            float r1 = r0.floatValue()     // Catch:{ NumberFormatException -> 0x0140 }
            int r1 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r1 != 0) goto L_0x013f
            if (r4 == 0) goto L_0x0140
        L_0x013f:
            return r0
        L_0x0140:
            java.lang.Double r0 = j(r3)     // Catch:{ NumberFormatException -> 0x0156 }
            boolean r1 = r0.isInfinite()     // Catch:{ NumberFormatException -> 0x0156 }
            if (r1 != 0) goto L_0x0156
            float r1 = r0.floatValue()     // Catch:{ NumberFormatException -> 0x0156 }
            double r1 = (double) r1
            int r5 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r5 != 0) goto L_0x0155
            if (r4 == 0) goto L_0x0156
        L_0x0155:
            return r0
        L_0x0156:
            java.math.BigDecimal r0 = n(r3)     // Catch:{ NumberFormatException -> 0x015b }
            return r0
        L_0x015b:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r1.append(r13)
            java.lang.String r13 = " is not a valid number."
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.<init>(r13)
            throw r0
        L_0x0172:
            if (r5 <= r6) goto L_0x0184
            int r1 = r13.length()
            int r1 = r1 - r2
            if (r5 >= r1) goto L_0x0184
            int r5 = r5 + r2
            int r0 = r13.length()
            java.lang.String r0 = r13.substring(r5, r0)
        L_0x0184:
            if (r8 != 0) goto L_0x0197
            if (r0 != 0) goto L_0x0197
            java.lang.Integer r0 = k(r13)     // Catch:{ NumberFormatException -> 0x018d }
            return r0
        L_0x018d:
            java.lang.Long r0 = l(r13)     // Catch:{ NumberFormatException -> 0x0192 }
            return r0
        L_0x0192:
            java.math.BigInteger r13 = m(r13)
            return r13
        L_0x0197:
            boolean r1 = q(r4)
            if (r1 == 0) goto L_0x01a4
            boolean r0 = q(r0)
            if (r0 == 0) goto L_0x01a4
            goto L_0x01a5
        L_0x01a4:
            r2 = 0
        L_0x01a5:
            java.lang.Float r0 = i(r13)     // Catch:{ NumberFormatException -> 0x01ba }
            boolean r1 = r0.isInfinite()     // Catch:{ NumberFormatException -> 0x01ba }
            if (r1 != 0) goto L_0x01ba
            float r1 = r0.floatValue()     // Catch:{ NumberFormatException -> 0x01ba }
            int r1 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r1 != 0) goto L_0x01b9
            if (r2 == 0) goto L_0x01ba
        L_0x01b9:
            return r0
        L_0x01ba:
            java.lang.Double r0 = j(r13)     // Catch:{ NumberFormatException -> 0x01cf }
            boolean r1 = r0.isInfinite()     // Catch:{ NumberFormatException -> 0x01cf }
            if (r1 != 0) goto L_0x01cf
            double r3 = r0.doubleValue()     // Catch:{ NumberFormatException -> 0x01cf }
            int r1 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r1 != 0) goto L_0x01ce
            if (r2 == 0) goto L_0x01cf
        L_0x01ce:
            return r0
        L_0x01cf:
            java.math.BigDecimal r13 = n(r13)
            return r13
        L_0x01d4:
            java.lang.Integer r13 = k(r13)
            return r13
        L_0x01d9:
            java.lang.NumberFormatException r13 = new java.lang.NumberFormatException
            java.lang.String r0 = "A blank string is not a valid number"
            r13.<init>(r0)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.math.NumberUtils.h(java.lang.String):java.lang.Number");
    }

    private static boolean q(String str) {
        if (str == null) {
            return true;
        }
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) != '0') {
                return false;
            }
        }
        if (str.length() > 0) {
            return true;
        }
        return false;
    }

    public static Float i(String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }

    public static Double j(String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(str);
    }

    public static Integer k(String str) {
        if (str == null) {
            return null;
        }
        return Integer.decode(str);
    }

    public static Long l(String str) {
        if (str == null) {
            return null;
        }
        return Long.valueOf(str);
    }

    public static BigInteger m(String str) {
        if (str == null) {
            return null;
        }
        return new BigInteger(str);
    }

    public static BigDecimal n(String str) {
        if (str == null) {
            return null;
        }
        if (!StringUtils.c(str)) {
            return new BigDecimal(str);
        }
        throw new NumberFormatException("A blank string is not a valid number");
    }

    public static long a(long[] jArr) {
        if (jArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (jArr.length != 0) {
            long j2 = jArr[0];
            for (int i2 = 1; i2 < jArr.length; i2++) {
                if (jArr[i2] < j2) {
                    j2 = jArr[i2];
                }
            }
            return j2;
        } else {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static int a(int[] iArr) {
        if (iArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (iArr.length != 0) {
            int i2 = iArr[0];
            for (int i3 = 1; i3 < iArr.length; i3++) {
                if (iArr[i3] < i2) {
                    i2 = iArr[i3];
                }
            }
            return i2;
        } else {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static short a(short[] sArr) {
        if (sArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (sArr.length != 0) {
            short s = sArr[0];
            for (int i2 = 1; i2 < sArr.length; i2++) {
                if (sArr[i2] < s) {
                    s = sArr[i2];
                }
            }
            return s;
        } else {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static byte a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (bArr.length != 0) {
            byte b2 = bArr[0];
            for (int i2 = 1; i2 < bArr.length; i2++) {
                if (bArr[i2] < b2) {
                    b2 = bArr[i2];
                }
            }
            return b2;
        } else {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static double a(double[] dArr) {
        if (dArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (dArr.length != 0) {
            double d2 = dArr[0];
            for (int i2 = 1; i2 < dArr.length; i2++) {
                if (Double.isNaN(dArr[i2])) {
                    return Double.NaN;
                }
                if (dArr[i2] < d2) {
                    d2 = dArr[i2];
                }
            }
            return d2;
        } else {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static float a(float[] fArr) {
        if (fArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (fArr.length != 0) {
            float f2 = fArr[0];
            for (int i2 = 1; i2 < fArr.length; i2++) {
                if (Float.isNaN(fArr[i2])) {
                    return Float.NaN;
                }
                if (fArr[i2] < f2) {
                    f2 = fArr[i2];
                }
            }
            return f2;
        } else {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static long b(long[] jArr) {
        if (jArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (jArr.length != 0) {
            long j2 = jArr[0];
            for (int i2 = 1; i2 < jArr.length; i2++) {
                if (jArr[i2] > j2) {
                    j2 = jArr[i2];
                }
            }
            return j2;
        } else {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static int b(int[] iArr) {
        if (iArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (iArr.length != 0) {
            int i2 = iArr[0];
            for (int i3 = 1; i3 < iArr.length; i3++) {
                if (iArr[i3] > i2) {
                    i2 = iArr[i3];
                }
            }
            return i2;
        } else {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static short b(short[] sArr) {
        if (sArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (sArr.length != 0) {
            short s = sArr[0];
            for (int i2 = 1; i2 < sArr.length; i2++) {
                if (sArr[i2] > s) {
                    s = sArr[i2];
                }
            }
            return s;
        } else {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static byte b(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (bArr.length != 0) {
            byte b2 = bArr[0];
            for (int i2 = 1; i2 < bArr.length; i2++) {
                if (bArr[i2] > b2) {
                    b2 = bArr[i2];
                }
            }
            return b2;
        } else {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static double b(double[] dArr) {
        if (dArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (dArr.length != 0) {
            double d2 = dArr[0];
            for (int i2 = 1; i2 < dArr.length; i2++) {
                if (Double.isNaN(dArr[i2])) {
                    return Double.NaN;
                }
                if (dArr[i2] > d2) {
                    d2 = dArr[i2];
                }
            }
            return d2;
        } else {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static float b(float[] fArr) {
        if (fArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (fArr.length != 0) {
            float f2 = fArr[0];
            for (int i2 = 1; i2 < fArr.length; i2++) {
                if (Float.isNaN(fArr[i2])) {
                    return Float.NaN;
                }
                if (fArr[i2] > f2) {
                    f2 = fArr[i2];
                }
            }
            return f2;
        } else {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static double a(double d2, double d3, double d4) {
        return Math.min(Math.min(d2, d3), d4);
    }

    public static float a(float f2, float f3, float f4) {
        return Math.min(Math.min(f2, f3), f4);
    }

    public static double b(double d2, double d3, double d4) {
        return Math.max(Math.max(d2, d3), d4);
    }

    public static float b(float f2, float f3, float f4) {
        return Math.max(Math.max(f2, f3), f4);
    }

    public static int a(double d2, double d3) {
        if (d2 < d3) {
            return -1;
        }
        if (d2 > d3) {
            return 1;
        }
        long doubleToLongBits = Double.doubleToLongBits(d2);
        long doubleToLongBits2 = Double.doubleToLongBits(d3);
        if (doubleToLongBits == doubleToLongBits2) {
            return 0;
        }
        return doubleToLongBits < doubleToLongBits2 ? -1 : 1;
    }

    public static int a(float f2, float f3) {
        if (f2 < f3) {
            return -1;
        }
        if (f2 > f3) {
            return 1;
        }
        int floatToIntBits = Float.floatToIntBits(f2);
        int floatToIntBits2 = Float.floatToIntBits(f3);
        if (floatToIntBits == floatToIntBits2) {
            return 0;
        }
        return floatToIntBits < floatToIntBits2 ? -1 : 1;
    }

    public static boolean o(String str) {
        if (StringUtils.a(str)) {
            return false;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (!Character.isDigit(str.charAt(i2))) {
                return false;
            }
        }
        return true;
    }

    public static boolean p(String str) {
        if (StringUtils.a(str)) {
            return false;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        boolean z = true;
        int i2 = charArray[0] == '-' ? 1 : 0;
        int i3 = i2 + 1;
        if (length > i3 && charArray[i2] == '0' && charArray[i3] == 'x') {
            int i4 = i2 + 2;
            if (i4 == length) {
                return false;
            }
            while (i4 < charArray.length) {
                if ((charArray[i4] < '0' || charArray[i4] > '9') && ((charArray[i4] < 'a' || charArray[i4] > 'f') && (charArray[i4] < 'A' || charArray[i4] > 'F'))) {
                    return false;
                }
                i4++;
            }
            return true;
        }
        int i5 = length - 1;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        while (true) {
            if (i2 >= i5) {
                if (i2 >= i5 + 1 || !z2 || z3) {
                }
            }
            if (charArray[i2] >= '0' && charArray[i2] <= '9') {
                z2 = false;
                z3 = true;
            } else if (charArray[i2] == '.') {
                if (z4 || z5) {
                    return false;
                }
                z4 = true;
            } else if (charArray[i2] != 'e' && charArray[i2] != 'E') {
                if (charArray[i2] != '+') {
                    if (charArray[i2] != '-') {
                        return false;
                    }
                }
                if (!z2) {
                    return false;
                }
                z2 = false;
                z3 = false;
            } else if (z5 || !z3) {
                return false;
            } else {
                z2 = true;
                z5 = true;
            }
            i2++;
            z = true;
        }
        if (i2 < charArray.length) {
            if (charArray[i2] >= '0' && charArray[i2] <= '9') {
                return z;
            }
            if (charArray[i2] == 'e' || charArray[i2] == 'E') {
                return false;
            }
            if (charArray[i2] == '.') {
                if (z4 || z5) {
                    return false;
                }
                return z3;
            } else if (!z2 && (charArray[i2] == 'd' || charArray[i2] == 'D' || charArray[i2] == 'f' || charArray[i2] == 'F')) {
                return z3;
            } else {
                if ((charArray[i2] == 'l' || charArray[i2] == 'L') && z3 && !z5) {
                    return true;
                }
                return false;
            }
        } else if (z2 || !z3) {
            return false;
        } else {
            return true;
        }
    }
}
