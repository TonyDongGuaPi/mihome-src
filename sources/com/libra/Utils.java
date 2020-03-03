package com.libra;

public class Utils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6239a = "Utils_TMTEST";
    private static int b = 750;
    private static float c;
    private static int d;
    private static final LruCache<String, Integer> e = new LruCache<>(100);

    public static boolean a(char c2) {
        return ' ' == c2 || 9 == c2 || 10 == c2;
    }

    public static boolean b(char c2) {
        return c2 >= '0' && c2 <= '9';
    }

    public static boolean c(char c2) {
        return (c2 >= '0' && c2 <= '9') || (c2 >= 'a' && c2 <= 'f') || (c2 >= 'A' && c2 <= 'F');
    }

    public static void a(float f, int i) {
        c = f;
        d = i;
    }

    public static void a(int i) {
        b = i;
    }

    public static int a(double d2) {
        double d3 = (double) d;
        Double.isNaN(d3);
        double d4 = d2 * d3;
        double d5 = (double) b;
        Double.isNaN(d5);
        return (int) ((d4 / d5) + 0.5d);
    }

    public static int b(double d2) {
        float f = c < 0.0f ? 1.0f : c;
        if (d2 >= 0.0d) {
            double d3 = (double) f;
            Double.isNaN(d3);
            return (int) ((d2 * d3) + 0.5d);
        }
        double d4 = (double) f;
        Double.isNaN(d4);
        return -((int) (((-d2) * d4) + 0.5d));
    }

    public static int a(float f) {
        return (int) ((f / (c < 0.0f ? 1.0f : c)) + 0.5f);
    }

    public static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        if ((str.charAt(0) == '$' && str.charAt(1) == '{' && str.charAt(length - 1) == '}') || (str.charAt(0) == '@' && str.charAt(1) == '{' && str.charAt(length - 1) == '}')) {
            return true;
        }
        return false;
    }

    public static boolean b(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        if (str.charAt(0) == '@' && str.charAt(1) == '{' && str.charAt(length - 1) == '}') {
            return true;
        }
        return false;
    }

    public static Boolean a(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (!(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        if ("true".equalsIgnoreCase(str)) {
            return true;
        }
        return "false".equalsIgnoreCase(str) ? false : null;
    }

    public static Float b(Object obj) {
        if (obj instanceof Float) {
            return (Float) obj;
        }
        if (obj instanceof Double) {
            return Float.valueOf(((Double) obj).floatValue());
        }
        if (obj instanceof Number) {
            return Float.valueOf(((Number) obj).floatValue());
        }
        if (!(obj instanceof String)) {
            return null;
        }
        try {
            return Float.valueOf((String) obj);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Double c(Object obj) {
        if (obj instanceof Double) {
            return (Double) obj;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (!(obj instanceof String)) {
            return null;
        }
        try {
            return Double.valueOf((String) obj);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Integer d(Object obj) {
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (!(obj instanceof String)) {
            return null;
        }
        try {
            return Integer.valueOf((int) Double.parseDouble((String) obj));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Long e(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (!(obj instanceof String)) {
            return null;
        }
        try {
            return Long.valueOf((long) Double.parseDouble((String) obj));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static String f(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    public static int c(String str) {
        try {
            Integer a2 = e.a(str);
            if (a2 != null) {
                return a2.intValue();
            }
            Integer valueOf = Integer.valueOf(Color.a(str));
            e.a(str, valueOf);
            return valueOf.intValue();
        } catch (Exception unused) {
            return 0;
        }
    }
}
