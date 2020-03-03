package org.apache.commons.lang;

import com.mobikwik.sdk.lib.Constants;
import org.apache.commons.lang.math.NumberUtils;

public class BooleanUtils {
    public static int a(boolean z, int i, int i2) {
        return z ? i : i2;
    }

    public static Integer a(boolean z, Integer num, Integer num2) {
        return z ? num : num2;
    }

    public static String a(boolean z, String str, String str2) {
        return z ? str : str2;
    }

    public static boolean a(int i) {
        return i != 0;
    }

    public static int b(boolean z) {
        return z ? 1 : 0;
    }

    public static Boolean a(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return bool.booleanValue() ? Boolean.FALSE : Boolean.TRUE;
    }

    public static boolean b(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static boolean c(Boolean bool) {
        return !b(bool);
    }

    public static boolean d(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return !bool.booleanValue();
    }

    public static boolean e(Boolean bool) {
        return !d(bool);
    }

    public static Boolean a(boolean z) {
        return z ? Boolean.TRUE : Boolean.FALSE;
    }

    public static boolean f(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static boolean a(Boolean bool, boolean z) {
        return bool == null ? z : bool.booleanValue();
    }

    public static Boolean b(int i) {
        return i == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Boolean a(Integer num) {
        if (num == null) {
            return null;
        }
        return num.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static boolean a(int i, int i2, int i3) {
        if (i == i2) {
            return true;
        }
        if (i == i3) {
            return false;
        }
        throw new IllegalArgumentException("The Integer did not match either specified value");
    }

    public static boolean a(Integer num, Integer num2, Integer num3) {
        if (num == null) {
            if (num2 == null) {
                return true;
            }
            if (num3 == null) {
                return false;
            }
        } else if (num.equals(num2)) {
            return true;
        } else {
            if (num.equals(num3)) {
                return false;
            }
        }
        throw new IllegalArgumentException("The Integer did not match either specified value");
    }

    public static Boolean a(int i, int i2, int i3, int i4) {
        if (i == i2) {
            return Boolean.TRUE;
        }
        if (i == i3) {
            return Boolean.FALSE;
        }
        if (i == i4) {
            return null;
        }
        throw new IllegalArgumentException("The Integer did not match any specified value");
    }

    public static Boolean a(Integer num, Integer num2, Integer num3, Integer num4) {
        if (num == null) {
            if (num2 == null) {
                return Boolean.TRUE;
            }
            if (num3 == null) {
                return Boolean.FALSE;
            }
            if (num4 == null) {
                return null;
            }
        } else if (num.equals(num2)) {
            return Boolean.TRUE;
        } else {
            if (num.equals(num3)) {
                return Boolean.FALSE;
            }
            if (num.equals(num4)) {
                return null;
            }
        }
        throw new IllegalArgumentException("The Integer did not match any specified value");
    }

    public static Integer c(boolean z) {
        return z ? NumberUtils.e : NumberUtils.d;
    }

    public static Integer g(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return bool.booleanValue() ? NumberUtils.e : NumberUtils.d;
    }

    public static int a(Boolean bool, int i, int i2, int i3) {
        if (bool == null) {
            return i3;
        }
        return bool.booleanValue() ? i : i2;
    }

    public static Integer a(Boolean bool, Integer num, Integer num2, Integer num3) {
        if (bool == null) {
            return num3;
        }
        return bool.booleanValue() ? num : num2;
    }

    public static Boolean a(String str) {
        String str2 = str;
        if (str2 == "true") {
            return Boolean.TRUE;
        }
        if (str2 == null) {
            return null;
        }
        switch (str.length()) {
            case 1:
                char charAt = str2.charAt(0);
                if (charAt == 'y' || charAt == 'Y' || charAt == 't' || charAt == 'T') {
                    return Boolean.TRUE;
                }
                if (charAt == 'n' || charAt == 'N' || charAt == 'f' || charAt == 'F') {
                    return Boolean.FALSE;
                }
                return null;
            case 2:
                char charAt2 = str2.charAt(0);
                char charAt3 = str2.charAt(1);
                if ((charAt2 == 'o' || charAt2 == 'O') && (charAt3 == 'n' || charAt3 == 'N')) {
                    return Boolean.TRUE;
                }
                if (charAt2 != 'n' && charAt2 != 'N') {
                    return null;
                }
                if (charAt3 == 'o' || charAt3 == 'O') {
                    return Boolean.FALSE;
                }
                return null;
            case 3:
                char charAt4 = str2.charAt(0);
                char charAt5 = str2.charAt(1);
                char charAt6 = str2.charAt(2);
                if ((charAt4 == 'y' || charAt4 == 'Y') && ((charAt5 == 'e' || charAt5 == 'E') && (charAt6 == 's' || charAt6 == 'S'))) {
                    return Boolean.TRUE;
                }
                if (charAt4 != 'o' && charAt4 != 'O') {
                    return null;
                }
                if (charAt5 != 'f' && charAt5 != 'F') {
                    return null;
                }
                if (charAt6 == 'f' || charAt6 == 'F') {
                    return Boolean.FALSE;
                }
                return null;
            case 4:
                char charAt7 = str2.charAt(0);
                char charAt8 = str2.charAt(1);
                char charAt9 = str2.charAt(2);
                char charAt10 = str2.charAt(3);
                if (charAt7 != 't' && charAt7 != 'T') {
                    return null;
                }
                if (charAt8 != 'r' && charAt8 != 'R') {
                    return null;
                }
                if (charAt9 != 'u' && charAt9 != 'U') {
                    return null;
                }
                if (charAt10 == 'e' || charAt10 == 'E') {
                    return Boolean.TRUE;
                }
                return null;
            case 5:
                char charAt11 = str2.charAt(0);
                char charAt12 = str2.charAt(1);
                char charAt13 = str2.charAt(2);
                char charAt14 = str2.charAt(3);
                char charAt15 = str2.charAt(4);
                if (charAt11 != 'f' && charAt11 != 'F') {
                    return null;
                }
                if (charAt12 != 'a' && charAt12 != 'A') {
                    return null;
                }
                if (charAt13 != 'l' && charAt13 != 'L') {
                    return null;
                }
                if (charAt14 != 's' && charAt14 != 'S') {
                    return null;
                }
                if (charAt15 == 'e' || charAt15 == 'E') {
                    return Boolean.FALSE;
                }
                return null;
            default:
                return null;
        }
    }

    public static Boolean a(String str, String str2, String str3, String str4) {
        if (str == null) {
            if (str2 == null) {
                return Boolean.TRUE;
            }
            if (str3 == null) {
                return Boolean.FALSE;
            }
            if (str4 == null) {
                return null;
            }
        } else if (str.equals(str2)) {
            return Boolean.TRUE;
        } else {
            if (str.equals(str3)) {
                return Boolean.FALSE;
            }
            if (str.equals(str4)) {
                return null;
            }
        }
        throw new IllegalArgumentException("The String did not match any specified value");
    }

    public static boolean b(String str) {
        return f(a(str));
    }

    public static boolean a(String str, String str2, String str3) {
        if (str == null) {
            if (str2 == null) {
                return true;
            }
            if (str3 == null) {
                return false;
            }
        } else if (str.equals(str2)) {
            return true;
        } else {
            if (str.equals(str3)) {
                return false;
            }
        }
        throw new IllegalArgumentException("The String did not match either specified value");
    }

    public static String h(Boolean bool) {
        return a(bool, "true", "false", (String) null);
    }

    public static String i(Boolean bool) {
        return a(bool, "on", "off", (String) null);
    }

    public static String j(Boolean bool) {
        return a(bool, Constants.YES, "no", (String) null);
    }

    public static String a(Boolean bool, String str, String str2, String str3) {
        if (bool == null) {
            return str3;
        }
        return bool.booleanValue() ? str : str2;
    }

    public static String d(boolean z) {
        return a(z, "true", "false");
    }

    public static String e(boolean z) {
        return a(z, "on", "off");
    }

    public static String f(boolean z) {
        return a(z, Constants.YES, "no");
    }

    public static boolean a(boolean[] zArr) {
        if (zArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (zArr.length != 0) {
            int i = 0;
            for (boolean z : zArr) {
                if (z) {
                    if (i >= 1) {
                        return false;
                    }
                    i++;
                }
            }
            if (i == 1) {
                return true;
            }
            return false;
        } else {
            throw new IllegalArgumentException("Array is empty");
        }
    }

    public static Boolean a(Boolean[] boolArr) {
        if (boolArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (boolArr.length != 0) {
            try {
                return a(ArrayUtils.b(boolArr)) ? Boolean.TRUE : Boolean.FALSE;
            } catch (NullPointerException unused) {
                throw new IllegalArgumentException("The array must not contain any null elements");
            }
        } else {
            throw new IllegalArgumentException("Array is empty");
        }
    }
}
