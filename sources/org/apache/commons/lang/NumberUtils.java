package org.apache.commons.lang;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class NumberUtils {
    public static int a(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    public static long a(long j, long j2, long j3) {
        if (j2 < j) {
            j = j2;
        }
        return j3 < j ? j3 : j;
    }

    public static int b(int i, int i2, int i3) {
        if (i2 > i) {
            i = i2;
        }
        return i3 > i ? i3 : i;
    }

    public static long b(long j, long j2, long j3) {
        if (j2 > j) {
            j = j2;
        }
        return j3 > j ? j3 : j;
    }

    public static int a(String str) {
        return a(str, 0);
    }

    public static int a(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:108|(1:112)|113|114|(1:120)|121|122|(1:128)|129|131) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:102|103|104) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:(1:36)|37|(1:42)(1:41)|43|(5:45|(3:47|(2:49|(2:51|(1:53)))|(2:69|70)(3:63|64|65))|71|72|(1:78))|79|80|(1:86)|(3:87|88|89)) */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0194, code lost:
        return f(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0199, code lost:
        return g(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ef, code lost:
        if (r1 == 'l') goto L_0x00f1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:102:0x0190 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:121:0x01bc */
    /* JADX WARNING: Missing exception handler attribute for start block: B:79:0x0143 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:87:0x0159 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Number b(java.lang.String r12) throws java.lang.NumberFormatException {
        /*
            r0 = 0
            if (r12 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r12.length()
            if (r1 == 0) goto L_0x01db
            int r1 = r12.length()
            r2 = 0
            r3 = 1
            if (r1 != r3) goto L_0x0034
            char r1 = r12.charAt(r2)
            boolean r1 = java.lang.Character.isDigit(r1)
            if (r1 == 0) goto L_0x001d
            goto L_0x0034
        L_0x001d:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r1.append(r12)
            java.lang.String r12 = " is not a valid number."
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r0.<init>(r12)
            throw r0
        L_0x0034:
            java.lang.String r1 = "--"
            boolean r1 = r12.startsWith(r1)
            if (r1 == 0) goto L_0x003d
            return r0
        L_0x003d:
            java.lang.String r1 = "0x"
            boolean r1 = r12.startsWith(r1)
            if (r1 != 0) goto L_0x01d6
            java.lang.String r1 = "-0x"
            boolean r1 = r12.startsWith(r1)
            if (r1 == 0) goto L_0x004f
            goto L_0x01d6
        L_0x004f:
            int r1 = r12.length()
            int r1 = r1 - r3
            char r1 = r12.charAt(r1)
            r4 = 46
            int r4 = r12.indexOf(r4)
            r5 = 101(0x65, float:1.42E-43)
            int r5 = r12.indexOf(r5)
            r6 = 69
            int r6 = r12.indexOf(r6)
            int r5 = r5 + r6
            int r5 = r5 + r3
            r6 = -1
            if (r4 <= r6) goto L_0x009c
            if (r5 <= r6) goto L_0x0091
            if (r5 < r4) goto L_0x007a
            int r7 = r4 + 1
            java.lang.String r7 = r12.substring(r7, r5)
            goto L_0x0097
        L_0x007a:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r1.append(r12)
            java.lang.String r12 = " is not a valid number."
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r0.<init>(r12)
            throw r0
        L_0x0091:
            int r7 = r4 + 1
            java.lang.String r7 = r12.substring(r7)
        L_0x0097:
            java.lang.String r4 = r12.substring(r2, r4)
            goto L_0x00a5
        L_0x009c:
            if (r5 <= r6) goto L_0x00a3
            java.lang.String r4 = r12.substring(r2, r5)
            goto L_0x00a4
        L_0x00a3:
            r4 = r12
        L_0x00a4:
            r7 = r0
        L_0x00a5:
            boolean r8 = java.lang.Character.isDigit(r1)
            r9 = 0
            r10 = 0
            if (r8 != 0) goto L_0x0175
            if (r5 <= r6) goto L_0x00c1
            int r6 = r12.length()
            int r6 = r6 - r3
            if (r5 >= r6) goto L_0x00c1
            int r5 = r5 + r3
            int r0 = r12.length()
            int r0 = r0 - r3
            java.lang.String r0 = r12.substring(r5, r0)
        L_0x00c1:
            int r5 = r12.length()
            int r5 = r5 - r3
            java.lang.String r5 = r12.substring(r2, r5)
            boolean r4 = k(r4)
            if (r4 == 0) goto L_0x00d8
            boolean r4 = k(r0)
            if (r4 == 0) goto L_0x00d8
            r4 = 1
            goto L_0x00d9
        L_0x00d8:
            r4 = 0
        L_0x00d9:
            r6 = 68
            if (r1 == r6) goto L_0x0143
            r6 = 70
            if (r1 == r6) goto L_0x012e
            r6 = 76
            if (r1 == r6) goto L_0x00f1
            r6 = 100
            if (r1 == r6) goto L_0x0143
            r6 = 102(0x66, float:1.43E-43)
            if (r1 == r6) goto L_0x012e
            r4 = 108(0x6c, float:1.51E-43)
            if (r1 != r4) goto L_0x015e
        L_0x00f1:
            if (r7 != 0) goto L_0x0117
            if (r0 != 0) goto L_0x0117
            char r0 = r5.charAt(r2)
            r1 = 45
            if (r0 != r1) goto L_0x0107
            java.lang.String r0 = r5.substring(r3)
            boolean r0 = i(r0)
            if (r0 != 0) goto L_0x010d
        L_0x0107:
            boolean r0 = i(r5)
            if (r0 == 0) goto L_0x0117
        L_0x010d:
            java.lang.Long r12 = f(r5)     // Catch:{ NumberFormatException -> 0x0112 }
            return r12
        L_0x0112:
            java.math.BigInteger r12 = g(r5)
            return r12
        L_0x0117:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r1.append(r12)
            java.lang.String r12 = " is not a valid number."
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r0.<init>(r12)
            throw r0
        L_0x012e:
            java.lang.Float r0 = c(r5)     // Catch:{ NumberFormatException -> 0x0143 }
            boolean r1 = r0.isInfinite()     // Catch:{ NumberFormatException -> 0x0143 }
            if (r1 != 0) goto L_0x0143
            float r1 = r0.floatValue()     // Catch:{ NumberFormatException -> 0x0143 }
            int r1 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r1 != 0) goto L_0x0142
            if (r4 == 0) goto L_0x0143
        L_0x0142:
            return r0
        L_0x0143:
            java.lang.Double r0 = d(r5)     // Catch:{ NumberFormatException -> 0x0159 }
            boolean r1 = r0.isInfinite()     // Catch:{ NumberFormatException -> 0x0159 }
            if (r1 != 0) goto L_0x0159
            float r1 = r0.floatValue()     // Catch:{ NumberFormatException -> 0x0159 }
            double r1 = (double) r1
            int r3 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r3 != 0) goto L_0x0158
            if (r4 == 0) goto L_0x0159
        L_0x0158:
            return r0
        L_0x0159:
            java.math.BigDecimal r0 = h(r5)     // Catch:{ NumberFormatException -> 0x015e }
            return r0
        L_0x015e:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r1.append(r12)
            java.lang.String r12 = " is not a valid number."
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r0.<init>(r12)
            throw r0
        L_0x0175:
            if (r5 <= r6) goto L_0x0187
            int r1 = r12.length()
            int r1 = r1 - r3
            if (r5 >= r1) goto L_0x0187
            int r5 = r5 + r3
            int r0 = r12.length()
            java.lang.String r0 = r12.substring(r5, r0)
        L_0x0187:
            if (r7 != 0) goto L_0x019a
            if (r0 != 0) goto L_0x019a
            java.lang.Integer r0 = e(r12)     // Catch:{ NumberFormatException -> 0x0190 }
            return r0
        L_0x0190:
            java.lang.Long r0 = f(r12)     // Catch:{ NumberFormatException -> 0x0195 }
            return r0
        L_0x0195:
            java.math.BigInteger r12 = g(r12)
            return r12
        L_0x019a:
            boolean r1 = k(r4)
            if (r1 == 0) goto L_0x01a7
            boolean r0 = k(r0)
            if (r0 == 0) goto L_0x01a7
            r2 = 1
        L_0x01a7:
            java.lang.Float r0 = c(r12)     // Catch:{ NumberFormatException -> 0x01bc }
            boolean r1 = r0.isInfinite()     // Catch:{ NumberFormatException -> 0x01bc }
            if (r1 != 0) goto L_0x01bc
            float r1 = r0.floatValue()     // Catch:{ NumberFormatException -> 0x01bc }
            int r1 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r1 != 0) goto L_0x01bb
            if (r2 == 0) goto L_0x01bc
        L_0x01bb:
            return r0
        L_0x01bc:
            java.lang.Double r0 = d(r12)     // Catch:{ NumberFormatException -> 0x01d1 }
            boolean r1 = r0.isInfinite()     // Catch:{ NumberFormatException -> 0x01d1 }
            if (r1 != 0) goto L_0x01d1
            double r3 = r0.doubleValue()     // Catch:{ NumberFormatException -> 0x01d1 }
            int r1 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r1 != 0) goto L_0x01d0
            if (r2 == 0) goto L_0x01d1
        L_0x01d0:
            return r0
        L_0x01d1:
            java.math.BigDecimal r12 = h(r12)
            return r12
        L_0x01d6:
            java.lang.Integer r12 = e(r12)
            return r12
        L_0x01db:
            java.lang.NumberFormatException r12 = new java.lang.NumberFormatException
            java.lang.String r0 = "\"\" is not a valid number."
            r12.<init>(r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.NumberUtils.b(java.lang.String):java.lang.Number");
    }

    private static boolean k(String str) {
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

    public static Float c(String str) {
        return Float.valueOf(str);
    }

    public static Double d(String str) {
        return Double.valueOf(str);
    }

    public static Integer e(String str) {
        return Integer.decode(str);
    }

    public static Long f(String str) {
        return Long.valueOf(str);
    }

    public static BigInteger g(String str) {
        return new BigInteger(str);
    }

    public static BigDecimal h(String str) {
        return new BigDecimal(str);
    }

    public static int a(double d, double d2) {
        if (d < d2) {
            return -1;
        }
        if (d > d2) {
            return 1;
        }
        long doubleToLongBits = Double.doubleToLongBits(d);
        long doubleToLongBits2 = Double.doubleToLongBits(d2);
        if (doubleToLongBits == doubleToLongBits2) {
            return 0;
        }
        return doubleToLongBits < doubleToLongBits2 ? -1 : 1;
    }

    public static int a(float f, float f2) {
        if (f < f2) {
            return -1;
        }
        if (f > f2) {
            return 1;
        }
        int floatToIntBits = Float.floatToIntBits(f);
        int floatToIntBits2 = Float.floatToIntBits(f2);
        if (floatToIntBits == floatToIntBits2) {
            return 0;
        }
        return floatToIntBits < floatToIntBits2 ? -1 : 1;
    }

    public static boolean i(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean j(String str) {
        if (StringUtils.a(str)) {
            return false;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        boolean z = true;
        int i = charArray[0] == '-' ? 1 : 0;
        int i2 = i + 1;
        if (length > i2 && charArray[i] == '0' && charArray[i2] == 'x') {
            int i3 = i + 2;
            if (i3 == length) {
                return false;
            }
            while (i3 < charArray.length) {
                if ((charArray[i3] < '0' || charArray[i3] > '9') && ((charArray[i3] < 'a' || charArray[i3] > 'f') && (charArray[i3] < 'A' || charArray[i3] > 'F'))) {
                    return false;
                }
                i3++;
            }
            return true;
        }
        int i4 = length - 1;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        while (true) {
            if (i < i4 || (i < i4 + 1 && z2 && !z3)) {
                if (charArray[i] >= '0' && charArray[i] <= '9') {
                    z2 = false;
                    z3 = true;
                } else if (charArray[i] == '.') {
                    if (z4 || z5) {
                        return false;
                    }
                    z4 = true;
                } else if (charArray[i] != 'e' && charArray[i] != 'E') {
                    if (charArray[i] != '+') {
                        if (charArray[i] != '-') {
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
                i++;
                z = true;
            }
        }
        if (i < charArray.length) {
            if (charArray[i] >= '0' && charArray[i] <= '9') {
                return z;
            }
            if (charArray[i] == 'e' || charArray[i] == 'E') {
                return false;
            }
            if (!z2 && (charArray[i] == 'd' || charArray[i] == 'D' || charArray[i] == 'f' || charArray[i] == 'F')) {
                return z3;
            }
            if ((charArray[i] == 'l' || charArray[i] == 'L') && z3 && !z5) {
                return true;
            }
            return false;
        } else if (z2 || !z3) {
            return false;
        } else {
            return true;
        }
    }
}
