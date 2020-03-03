package org.apache.commons.lang;

import java.util.Random;

public class RandomStringUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final Random f3371a = new Random();

    public static String a(int i) {
        return a(i, false, false);
    }

    public static String b(int i) {
        return a(i, 32, 127, false, false);
    }

    public static String c(int i) {
        return a(i, true, false);
    }

    public static String d(int i) {
        return a(i, true, true);
    }

    public static String e(int i) {
        return a(i, false, true);
    }

    public static String a(int i, boolean z, boolean z2) {
        return a(i, 0, 0, z, z2);
    }

    public static String a(int i, int i2, int i3, boolean z, boolean z2) {
        return a(i, i2, i3, z, z2, (char[]) null, f3371a);
    }

    public static String a(int i, int i2, int i3, boolean z, boolean z2, char[] cArr) {
        return a(i, i2, i3, z, z2, cArr, f3371a);
    }

    public static String a(int i, int i2, int i3, boolean z, boolean z2, char[] cArr, Random random) {
        char c;
        if (i == 0) {
            return "";
        }
        if (i >= 0) {
            if (i2 == 0 && i3 == 0) {
                i3 = 123;
                i2 = 32;
                if (!z && !z2) {
                    i2 = 0;
                    i3 = Integer.MAX_VALUE;
                }
            }
            char[] cArr2 = new char[i];
            int i4 = i3 - i2;
            while (true) {
                int i5 = i - 1;
                if (i == 0) {
                    return new String(cArr2);
                }
                if (cArr == null) {
                    c = (char) (random.nextInt(i4) + i2);
                } else {
                    c = cArr[random.nextInt(i4) + i2];
                }
                if ((!z || !Character.isLetter(c)) && ((!z2 || !Character.isDigit(c)) && (z || z2))) {
                    i5++;
                } else if (c < 56320 || c > 57343) {
                    if (c < 55296 || c > 56191) {
                        if (c < 56192 || c > 56319) {
                            cArr2[i5] = c;
                        } else {
                            i5++;
                        }
                    } else if (i5 == 0) {
                        i5++;
                    } else {
                        cArr2[i5] = (char) (random.nextInt(128) + 56320);
                        i5--;
                        cArr2[i5] = c;
                    }
                } else if (i5 == 0) {
                    i5++;
                } else {
                    cArr2[i5] = c;
                    i5--;
                    cArr2[i5] = (char) (random.nextInt(128) + 55296);
                }
                i = i5;
            }
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Requested random string length ");
            stringBuffer.append(i);
            stringBuffer.append(" is less than 0.");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    public static String a(int i, String str) {
        if (str != null) {
            return a(i, str.toCharArray());
        }
        return a(i, 0, 0, false, false, (char[]) null, f3371a);
    }

    public static String a(int i, char[] cArr) {
        if (cArr == null) {
            return a(i, 0, 0, false, false, (char[]) null, f3371a);
        }
        return a(i, 0, cArr.length, false, false, cArr, f3371a);
    }
}
