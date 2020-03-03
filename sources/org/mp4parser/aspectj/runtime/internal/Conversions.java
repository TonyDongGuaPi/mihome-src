package org.mp4parser.aspectj.runtime.internal;

public final class Conversions {
    public static Object a() {
        return null;
    }

    public static Object i(Object obj) {
        return obj == null ? obj : obj;
    }

    private Conversions() {
    }

    public static Object a(int i) {
        return new Integer(i);
    }

    public static Object a(short s) {
        return new Short(s);
    }

    public static Object a(byte b) {
        return new Byte(b);
    }

    public static Object a(char c) {
        return new Character(c);
    }

    public static Object a(long j) {
        return new Long(j);
    }

    public static Object a(float f) {
        return new Float(f);
    }

    public static Object a(double d) {
        return new Double(d);
    }

    public static Object a(boolean z) {
        return new Boolean(z);
    }

    public static int a(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(obj.getClass().getName());
        stringBuffer.append(" can not be converted to int");
        throw new ClassCastException(stringBuffer.toString());
    }

    public static long b(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(obj.getClass().getName());
        stringBuffer.append(" can not be converted to long");
        throw new ClassCastException(stringBuffer.toString());
    }

    public static float c(Object obj) {
        if (obj == null) {
            return 0.0f;
        }
        if (obj instanceof Number) {
            return ((Number) obj).floatValue();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(obj.getClass().getName());
        stringBuffer.append(" can not be converted to float");
        throw new ClassCastException(stringBuffer.toString());
    }

    public static double d(Object obj) {
        if (obj == null) {
            return 0.0d;
        }
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(obj.getClass().getName());
        stringBuffer.append(" can not be converted to double");
        throw new ClassCastException(stringBuffer.toString());
    }

    public static byte e(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Number) {
            return ((Number) obj).byteValue();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(obj.getClass().getName());
        stringBuffer.append(" can not be converted to byte");
        throw new ClassCastException(stringBuffer.toString());
    }

    public static short f(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Number) {
            return ((Number) obj).shortValue();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(obj.getClass().getName());
        stringBuffer.append(" can not be converted to short");
        throw new ClassCastException(stringBuffer.toString());
    }

    public static char g(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Character) {
            return ((Character) obj).charValue();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(obj.getClass().getName());
        stringBuffer.append(" can not be converted to char");
        throw new ClassCastException(stringBuffer.toString());
    }

    public static boolean h(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(obj.getClass().getName());
        stringBuffer.append(" can not be converted to boolean");
        throw new ClassCastException(stringBuffer.toString());
    }
}
