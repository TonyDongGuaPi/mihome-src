package org.slf4j.helpers;

import com.taobao.weex.el.parse.Operators;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public final class MessageFormatter {

    /* renamed from: a  reason: collision with root package name */
    static final char f4176a = '{';
    static final char b = '}';
    static final String c = "{}";
    private static final char d = '\\';

    public static final String a(String str, Object obj) {
        return a(str, new Object[]{obj});
    }

    public static final String a(String str, Object obj, Object obj2) {
        return a(str, new Object[]{obj, obj2});
    }

    public static final String a(String str, Object[] objArr) {
        int i;
        if (str == null) {
            return null;
        }
        if (objArr == null) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 50);
        int i2 = 0;
        int i3 = 0;
        while (i2 < objArr.length) {
            int indexOf = str.indexOf(c, i3);
            if (indexOf != -1) {
                if (!a(str, indexOf)) {
                    stringBuffer.append(str.substring(i3, indexOf));
                    a(stringBuffer, objArr[i2], (Map) new HashMap());
                    i = indexOf + 2;
                } else if (!b(str, indexOf)) {
                    i2--;
                    stringBuffer.append(str.substring(i3, indexOf - 1));
                    stringBuffer.append('{');
                    i = indexOf + 1;
                } else {
                    stringBuffer.append(str.substring(i3, indexOf - 1));
                    a(stringBuffer, objArr[i2], (Map) new HashMap());
                    i = indexOf + 2;
                }
                i3 = i;
                i2++;
            } else if (i3 == 0) {
                return str;
            } else {
                stringBuffer.append(str.substring(i3, str.length()));
                return stringBuffer.toString();
            }
        }
        stringBuffer.append(str.substring(i3, str.length()));
        return stringBuffer.toString();
    }

    static final boolean a(String str, int i) {
        return i != 0 && str.charAt(i - 1) == '\\';
    }

    static final boolean b(String str, int i) {
        return i >= 2 && str.charAt(i - 2) == '\\';
    }

    private static void a(StringBuffer stringBuffer, Object obj, Map map) {
        if (obj == null) {
            stringBuffer.append("null");
        } else if (!obj.getClass().isArray()) {
            a(stringBuffer, obj);
        } else if (obj instanceof boolean[]) {
            a(stringBuffer, (boolean[]) obj);
        } else if (obj instanceof byte[]) {
            a(stringBuffer, (byte[]) obj);
        } else if (obj instanceof char[]) {
            a(stringBuffer, (char[]) obj);
        } else if (obj instanceof short[]) {
            a(stringBuffer, (short[]) obj);
        } else if (obj instanceof int[]) {
            a(stringBuffer, (int[]) obj);
        } else if (obj instanceof long[]) {
            a(stringBuffer, (long[]) obj);
        } else if (obj instanceof float[]) {
            a(stringBuffer, (float[]) obj);
        } else if (obj instanceof double[]) {
            a(stringBuffer, (double[]) obj);
        } else {
            a(stringBuffer, (Object[]) obj, map);
        }
    }

    private static void a(StringBuffer stringBuffer, Object obj) {
        try {
            stringBuffer.append(obj.toString());
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("SLF4J: Failed toString() invocation on an object of type [");
            stringBuffer2.append(obj.getClass().getName());
            stringBuffer2.append(Operators.ARRAY_END_STR);
            printStream.println(stringBuffer2.toString());
            th.printStackTrace();
            stringBuffer.append("[FAILED toString()]");
        }
    }

    private static void a(StringBuffer stringBuffer, Object[] objArr, Map map) {
        stringBuffer.append(Operators.ARRAY_START);
        if (!map.containsKey(objArr)) {
            map.put(objArr, (Object) null);
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                a(stringBuffer, objArr[i], map);
                if (i != length - 1) {
                    stringBuffer.append(", ");
                }
            }
            map.remove(objArr);
        } else {
            stringBuffer.append("...");
        }
        stringBuffer.append(Operators.ARRAY_END);
    }

    private static void a(StringBuffer stringBuffer, boolean[] zArr) {
        stringBuffer.append(Operators.ARRAY_START);
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(zArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(Operators.ARRAY_END);
    }

    private static void a(StringBuffer stringBuffer, byte[] bArr) {
        stringBuffer.append(Operators.ARRAY_START);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(bArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(Operators.ARRAY_END);
    }

    private static void a(StringBuffer stringBuffer, char[] cArr) {
        stringBuffer.append(Operators.ARRAY_START);
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(cArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(Operators.ARRAY_END);
    }

    private static void a(StringBuffer stringBuffer, short[] sArr) {
        stringBuffer.append(Operators.ARRAY_START);
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(sArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(Operators.ARRAY_END);
    }

    private static void a(StringBuffer stringBuffer, int[] iArr) {
        stringBuffer.append(Operators.ARRAY_START);
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(iArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(Operators.ARRAY_END);
    }

    private static void a(StringBuffer stringBuffer, long[] jArr) {
        stringBuffer.append(Operators.ARRAY_START);
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(jArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(Operators.ARRAY_END);
    }

    private static void a(StringBuffer stringBuffer, float[] fArr) {
        stringBuffer.append(Operators.ARRAY_START);
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(fArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(Operators.ARRAY_END);
    }

    private static void a(StringBuffer stringBuffer, double[] dArr) {
        stringBuffer.append(Operators.ARRAY_START);
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(dArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(Operators.ARRAY_END);
    }
}
