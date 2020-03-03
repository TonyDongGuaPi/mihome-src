package org.apache.commons.lang;

public class CharUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final char f3359a = '\n';
    public static final char b = '\r';
    private static final String c = "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
    private static final String[] d = new String[128];
    private static final Character[] e = new Character[128];

    public static boolean e(char c2) {
        return c2 < 128;
    }

    public static boolean f(char c2) {
        return c2 >= ' ' && c2 < 127;
    }

    public static boolean g(char c2) {
        return c2 < ' ' || c2 == 127;
    }

    public static boolean h(char c2) {
        return (c2 >= 'A' && c2 <= 'Z') || (c2 >= 'a' && c2 <= 'z');
    }

    public static boolean i(char c2) {
        return c2 >= 'A' && c2 <= 'Z';
    }

    public static boolean j(char c2) {
        return c2 >= 'a' && c2 <= 'z';
    }

    public static boolean k(char c2) {
        return c2 >= '0' && c2 <= '9';
    }

    public static boolean l(char c2) {
        return (c2 >= 'A' && c2 <= 'Z') || (c2 >= 'a' && c2 <= 'z') || (c2 >= '0' && c2 <= '9');
    }

    static boolean m(char c2) {
        return 55296 <= c2 && 56319 >= c2;
    }

    static {
        for (int i = 127; i >= 0; i--) {
            d[i] = c.substring(i, i + 1);
            e[i] = new Character((char) i);
        }
    }

    public static Character a(char c2) {
        if (c2 < e.length) {
            return e[c2];
        }
        return new Character(c2);
    }

    public static Character a(String str) {
        if (StringUtils.a(str)) {
            return null;
        }
        return a(str.charAt(0));
    }

    public static char a(Character ch) {
        if (ch != null) {
            return ch.charValue();
        }
        throw new IllegalArgumentException("The Character must not be null");
    }

    public static char a(Character ch, char c2) {
        return ch == null ? c2 : ch.charValue();
    }

    public static char b(String str) {
        if (!StringUtils.a(str)) {
            return str.charAt(0);
        }
        throw new IllegalArgumentException("The String must not be empty");
    }

    public static char a(String str, char c2) {
        if (StringUtils.a(str)) {
            return c2;
        }
        return str.charAt(0);
    }

    public static int b(char c2) {
        if (k(c2)) {
            return c2 - '0';
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("The character ");
        stringBuffer.append(c2);
        stringBuffer.append(" is not in the range '0' - '9'");
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static int a(char c2, int i) {
        return !k(c2) ? i : c2 - '0';
    }

    public static int b(Character ch) {
        if (ch != null) {
            return b(ch.charValue());
        }
        throw new IllegalArgumentException("The character must not be null");
    }

    public static int a(Character ch, int i) {
        return ch == null ? i : a(ch.charValue(), i);
    }

    public static String c(char c2) {
        if (c2 < 128) {
            return d[c2];
        }
        return new String(new char[]{c2});
    }

    public static String c(Character ch) {
        if (ch == null) {
            return null;
        }
        return c(ch.charValue());
    }

    public static String d(char c2) {
        if (c2 < 16) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("\\u000");
            stringBuffer.append(Integer.toHexString(c2));
            return stringBuffer.toString();
        } else if (c2 < 256) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("\\u00");
            stringBuffer2.append(Integer.toHexString(c2));
            return stringBuffer2.toString();
        } else if (c2 < 4096) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("\\u0");
            stringBuffer3.append(Integer.toHexString(c2));
            return stringBuffer3.toString();
        } else {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("\\u");
            stringBuffer4.append(Integer.toHexString(c2));
            return stringBuffer4.toString();
        }
    }

    public static String d(Character ch) {
        if (ch == null) {
            return null;
        }
        return d(ch.charValue());
    }
}
